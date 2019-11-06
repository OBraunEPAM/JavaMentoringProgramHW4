package common;

import PageObjects.MailRuCommonPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import static java.lang.System.setProperty;

public class SetupClass {

    protected WebDriver driver;
    protected WebDriverWait driverWait;
    protected MailRuCommonPage commonPage;

    @BeforeSuite(alwaysRun = true)
    public void setUpDriverDirectory() {
        setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
    }

    @BeforeClass(alwaysRun = true)
    public void setUpDriver() {
        driver = new ChromeDriver();
        driverWait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        commonPage = PageFactory.initElements(driver, MailRuCommonPage.class);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
