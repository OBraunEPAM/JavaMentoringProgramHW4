package page_objects;

import utils.enums.Credentials;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_objects.PageObject;

import static utils.enums.MailRuData.*;

public class MailRuCommonPage extends PageObject {

    WebDriverWait driverWait;

    @FindBy(xpath = "//input[@id='mailbox:login']")
    private WebElement usernameTextfield;

    @FindBy(xpath = "//input[@id='mailbox:password']")
    private WebElement passwordTextfield;

    @FindBy(xpath = "//div[contains(@class,'portal-menu')]")
    private WebElement basicLoginMailTable;

    public MailRuCommonPage(WebDriver driver) {
        super(driver);
        this.driverWait = new WebDriverWait(driver, 10);
    }

    public void login(Credentials autotestUser) {
        open(MAIL_RU_URL.value);
        checkTitle(MAIL_RU_TITLE);
        fillTextfieldWithData(usernameTextfield, autotestUser.getUsername().substring(0, autotestUser.getUsername().indexOf("@")));
        pressEnter(usernameTextfield);
        driverWait.until(ExpectedConditions.visibilityOf(passwordTextfield));
        fillTextfieldWithData(passwordTextfield, autotestUser.getPassword());
        pressEnter(passwordTextfield);
        checkLoginIsSuccessful();
    }

    private void pressEnter(WebElement locator) {
        new Actions(driver).sendKeys(locator, Keys.ENTER).build().perform();
    }

    private void fillTextfieldWithData(WebElement textfield, String data) {
        textfield.click();
        textfield.clear();
        new Actions(driver).sendKeys(textfield, data).build().perform();
    }



    private void checkLoginIsSuccessful() {
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(basicLoginMailTable));
        checkTitle(LOG_IN_TITLE);
    }
}
