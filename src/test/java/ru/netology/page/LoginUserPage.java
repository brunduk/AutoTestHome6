package ru.netology.page;

import com.codeborne.selenide.Condition;
import ru.netology.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginUserPage {
    public VerificationPage validLogin(DataHelper.AutoInfo info) {
        $("[data-test-id='login'] input").setValue(info.getLogin());
        $("[data-test-id='password'] input").setValue(info.getPassword());
        $("[data-test-id='action-login']").click();
        return new VerificationPage();

    }
}
