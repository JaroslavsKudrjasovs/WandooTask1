package com.wandoo.web.selenium.pages.registration;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.wandoo.web.selenium.pages.ParentPage;

import static com.codeborne.selenide.Selenide.*;

public class FinancialDataPage extends ParentPage<FinancialDataPage> {
    private final SelenideElement submitButton = $x("//div[contains(@class,'button')]//span[contains(text(), 'Submit')]");

    public FinancialDataPage() {
        relativeUrl = "/en/sign-up/financial-data";
    }

    public FinancialDataPage selectAllFirstOptions() {
        ElementsCollection reactControls = $$(".react-select__control");
        // consider to wait for something specific for FinancialDataPage
        reactControls.shouldBe(CollectionCondition.sizeGreaterThan(1));
        log.debug("reactControls size = {}", reactControls.size());
        reactControls.forEach(rControl -> {
            rControl.click();
            ElementsCollection opts = $$(".react-select__option");
            opts.shouldBe(CollectionCondition.sizeGreaterThan(0));
            log.debug("opts size = {}", opts.size());
            opts.first().click();
        });
        return this;
    }

    public FinancialDataPage checkPoliticalExposedPerson(boolean isPep) {
        $(String.format("input[name='isPep'][value='%s']", isPep)).click();
        return this;
    }

    public FinancialDataPage checkBeneficialOwner(boolean isBeneficialOwner) {
        $(String.format("input[name='isBeneficialOwner'][value='%s']", isBeneficialOwner)).click();
        return this;
    }

    public void clickSubmitButton() {
        submitButton.click();
    }
}
