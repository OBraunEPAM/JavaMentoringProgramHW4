package common;

import PageObjects.MailRuCommonPage;
import enums.MailRuData;
import enums.SetupEnums;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

import static enums.MailRuData.*;
import static enums.SetupEnums.REMOTE_WEB_DRIVER;
import static enums.SetupEnums.WEB_DRIVER;
import static java.lang.System.setProperty;

public class SetupClass {

    protected static WebDriver driver;
    protected static WebDriverWait driverWait;
    protected MailRuCommonPage commonPage;

    @BeforeSuite(alwaysRun = true)
    public void setUpDriverDirectory() {
        setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    protected void setupDriver(SetupEnums type) throws Exception {
        if (type.equals(REMOTE_WEB_DRIVER)) {
            setRemoteDriver();
        } else if (type.equals(WEB_DRIVER)) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        } else {
            throw new Exception("There is no such driver type");
        }
        setDriverWait();
        commonPage = PageFactory.initElements(driver, MailRuCommonPage.class);
    }

    private void setDriverWait() {
        driverWait = new WebDriverWait(driver, 10);
    }

    private void setRemoteDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        options.addArguments("start-maximized");
        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnreachableBrowserException e) {
            System.out.println("Selenium grid must be running");
        }
    }
}
