package factory_method;

import org.openqa.selenium.chrome.ChromeDriver;

import static java.lang.System.setProperty;

public class ChromeDriverCreator implements WebDriverCreator {

    @Override
    public ChromeDriver createWebDriver() {
        setProperty("webdriver.chrome.driver", "my-external-framework\\src\\main\\resources\\chromedriver.exe");
        return new ChromeDriver();
    }
}
