package org.abtasty.pages.login;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.abtasty.pages.BaseUIPage;

import static com.codeborne.selenide.Selenide.$;

public class ApplicationPage extends BaseUIPage {

    @Step
    public void verifyApplicationPageIsOpened() {
        // Verify we are not on login or MFA pages at least 🙂
        Selenide.$("#signInButton").shouldNotBe(Condition.visible);
        Selenide.$("#mfaOk").shouldNotBe(Condition.visible);
    }
}
