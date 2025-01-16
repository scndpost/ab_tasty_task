package org.abtasty.pages.login;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.abtasty.pages.BaseUIPage;

import static com.codeborne.selenide.Selenide.$;

public class MfaPage extends BaseUIPage {

    private final SelenideElement mfaCodeInput = $("#mfaCode");
    private final SelenideElement okButton = $("#mfaOk");

    @Step
    public MfaPage insertMfaCode(String mfaCode) {
        mfaCodeInput
                .shouldBe(Condition.interactable)
                .setValue(mfaCode);
        return this;
    }

    @Step
    public ApplicationPage clickOkButton() {
        okButton
                .shouldBe(Condition.clickable)
                .click();
        return new ApplicationPage();
    }

}
