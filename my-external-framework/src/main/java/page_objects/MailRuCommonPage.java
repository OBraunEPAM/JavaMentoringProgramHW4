package page_objects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.enums.Credentials;


import static utils.enums.MailRuData.*;

public class MailRuCommonPage extends PageObject {

    @FindBy(xpath = "//div[contains(@class,'portal-menu')]")
    private WebElement basicLoginMailTable;

    @FindBy(xpath = "//input[@id='mailbox:login']")
    private WebElement usernameTextfield;

    @FindBy(xpath = "//input[@id='mailbox:password']")
    private WebElement passwordTextfield;

    @FindBy(xpath = "//a[@title='выход']")
    private WebElement logOffButton;

    @FindBy(xpath = "//a[text()='Вход']")
    private WebElement loginLink;

    public MailRuCommonPage(WebDriver driver) {
        super(driver);
        this.driverWait = new WebDriverWait(driver, 10);
    }

    public void fillTextfieldWithData(WebElement textfield, String data) {
        textfield.click();
        textfield.clear();
        new Actions(driver).sendKeys(textfield, data).build().perform();
    }

    public void checkLoginIsSuccessful() {
        driverWait.until(ExpectedConditions.visibilityOf(basicLoginMailTable));
        checkTitle(LOG_IN_TITLE);
    }
    public void logOff() {
        logOffButton.click();
        driverWait.until(ExpectedConditions.visibilityOf(loginLink));
    }

    public void fillLoginTextfield(Credentials user) {
        clearAndSelectTextfield(usernameTextfield);
        new Actions(driver).sendKeys(usernameTextfield, user.getUsername().substring(0, user.getUsername().indexOf("@"))).build().perform();
    }

    public void pressEnterOnLoginForm() {
        pressEnter(usernameTextfield);
    }

    public void waitUntilPasswordFieldIsVisible() {
        driverWait.until(ExpectedConditions.visibilityOf(passwordTextfield));
    }

    public void filldPasswordTextfield(Credentials autotestUser) {
        clearAndSelectTextfield(passwordTextfield);
        new Actions(driver).sendKeys(passwordTextfield, autotestUser.getPassword()).build().perform();
    }

    public void pressEnterOnPasswordForm() {
        pressEnter(passwordTextfield);
    }
}
