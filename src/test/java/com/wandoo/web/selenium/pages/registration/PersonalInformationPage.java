package com.wandoo.web.selenium.pages.registration;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.wandoo.testutils.UserData;
import com.wandoo.web.selenium.pages.ParentPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;

public class PersonalInformationPage extends ParentPage<PersonalInformationPage> {
    private final SelenideElement firstNameInput = $(byName("firstName")).shouldBe(Condition.editable, Duration.ofSeconds(20));
    private final SelenideElement lastNameInput = $(byName("lastName"));
    private final SelenideElement birthDateInput = $(byName("birthDate"));
    private final SelenideElement addressLine1Input = $(byName("addressLine1"));
    private final SelenideElement identificationDocumentNumberInput = $(byName("identificationDocumentNumber"));
    private final SelenideElement identificationDocumentExpirationDateInput = $(byName("identificationDocumentExpirationDate"));
    private final SelenideElement nextButton = $x("//div[contains(@class,'button')]//span[contains(text(), 'Next')]");

    // this doesn't work
    //private final SelenideElement countryField = $x("//div[text()='Country of Residence']/following-sibling::div//input");
    private final SelenideElement countrySelectionContainer = $(".react-select__control");
    private final SelenideElement countryHiddenInput = $(".react-select__input-container input");

    public PersonalInformationPage() {
        relativeUrl = "/en/sign-up/personal-information";
    }

    public void fillInPersonalInformationAndClickNext(UserData userData) {
        firstNameInput.setValue(userData.getFirstName());
        lastNameInput.setValue(userData.getLastName());
        // .setValue() didn't work. Work-around:
        // executeJavaScript("arguments[0].value='01-01-2000';", birthDate);
        birthDateInput.shouldBe(visible).click();
        birthDateInput.sendKeys(userData.getBirthDate());
        addressLine1Input.setValue("1 Liberty street, Riga, Latvia");
        identificationDocumentNumberInput.setValue(userData.getPassportNumber());
        // .setValue() didn't work. Work-around:
        // executeJavaScript("arguments[0].value='01-01-3000';", identificationDocumentExpirationDate);
        identificationDocumentExpirationDateInput.shouldBe(visible).click();
        identificationDocumentExpirationDateInput.sendKeys(userData.getPassportExpDate());

        // Step 1: Open the dropdown
        countrySelectionContainer.click();
        // Step 2: Type and select
        countryHiddenInput.setValue(userData.getCountry()).pressEnter();

        nextButton.click();
    }
}
