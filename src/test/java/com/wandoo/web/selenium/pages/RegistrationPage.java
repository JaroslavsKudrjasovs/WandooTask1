package com.wandoo.web.selenium.pages;

import com.codeborne.selenide.SelenideElement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    private final SelenideElement emailInput = $("input[type='email']");
    private final SelenideElement passwordInput = $("input[type='password']");

    private final SelenideElement phonePrefixElement =
            $x("//label[text()='Phone']/following-sibling::div[contains(@class, 'input-container')]//div[contains(@class, 'dropdown')]");

    private final SelenideElement phoneInput = $("input[placeholder='Phone']");
    private final SelenideElement nextButton = $x("//div[contains(@class,'button')]//span[contains(text(), 'Next')]");

    public RegistrationPage openPage() {
        open("https://swaper.com/sign-up");
        return this;
    }

    public String registerUser(String email, String password, String phonePrefix, String phoneNumber) {
        SelenideElement phonePrefixSelection =
                $x(String.format("//div[contains(@class,'item')]//div[contains(text(), '%s')]", phonePrefix));

        emailInput.setValue(email);
        passwordInput.setValue(password);
        phoneInput.setValue(phoneNumber);
        phonePrefixElement.click();
        phonePrefixSelection.click();
        nextButton.click();

        return email;
    }

    private String generateUniqueEmail() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        return "testuser_" + timestamp + "@qa.com";
    }
}

