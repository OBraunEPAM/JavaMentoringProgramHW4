package config;

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

import static utils.enums.SetupEnums.REMOTE_WEB_DRIVER;
import static utils.enums.SetupEnums.WEB_DRIVER;
import static java.lang.System.setProperty;

public class Setup {

    private static WebDriver driver = null;
    private static WebDriverWait driverWait = null;

    protected WebDriver setupDriver(SetupEnums type) throws NullPointerException {
        if (type.equals(REMOTE_WEB_DRIVER)) {
            setRemoteDriver();
        } else if (type.equals(WEB_DRIVER)) {
            if (driver == null) {
                setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
                driver = new ChromeDriver();
            }
            driver.manage().window().maximize();
        } else {
            throw new NullPointerException("There is no such driver type");
        }
        setDriverWait();
        return driver;
    }

    protected <T> T initPage(Class<T> page) {
        T preparedDriver = null;
        try {
            if (driver == null) setupDriver(WEB_DRIVER);
            preparedDriver = PageFactory.initElements(driver, page);
        } catch (NullPointerException ex) {
            ex.getMessage();
        }
        return preparedDriver;
    }

    private void setDriverWait() {
        driverWait = new WebDriverWait(driver, 10);
    }

    protected WebDriver getDriver() throws NullPointerException {
        if (driver == null) setupDriver(WEB_DRIVER);
        return driver;
    }

    protected WebDriverWait getDriverWait() {
        if (driverWait == null) setDriverWait();
        return driverWait;
    }

    private void setRemoteDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        options.addArguments("start-maximized");
        try {
            if (driver == null) driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnreachableBrowserException e) {
            System.out.println("Selenium grid must be running");
        }
    }
}
