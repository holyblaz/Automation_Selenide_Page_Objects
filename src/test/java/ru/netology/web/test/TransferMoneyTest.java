package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPageV2;
import ru.netology.web.page.TransferPage;

import static com.codeborne.selenide.Selenide.open;

public class TransferMoneyTest {
    @BeforeEach
    void shouldLogin() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV2();
        var autoInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(autoInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(autoInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldTransferMoneyFromSecondToFirstCard() {
        int value = 100;
        String cardNumber = String.valueOf(DataHelper.getSecondCardNumber());
        val dashboardPage = new DashboardPage();
        var firstCardBalance = dashboardPage.getFirstCardBalance();
        var secondCardBalance = dashboardPage.getSecondCardBalance();
        dashboardPage.transferButtonSecondToFirst();
        val transferPage = new TransferPage();
        transferPage.importTransferData(value, cardNumber);
        var firstCardBalance1 = dashboardPage.getFirstCardBalance();
        var secondCardBalance1 = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(secondCardBalance - value, secondCardBalance1);
        Assertions.assertEquals(firstCardBalance + value, firstCardBalance1);
    }

    @Test
    void shouldTransferMoneyFirstToSecondCard() {
        int value = 100;
        String cardNumber = String.valueOf(DataHelper.getFirstCardNumber());
        val dashboardPage = new DashboardPage();
        var firstCardBalance = dashboardPage.getFirstCardBalance();
        var secondCardBalance = dashboardPage.getSecondCardBalance();
        dashboardPage.transferButtonFirstToSecond();
        val transferPage = new TransferPage();
        transferPage.importTransferData(value, cardNumber);
        var firstCardBalance1 = dashboardPage.getFirstCardBalance();
        var secondCardBalance1 = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(firstCardBalance - value, firstCardBalance1);
        Assertions.assertEquals(secondCardBalance + value, secondCardBalance1);
    }

    @Test
    @DisplayName("Deposit of card is 0 rubles at the moment")
    void shouldNotTransferMoneyFromSecondToFirstAfterLimit() {
        int value = 100000;
        String cardNumber = String.valueOf(DataHelper.getSecondCardNumber());
        val dashboardPage = new DashboardPage();
        var secondCardBalance = dashboardPage.getSecondCardBalance();
        var firstCardBalance = dashboardPage.getFirstCardBalance();
        dashboardPage.transferButtonSecondToFirst();
        val transferPage = new TransferPage();
        transferPage.importTransferData(value, cardNumber);
        var firstCardBalance1 = dashboardPage.getFirstCardBalance();
        var secondCardBalance1 = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(secondCardBalance - value, secondCardBalance1);
        Assertions.assertEquals(firstCardBalance + value, firstCardBalance1);
    }
}
