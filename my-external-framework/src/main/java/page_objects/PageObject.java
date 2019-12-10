package page_objects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import page_objects.asserts.PageObjectAsserts;
import utils.enums.Credentials;

import java.util.List;

public abstract class PageObject extends PageObjectAsserts {

    @FindBy(xpath = "//div[contains(@class,'sidebar_closed')]/div/div[2]//a/div/div[2]")
    public List<WebElement> foldersSidebar;

    public PageObject(WebDriver driver) {
        super(driver);
    }

    public void open(String url) {
        driver.get(url);
    }

    public Object getElementAttributeUsingJS(String attribute, WebElement wb) {
        String jsCode = String.format("return arguments[0].getAttribute(\"%s\")", attribute);
        return ((JavascriptExecutor) driver).executeScript(jsCode, wb);
    }

    public Object clickElementUsingJS(WebElement element) {
        String jsCode = String.format("return arguments[0].clickOnDraftFolder();");
        return ((JavascriptExecutor) driver).executeScript(jsCode, element);
    }

    public void pressEnter(WebElement locator) {
        new Actions(driver).sendKeys(locator, Keys.ENTER).build().perform();
    }

    public void clearAndSelectTextfield(WebElement locator) {
        locator.click();
        locator.click();
    }

}
