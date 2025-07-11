package com.wandoo;

import com.wandoo.testutils.WandooAssert;
import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SwaperLoginTest extends BaseTest {
    private static boolean skipRegistrationTest = false;
    private static Cookies cookies;
    private static String csrfToken;

    @BeforeAll
    public static void setupBaseURI() {
        // Set base URI
        RestAssured.baseURI = "https://swaper.com";
    }

    @Test
    @Order(1)
    public void testLoginToSwaper() {
        Response loginResponse = doLogin(getProp("username"), getProp("password"));

        WandooAssert.assertEquals(log, 200, loginResponse.statusCode()
                , "Checking response status");
        WandooAssert.assertEquals(log, "REGISTERED", loginResponse.path("status")
                , "Checking user status");
        WandooAssert.assertEquals(log, 0.00f, loginResponse.path("accountBalance")
                , "Checking user account balance");
        skipRegistrationTest = true;
    }

    @Test
    @Order(2)
    public void registerIfLoginFailed(TestInfo testInfo) {
        if (skipRegistrationTest) {
            log.info("Skipping registration — already logged in");
            return;
        }
        String registerUri = "/rest/public/register";

        DateTimeFormatter timeStampFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timestamp = LocalDateTime.now().format(timeStampFormatter);
        String username = String.format("testuser%s@qa.com", timestamp);
        String password = getProp("password");
        String registerData = String.format("{\n" +
                "  \"email\": \"%s\",\n" +
                "  \"incomingUrl\": false,\n" +
                "  \"clickId\": \"\",\n" +
                "  \"investorType\": \"PRIVATE_PERSON\",\n" +
                "  \"password\": \"%s\",\n" +
                "  \"phoneNumber\": \"%s\",\n" +
                "  \"phonePrefix\": \"+371\",\n" +
                "  \"recaptchaToken\": \"\",\n" +
                "  \"referrerUrl\": false,\n" +
                "  \"sendEmailNotifications\": false,\n" +
                "  \"invitationFromUuid\": null" +
                "}", username, password, timestamp);

        Response registerResponse1 = given()
                .contentType("application/vnd.com.swaper.v1+json")
                .body(registerData)
                .when()
                .post(registerUri);

        registerResponse1
                .then()
                .statusCode(200) // Expect HTTP 200 OK
                .body("status", equalTo("REGISTERED"))
                .body("accountBalance", equalTo(0.0f))
                .extract().response();

        String successInfo = String.format("Registered %s, id=%s, status=%s, accountBalance=%s"
                , registerResponse1.path("username")
                , registerResponse1.path("id")
                , registerResponse1.path("status")
                , registerResponse1.path("accountBalance"));
        log.info(successInfo);
        testLogout(testInfo);
        doLogin(username, password);
    }

    @Test
    @Order(3)
    public void testOpeningBalance() {
        String accountEntriesUri = "/rest/public/profile/account-entries";
        String from = LocalDate.now().minusDays(30).format(DateTimeFormatter.ISO_DATE);
        String to = LocalDate.now().format(DateTimeFormatter.ISO_DATE);

        String payload = String.format("{\"bookingDateFrom\": \"%s\",\"bookingDateTo\": \"%s\"}", from, to);

        // Use session to call account entries endpoint
        Response balanceResponse = given()
                .header("X-Xsrf-Token", csrfToken)
                .cookies(cookies)
                .contentType("application/json")
                .body(payload)
                .post(accountEntriesUri)
                .then()
                .statusCode(200)
                .extract().response();

        // Extract openingBalance and validate
        Float openingBalance = balanceResponse.path("openingBalance");
        WandooAssert.assertEquals(log, 0.0f, openingBalance
                , "Opening balance must be 0.00");

    }

    private Response doLogin(String username, String password) {
        String loginUri = "/rest/public/login";

        // Prepare login payload
        String loginPayload = String.format("{\"name\": \"%s\",\"password\": \"%s\"}", username, password);

        // Send POST request to login
        log.info("Attempting to login as {}", username);
        Response loginResponse = given()
                .contentType("application/json")
                .body(loginPayload)
                .when()
                .post(loginUri)
                .then()
                .extract().response();

        // Print full response for debugging
        log.debug("LoginResponse: {}", loginResponse.asString());

        if (loginResponse.statusCode() == 200) {
            cookies = loginResponse.detailedCookies();
            csrfToken = loginResponse.header("_csrf");
        } else {
            log.info("Login didn't go as expected: {}", loginResponse.asString());
        }

        return loginResponse;
    }

    @Test
    @Order(4)
    void testLogout(TestInfo testInfo) {
        Method method = testInfo.getTestMethod().orElse(null);
        log.info("'logout' called from {}", method != null ? method.getName() : "null");
        Response logoutResponse = given()
                .cookies(cookies)
                .header("X-Xsrf-Token", csrfToken)
                .when()
                .post("/rest/public/logout")
                .then()
                .statusCode(200)
                .extract().response();

        // Step 3: Validate logout response
        String message = logoutResponse.jsonPath().getString("message");
        WandooAssert.assertEquals(log, "Logout successful", message, "Check 'logout' response message");
        WandooAssert.assertTrue(log, logoutResponse.jsonPath().getList("errors").isEmpty(), "Expected no errors on logout");
    }

}
