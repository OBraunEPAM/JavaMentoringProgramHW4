package page_objects.asserts;

import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertTrue;


public class PageObjectAsserts {

    protected WebDriver driver;

    public PageObjectAsserts(WebDriver driver) {
        this.driver = driver;
    }

    protected <E extends Enum<E>> void checkTitle(Enum<E> page) {
        assertTrue(driver.getTitle().contains(page.toString()));
    }
}
