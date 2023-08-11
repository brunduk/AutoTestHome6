package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.DataHelper;
import ru.netology.page.DashboardPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TransferPage {

    public SelenideElement head = $(byText("Пополнение карты"));
    public SelenideElement amount = $("[data-test-id=amount] input");
    public SelenideElement from = $("[data-test-id=from] input");
    public SelenideElement transferButton = $("[data-test-id=action-transfer]");
    public SelenideElement cancelButton = $("[data-test-id=cation-cancel]");
    public SelenideElement errorMessage = $("[data-test-id=error-notification]");

    public TransferPage() {
        head.shouldBe(visible);
    }


    public void makeTransfer(String transferredAmount, DataHelper.CardInfo cardInfo) {
        amount.setValue(transferredAmount);
        from.setValue(cardInfo.getCardNumber());
        transferButton.click();
    }

    public DashboardPage makeValidTransfer(String transferredAmount, DataHelper.CardInfo cardInfo) {
        makeTransfer(transferredAmount, cardInfo);
        return new DashboardPage();
    }

    public void findError(String expectedText) {
        errorMessage.shouldHave(exactText(expectedText), Duration.ofSeconds(15)).shouldBe(visible);
    }
}
