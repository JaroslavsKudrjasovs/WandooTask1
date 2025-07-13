package com.wandoo.web.selenium;

import com.wandoo.BaseTest;
import com.wandoo.testutils.UserData;
import com.wandoo.testutils.WandooAssert;
import com.wandoo.web.selenium.pages.DashboardPage;
import com.wandoo.web.selenium.pages.LoginPage;
import com.wandoo.web.selenium.pages.RegistrationPage;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class BalanceCheckTest extends BaseTest {

    @Test
    public void testBalanceCheckWithFallbackRegistration() {
        LoginPage loginPage = new LoginPage().openPage();
        loginPage.login(getProp("username"), getProp("password"));
        DashboardPage dashboardPage = new DashboardPage();
        WandooAssert.assertEquals(log, 0.0f, dashboardPage.getBalance(), "Checking b`alance");
    }

    @Test
    public void test2() {
        log.info("ttest2");
        throw new SkipException("SKIPPING...");
    }

    @Test
    public void ttest3() {
        log.info("test3");
    }

    @Test
    public void testRegisterNewUser() {
        RegistrationPage registrationPage = new RegistrationPage().openPage();
        UserData userData = new UserData();
        registrationPage.registerUser(userData.getEmail(), userData.getPassword(), userData.getPhonePrefix(), userData.getPhone());
    }
}
