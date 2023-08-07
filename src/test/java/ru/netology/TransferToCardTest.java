package ru.netology;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginUserPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.DataHelper.*;

public class TransferToCardTest {

    DashboardPage dashboardPage;

    @BeforeEach
    void setUp () {
        var loginPage = open("http://localhost:9999/", LoginUserPage.class);
        var autoInfo = getAutoInfo();
        var verificationPage = loginPage.validLogin(autoInfo);
        var verificationCode = getVerificationCode();
        dashboardPage = verificationPage.validVerify(verificationCode);

    }

    @Test
    void transferToCard01FromCard02() {
        var card01 = DataHelper.getCard01();
        var card02 = DataHelper.getCard02();
        var balanceCard01 = dashboardPage.getCardBalance(card01);
        var balanceCard02 = dashboardPage.getCardBalance(card02);
        var amountTransfer = generateValidAmount(balanceCard01);
        var expectedBalance01 = balanceCard01 - amountTransfer;
        var expectedBalance02 = balanceCard02 + amountTransfer;
        var transferPage = dashboardPage.selectCardToTransfer(card02);
        dashboardPage = transferPage.makeValidTransfer(String.valueOf(amountTransfer),card01);
        var actualBalance01 = dashboardPage.getCardBalance(card01);
        var actualBalance02 = dashboardPage.getCardBalance(card02);
        assertEquals(expectedBalance01,actualBalance01);
        assertEquals(expectedBalance02,actualBalance02);
    }

    @Test
    void transferInvalidAmount() {
        var card01 = DataHelper.getCard01();
        var card02 = DataHelper.getCard02();
        var balanceCard01 = dashboardPage.getCardBalance(card01);
        var balanceCard02 = dashboardPage.getCardBalance(card02);
        var amountTransfer = generateInvalidAmount(balanceCard02);
        var transferPage = dashboardPage.selectCardToTransfer(card01);
        transferPage.makeTransfer(String.valueOf(amountTransfer), card02);
        transferPage.findError("Ошибка");
        var actualBalance01 = dashboardPage.getCardBalance(card01);
        var actualBalance02 = dashboardPage.getCardBalance(card02);
        assertEquals(balanceCard01,actualBalance01);
        assertEquals(balanceCard02,actualBalance02);
    }
}
