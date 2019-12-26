package config;

import factory_method.ChromeDriverCreator;
import factory_method.FirefoxDriverCreator;
import factory_method.WebDriverCreator;
import org.openqa.selenium.remote.BrowserType;
import utils.enums.SetupEnums;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.net.URL;

import static org.openqa.selenium.remote.BrowserType.CHROME;
import static org.openqa.selenium.remote.BrowserType.FIREFOX;
import static utils.enums.SetupEnums.REMOTE_WEB_DRIVER;
import static utils.enums.SetupEnums.WEB_DRIVER;
import static java.lang.System.setProperty;

public class Setup {

    private static WebDriver driver = null;
    private static WebDriverWait driverWait = null;

    private static final String noSuchBrowserMessage = "There is no such driver type";

    public WebDriver setupDriver(String browser) throws Exception {
        if (browser.equals(CHROME)) {
            if (driver == null) {
                driver = new ChromeDriverCreator().createWebDriver();
            }
        } else if (browser.equals(FIREFOX)) {
                driver = new FirefoxDriverCreator().createWebDriver();
        } else {
            throw new Exception(noSuchBrowserMessage);
        }
        driver.manage().window().maximize();
        return driver;
    }

    public <T> T initPage(Class<T> page) {
        T preparedDriver = null;
        try {
            preparedDriver = PageFactory.initElements(driver, page);
        } catch (NullPointerException ex) {
            ex.getMessage();
        }
        return preparedDriver;
    }

    private void setDriverWait() {
        driverWait = new WebDriverWait(driver, 10);
    }

    public WebDriver getDriver() throws Exception {
        if (driver == null) setupDriver(CHROME);
        return driver;
    }

    public WebDriverWait getDriverWait() {
        if (driverWait == null) setDriverWait();
        return driverWait;
    }
}
