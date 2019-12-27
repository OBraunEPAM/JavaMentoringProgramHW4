package BDD;

import config.Setup;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import static org.openqa.selenium.remote.BrowserType.CHROME;

@CucumberOptions(
        strict = true,
        features = "src/test/resources/featureFiles/SentEmailTest.feature"
)
public class Runner extends AbstractTestNGCucumberTests {

    protected Setup setup = new Setup();

    @BeforeClass(alwaysRun = true)
    public void driverSetUp() throws Exception {
        setup.setupDriver(CHROME);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        setup.getDriver().quit();
    }
}
