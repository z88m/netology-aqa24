package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.pages.DashboardPage;
import ru.netology.pages.LoginPage;
import ru.netology.testUtils.Card;
import ru.netology.testUtils.DataHelper;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {

    private String testSite = "http://localhost:9999";
    private int amount = 1;

    private Card firstCard = new Card("5559 0000 0000 0001","0001",10000);
    private Card secondCard = new Card("5559 0000 0000 0002", "0002", 10000);

    @Test
    @DisplayName("Перевод с первой карты на вторую")
    void transferFromFirstCardToSecondCard() {
        open(testSite);
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        DashboardPage dashboardPage = verificationPage.validVerify(verificationCode);

        dashboardPage.transferFromCardToCard(firstCard, secondCard, amount);
        Card.transferFromCardToCard(firstCard, secondCard, amount);
        dashboardPage.checkBalance(firstCard);
        dashboardPage.checkBalance(secondCard);
    }
}