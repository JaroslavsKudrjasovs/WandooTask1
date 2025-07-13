package com.wandoo.web.selenium.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage extends ParentPge {

    public LoginPage openPage() {
        open("https://swaper.com/en/login");
        SelenideElement acceptAllButton = $("button#CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll");
        acceptAllButton.click();
        return this;
    }

    public void login(String email, String password) {
        SelenideElement emailInput = $("input[name='email']");
        SelenideElement passwordInput = $("input[name='password']");
        SelenideElement loginButton = $x("//div[@class='title']/span[text()='Log In']");

        log.info("Attempting to login as {}", email);
        emailInput.setValue(email);
        passwordInput.setValue(password);
        loginButton.click();
    }

}
