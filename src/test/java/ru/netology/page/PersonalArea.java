package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class PersonalArea {
    private SelenideElement heading = $("[data-test-id=dashboard]");

    public PersonalArea () {
        heading.waitUntil(Condition.visible, 15000);
    }
}