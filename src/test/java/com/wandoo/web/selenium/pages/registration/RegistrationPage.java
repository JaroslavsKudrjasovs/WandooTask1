package com.wandoo.web.selenium.pages.registration;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.wandoo.testutils.UserData;
import com.wandoo.web.selenium.pages.ParentPage;

import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage extends ParentPage<RegistrationPage> {

    private final SelenideElement emailInput = $("input[type='email']");
    private final SelenideElement passwordInput = $("input[type='password']");
    // this one is clickable element
    private final SelenideElement phonePrefixElement =
            $x("//label[text()='Phone']/following-sibling::div[contains(@class, 'input-container')]//div[contains(@class, 'dropdown')]");
    private final SelenideElement phoneInput = $("input[placeholder='Phone']");
    private final SelenideElement nextButton = $x("//div[contains(@class,'button')]//span[contains(text(), 'Next')]");

    public RegistrationPage() {
        relativeUrl = "/en/sign-up";
    }

    public void registerUser(UserData userData) {
        // this one is also clickable but represents specific selection like 'Latvia +(+371)'
        SelenideElement phonePrefixSelection =
                $x(String.format("//div[contains(@class,'item')]//div[contains(text(), '%s')]", userData.getPhonePrefix()));

        emailInput.setValue(userData.getEmail());
        passwordInput.setValue(userData.getPassword());
        phoneInput.setValue(userData.getPhone());
        phonePrefixElement.click();
        phonePrefixSelection.click();

        ElementsCollection agreementCheckBoxes = $$("div.agreement-box div.checkbox input[type='checkbox']");
        agreementCheckBoxes.forEach(SelenideElement::click);

        nextButton.click();
    }
}

