package ru.netology.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.testUtils.Card;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement header = $("[data-test-id=dashboard");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private SelenideElement amountInput = $("[data-test-id=amount] input");
    private SelenideElement cardFromInput = $("[data-test-id=from] input");
    private ElementsCollection liElements = $$("[class=list__item]");

    public DashboardPage() {header.shouldBe(Condition.visible);}

    public DashboardPage transferFromCardToCard(Card fromCard, Card toCard, int amount) {
        liElements.findBy(text(toCard.getViewedNum())).find("[data-test-id=action-deposit]").click();
        amountInput.shouldBe(Condition.visible);
        amountInput.sendKeys(Integer.toString(amount));
        cardFromInput.sendKeys(fromCard.getNumber());
        transferButton.click();
        return new DashboardPage();
    }

    public void checkBalance(Card card) {
        String balanceInfo = "**** **** **** %s, баланс: %s р.";
        liElements.findBy(text(card.getViewedNum())).shouldHave(text(String.format(balanceInfo, card.getViewedNum(), card.getBalance())));
    }
}
