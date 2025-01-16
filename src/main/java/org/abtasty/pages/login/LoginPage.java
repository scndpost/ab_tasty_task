package org.abtasty.pages.login;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.abtasty.pages.BaseUIPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage extends BaseUIPage {

    private final SelenideElement emailInput = $("#email");
    private final SelenideElement passwordInput = $("#password");
    private final SelenideElement signInButton = $("#signInButton");
    private final SelenideElement signInButtonLoadingState =
            $x("//button[@id='signInButton' and ./*[contains(@class, 'LoadingIndicatorThreeDots')]]");
    private final SelenideElement loginErrorMessage = $("#loginErrorMessage");

    @Step("Open 'Login' page")
    public static LoginPage open() {
        return Selenide.open(BASE_URL + "/login", LoginPage.class);
    }

    @Step
    public LoginPage insertEmail(String email) {
        emailInput
                .shouldBe(Condition.editable)
                .setValue(email);
        return this;
    }

    @Step
    public LoginPage insertPassword(String password) {
        passwordInput
                .shouldBe(Condition.editable)
                .setValue(password);
        return this;
    }

    @Step
    public ApplicationPage clickSignInButton() {
        signInButton
                .shouldBe(Condition.clickable)
                .click();
        return new ApplicationPage();
    }

    @Step
    public MfaPage clickSignInButtonMfaUser() {
        signInButton
                .shouldBe(Condition.clickable)
                .click();
        waitUntilSignInProcessing();
        return new MfaPage();
    }

    @Step
    public LoginPage clickSignInButtonErrorFlow() {
        signInButton
                .shouldBe(Condition.clickable)
                .click();
        return this;
    }

    @Step
    public LoginPage verifyLoginErrorMessageIsPresent() {
        loginErrorMessage
                .shouldBe(Condition.visible);
        return this;
    }

    @Step
    private void waitUntilSignInProcessing() {
        signInButtonLoadingState
                .shouldNotBe(Condition.visible);
    }

}
