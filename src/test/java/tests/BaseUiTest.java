package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.abtasty.configuration.ConfigManager;
import org.abtasty.utils.PhoneClient;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public abstract class BaseUiTest {

    protected static PhoneClient phoneClient;

    @BeforeSuite(alwaysRun = true)
    public static void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless=new");
        options.addArguments("--remote-allow-origins=*");

        Configuration.browserCapabilities = options;
        Configuration.pageLoadTimeout = 30000;
        Configuration.timeout = 10000;

        phoneClient = new PhoneClient(ConfigManager.config.userPhoneNumber());
    }

    @BeforeMethod(alwaysRun = true)
    public void addLogger() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterMethod(alwaysRun = true)
    public void closeWebDriver() {
        WebDriverRunner.clearBrowserCache();
        Selenide.closeWebDriver();
    }
}
