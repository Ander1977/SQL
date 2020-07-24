package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;
import ru.netology.page.PersonalArea;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {
    private LoginPage loginPage;

    @BeforeEach
    public void openPage() {
        loginPage = open("http://localhost:9999", LoginPage.class);
    }

    @AfterAll
    public static void postConditions() {
        DataHelper.clearAuthCodesTable();
    }

    @Test
    public void openPersonalArea() {
        val authInfo = DataHelper.getValidAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        PersonalArea page = verificationPage.validVerify(DataHelper.getVerificationCode(authInfo));
    }

    @Test
    public void shouldNotBlockedIfPasswordNotCorrect() {
        loginPage.invalidLogin();
    }
}

