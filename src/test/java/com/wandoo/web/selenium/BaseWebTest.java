
package com.wandoo.web.selenium;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.wandoo.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
@Test
public class BaseWebTest extends BaseTest {
    @BeforeClass
    public void setUp() {
        Configuration.timeout = 6000; // 6 seconds
        Configuration.baseUrl = "https://swaper.com";
        open("");
        SelenideElement acceptAllButton = $("button#CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll");
        acceptAllButton.click();
        getWebDriver().manage().window().maximize();
    }
}
