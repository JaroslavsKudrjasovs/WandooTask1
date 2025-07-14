package com.wandoo.web.selenium.pages;

import com.codeborne.selenide.SelenideElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.*;

public class ParentPage<T extends ParentPage<T>> {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    protected String relativeUrl;
    protected SelenideElement logoutButton = $("div#logout");
    protected SelenideElement loginButton = $("a#login");

    @SuppressWarnings("unchecked")
    public T openPage() {
        open(relativeUrl);
        return (T) this;
    }

    public void clickLogoutButton() {
        logoutButton.click();
        refresh();
    }

    public void clickLoginButton() {
        loginButton.click();
        refresh();
    }
}
