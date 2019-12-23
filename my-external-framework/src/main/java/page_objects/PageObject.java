package page_objects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_objects.asserts.PageObjectAsserts;

public class PageObject extends PageObjectAsserts {

    public PageObject(WebDriver driver) {
        super(driver);
    }

    protected void open(String url) {
        driver.get(url);
    }

    Object getElementAttributeUsingJS(String attribute, WebElement wb) {
        String jsCode = String.format("return arguments[0].getAttribute(\"%s\")", attribute);
        return ((JavascriptExecutor) driver).executeScript(jsCode, wb);
    }

    Object clickElementUsingJS(WebElement element) {
        String jsCode = String.format("return arguments[0].click();");
        return ((JavascriptExecutor) driver).executeScript(jsCode, element);
    }
}
