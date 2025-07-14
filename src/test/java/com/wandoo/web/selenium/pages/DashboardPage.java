package com.wandoo.web.selenium.pages;


import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage extends ParentPage<DashboardPage>{

    private final SelenideElement balanceElement = $("a.menu-item#add-funds");

    public float getBalance() {
        String balanceString = balanceElement.getText().replaceAll("[ €,]", "");
        return Float.parseFloat(balanceString);
    }
}

