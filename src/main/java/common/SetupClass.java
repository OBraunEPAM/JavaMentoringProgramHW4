import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static java.lang.System.setProperty;

public class SetupClass {

    protected WebDriver driver;
    protected WebDriverWait driverWait;

    @BeforeClass(alwaysRun = true)
    public void setUpDriverDirectory() {
        setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
    }

    @BeforeMethod(alwaysRun = true)
    public void setUpDriver() {
        driver = new ChromeDriver();
        driverWait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
