package config;

import factory_method.ChromeDriverCreator;
import factory_method.FirefoxDriverCreator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;

import static org.openqa.selenium.remote.BrowserType.CHROME;
import static org.openqa.selenium.remote.BrowserType.FIREFOX;

public class Setup {

    private static WebDriver driver = null;
    private static WebDriverWait driverWait = null;

    private static final String noSuchBrowserMessage = "There is no such driver type";

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    protected WebDriver setupDriver(String browser) throws Exception {
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

    protected <T> T initPage(Class<T> page) {
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
