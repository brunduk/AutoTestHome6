package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    public SelenideElement heading = $("[data-test-id=dashboard]");
    public ElementsCollection listCard = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";


    public DashboardPage() {
        heading.shouldBe(visible);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public int getCardBalance(DataHelper.CardInfo cardInfo) {
        var text = listCard.findBy(text(cardInfo.getCardNumber().substring(15))).getText();
        return extractBalance(text);
    }

    public int getCardBalance(int index) {
        var text = listCard.get(index).getText();
        return extractBalance(text);
    }

    public TransferPage selectCardToTransfer(DataHelper.CardInfo cardInfo) {
        listCard.findBy(attribute("data-test-id", cardInfo.getTestId())).$(".button").click();
        return new TransferPage();
    }
}

