package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return page(VerificationPage.class);
    }

    public void invalidLogin() {
        for (int i = 0; i < 5; i++) {
            loginField.doubleClick();
            loginField.sendKeys(Keys.BACK_SPACE);
            loginField.setValue(DataHelper.generateAuthInfo().getLogin());
            passwordField.doubleClick();
            passwordField.sendKeys(Keys.BACK_SPACE);
            passwordField.setValue(DataHelper.generateAuthInfo().getPassword());
            loginButton.click();
        }
        $(withText("Неверно")).waitUntil(visible, 15000);
    }
}
