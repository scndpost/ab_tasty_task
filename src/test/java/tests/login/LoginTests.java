package tests.login;

import org.abtasty.configuration.ConfigManager;
import org.abtasty.pages.login.LoginPage;
import org.abtasty.pages.login.MfaPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.BaseUiTest;

public class LoginTests extends BaseUiTest {

    @Test(description = "Login with valid email/password data (MFA off) successful")
    public void validEmailPasswordLoginTest() {
        LoginPage.open()
                .insertEmail(ConfigManager.config.userEmail())
                .insertPassword(ConfigManager.config.userPassword())
                .clickSignInButton()
                .verifyApplicationPageIsOpened();
    }

    @Test(description = "Login with invalid email/password data shows up an login error message",
            dataProvider = "invalidCredentials")
    public void invalidEmailPasswordLoginTest(String email, String password) {
        LoginPage.open()
                .insertEmail(email)
                .insertPassword(password)
                .clickSignInButtonErrorFlow()
                .verifyLoginErrorMessageIsPresent();
    }

    @Test(description = "Login with valid email/password data (MFA on) successful")
    public void validEmailPasswordWithMfaLoginTest() {
        MfaPage mfaPage = LoginPage.open()
                .insertEmail(ConfigManager.config.userEmail())
                .insertPassword(ConfigManager.config.userPassword())
                .clickSignInButtonMfaUser();

        String mfaCode = phoneClient.performMagicAndGetPhoneMfaCodeHere();

        mfaPage
                .insertMfaCode(mfaCode)
                .clickOkButton()
                .verifyApplicationPageIsOpened();
    }

    @SuppressWarnings("deprecation")
    @DataProvider(parallel = true)
    private Object[][] invalidCredentials() {
        return new Object[][]{
                {ConfigManager.config.userEmail(), RandomStringUtils.randomAlphanumeric(12)},
                {"%s@test.com".formatted(RandomStringUtils.randomAlphanumeric(6)), ConfigManager.config.userPassword()}
        };
    }

}