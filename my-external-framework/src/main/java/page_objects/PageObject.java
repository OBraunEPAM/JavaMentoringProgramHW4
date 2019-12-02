package page_objects;

import org.openqa.selenium.WebDriver;
import page_objects.asserts.PageObjectAsserts;

public class PageObject extends PageObjectAsserts {

    public PageObject(WebDriver driver) {
        super(driver);
    }

    protected void open(String url) {
        driver.get(url);
    }
}
