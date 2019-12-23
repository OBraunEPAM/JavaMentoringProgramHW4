package factory_method;

import org.openqa.selenium.firefox.FirefoxDriver;

import static java.lang.System.setProperty;

public class FirefoxDriverCreator implements WebDriverCreator {

    @Override
    public FirefoxDriver createWebDriver() {
        setProperty("webdriver.chrome.driver", "my-external-framework\\src\\main\\resources\\geckodriver.exe");
        return new FirefoxDriver();
    }

}
