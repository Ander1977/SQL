package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;
import ru.netology.page.PersonalArea;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {
    @AfterAll
    public static void PostConditions() throws SQLException {
        DataHelper.ClearAuthCodesTable();
    }

    @Test
    public void openPersonalArea() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getValidAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        DataHelper.VerificationCode verificationCode = null;
        try {
            verificationCode = DataHelper.getVerificationCode(authInfo);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        PersonalArea page = verificationPage.validVerify(verificationCode);
    }

    @Test
    public void shouldNotBlockedIfPasswordNotCorrect() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        loginPage.invalidLogin();
    }
}

