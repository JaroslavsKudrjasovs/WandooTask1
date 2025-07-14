package com.wandoo.web.selenium;

import com.wandoo.testutils.UserData;
import com.wandoo.web.selenium.pages.DashboardPage;
import com.wandoo.web.selenium.pages.LoginPage;
import com.wandoo.web.selenium.pages.registration.FinancialDataPage;
import com.wandoo.web.selenium.pages.registration.PersonalInformationPage;
import com.wandoo.web.selenium.pages.registration.RegistrationPage;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

@Test
public class BalanceCheckTest extends BaseWebTest {
    boolean skipRegistrationTest = false;

    @Test
    public void testBalanceCheckWithFallbackRegistration() {
        LoginPage loginPage = new LoginPage().openPage();
        loginPage.login(getProp("username"), getProp("password"));
        DashboardPage dashboardPage = new DashboardPage();
        Assert.assertEquals(dashboardPage.getBalance(), 0.0f, "checking balance");
        //WandooAssert.assertEquals(log, 0.0f, dashboardPage.getBalance(), "Checking balance");
        skipRegistrationTest = true;
    }

    @Test(dependsOnMethods = "testBalanceCheckWithFallbackRegistration")
    public void testARegisterNewUser() {
        if (skipRegistrationTest) {
            throw new SkipException("SKIPPING...");
        }
        RegistrationPage registrationPage = new RegistrationPage().openPage();
        UserData userData = new UserData();
        registrationPage.registerUser(userData);

        PersonalInformationPage personalInformationPage = new PersonalInformationPage();
        personalInformationPage.fillInPersonalInformationAndClickNext(userData);

        FinancialDataPage financialDataPage = new FinancialDataPage();
        financialDataPage
                .selectAllFirstOptions()
                .checkPoliticalExposedPerson(false)
                .checkBeneficialOwner(true)
                .clickSubmitButton();
        financialDataPage.clickLogoutButton();
        LoginPage loginPage = new LoginPage();
        loginPage.clickLoginButton();
        loginPage.login(userData.getEmail(), userData.getPassword());
        DashboardPage dashboardPage = new DashboardPage();
        //WandooAssert.assertEquals(log, 0.0f, dashboardPage.getBalance(), "Checking balance");
        Assert.assertEquals(dashboardPage.getBalance(), 0.0f, "checking balance");

    }
}
