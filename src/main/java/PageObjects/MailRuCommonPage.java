package PageObjects;

import Exceptions.NoSuchEMailException;
import enums.Credentials;
import enums.Emails;
import enums.MailRuData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static enums.MailRuData.*;
import static org.testng.Assert.*;

public class MailRuCommonPage {

    private WebDriver driver;

    private static int numberOfEmailsInFolderCounter;

    /**
     * General elements
     */
    @FindBy(xpath = "//select[@id='mailbox:domain']/option[@selected]")
    private WebElement title;

    @FindBy(xpath = "//input[@id='mailbox:login']")
    private WebElement usernameTextfield;

    @FindBy(xpath = "//input[@id='mailbox:password']")
    private WebElement passwordTextfield;

    @FindBy(xpath = "//a[@title='выход']")
    private WebElement logOffButton;

    @FindBy(xpath = "//input[@class='o-control']")
    private WebElement submitButton;

    @FindBy(xpath = "//a[text()='Вход']")
    private WebElement loginLink;

    /**
     * Folders elements
     */
    @FindBy(xpath = "//div[contains(@style, 'display: table;')]")
    private WebElement folderBasicTable;

    @FindBy(xpath = "//div[contains(@class,'sidebar_closed')]/div/div[2]//a/div/div[2]")
    private List<WebElement> foldersSidebar;

    @FindBy(xpath = "//div[contains(@class, 'dataset__items')]/a")
    private List<WebElement> numberOfEmailsInFolder;

    @FindBy(xpath = "//div[contains(@class,'portal-menu')]")
    private WebElement basicLoginMailTable;

    @FindBy(xpath = "//span[@title='Написать письмо']")
    private WebElement createNewEmailButton;

    /**
     * New Email window button
     */
    @FindBy(xpath = "//*[@title='Закрыть']")
    private WebElement closeNewEmailWindowButton;

    @FindBy(xpath = "//span[@title='Закрыть']")
    private WebElement closeConfirmationWindowButton;

    @FindBy(xpath = "//span[@title='Отправить']")
    private WebElement sendNewEmailButton;

    @FindBy(xpath = "//span[@title='Сохранить']")
    private WebElement saveNewEmailButton;

    @FindBy(xpath = "//span[@title='Отменить']")
    private WebElement abortNewEmailButton;

    @FindBy(xpath = "//div[contains(@class, 'dimmer')]")
    private WebElement newEmailWindow;

    @FindBy(xpath = "//span[@class='notify__message__text']")
    private WebElement saveEMailToaster;

    @FindBy(xpath = "//div[@data-type='to']//input[@type='text']")
    private WebElement addresseeTextfield;

    @FindBy(xpath = "//div[contains(@class, 'contactsContainer')]")
    private WebElement filledAddresseeTextfield;

    @FindBy(xpath = "//input[@name='Subject']")
    private WebElement subjectTextfield;

    @FindBy(xpath = "//div[contains(@class, 'editable-container')]/div[1]")
    private WebElement newEMailTextarea;

    //----------------------------------------------------------------------

    public MailRuCommonPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open(MailRuData mailRuUrl) {
        driver.get(mailRuUrl.value);
    }

    public void checkTitle(MailRuData mailRuTitle) {
        assertEquals(title.getText(), mailRuTitle.value);
    }

    public void login(Credentials autotestUser) {
        fillTextfieldWithData(usernameTextfield, autotestUser.getUsername().substring(0, autotestUser.getUsername().indexOf("@")));
        submitButton.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(passwordTextfield));
        passwordTextfield.sendKeys(autotestUser.getPassword());
        submitButton.click();
    }

    private void fillTextfieldWithData(WebElement textfield, String data) {
        textfield.click();
        textfield.clear();
        textfield.sendKeys(data);
    }

    public void logOff() {
        logOffButton.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(loginLink));
    }

    private void openFolder(MailRuData folderName) {
        for (WebElement link : foldersSidebar) {
            if (link.getText().contains(folderName.value)) {
                link.click();
                new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(folderBasicTable));
            }
        }
    }

    public void checkLoginIsSuccesful() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(basicLoginMailTable));
        assertTrue(driver.getTitle().contains("Входящие"));
    }

    private void fillNewEmailData(Emails autotestEmail) {
        addresseeTextfield.sendKeys(autotestEmail.getAddressee());
        subjectTextfield.sendKeys(autotestEmail.getSubject());
        newEMailTextarea.sendKeys(autotestEmail.getText());
    }

    public void createNewEmail(Emails autotestEmail, MailRuData mailOption) {
        createNewEmailButton.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(newEmailWindow));
        fillNewEmailData(autotestEmail);
        switch (mailOption) {
            case NEW_EMAIL_SEND:
                sendNewEmailButton.click();
                break;
            case NEW_EMAIL_SAVE_AS_DRAFT:
                saveNewEmailButton.click();
                break;
            case NEW_EMAIL_CANCEL:
                abortNewEmailButton.click();
                break;
        }
        WebDriverWait driverWait = new WebDriverWait(driver, 10);
        driverWait.until(ExpectedConditions.visibilityOf(saveEMailToaster));
        closeNewEmailWindowButton.click();
        // for some reason invisibilityOf method doesn't work when WebElement is passed as an argument
        //new WebDriverWait(driver, 10 ).until(ExpectedConditions.invisibilityOf(newEmailWindow));
        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'dimmer')]")));
    }

    public void checkContentOfEmail(Emails autotestEmail, MailRuData folderName) throws NoSuchEMailException {
        checkEmailIsPresentInTheFolder(autotestEmail, folderName);
        driver.findElement(By.xpath("//*[text()='" + autotestEmail.getSubject() + "']")).click();
        WebDriverWait driverWait = new WebDriverWait(driver, 10);
        driverWait.until(ExpectedConditions.visibilityOf(newEMailTextarea));
        assertEquals(filledAddresseeTextfield.getText(), autotestEmail.getAddressee());
        assertEquals(subjectTextfield.getAttribute("value"), autotestEmail.getSubject());
        assertTrue(newEMailTextarea.getText().contains(autotestEmail.getText()));
        closeNewEmailWindowButton.click();
        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'dimmer')]")));
    }

    public void sendEmailFromDraftFolder(Emails autotestEmail) throws NoSuchEMailException {
        findAndOpenEMail(autotestEmail, DRAFT);
        numberOfEmailsInFolderCounter = numberOfEmailsInFolder.size();
        sendNewEmailButton.click();
        WebDriverWait driverWait = new WebDriverWait(driver, 10);
        driverWait.until(ExpectedConditions.visibilityOf(closeConfirmationWindowButton));
        closeConfirmationWindowButton.click();
        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'dimmer')]")));
    }

    private void findAndOpenEMail(Emails eMailName, MailRuData folderName) throws NoSuchEMailException {
        checkEmailIsPresentInTheFolder(eMailName, folderName);
        driver.findElement(By.xpath("//*[text()='" + eMailName.getSubject() + "']")).click();
        WebDriverWait driverWait = new WebDriverWait(driver, 10);
        driverWait.until(ExpectedConditions.visibilityOf(newEMailTextarea));
    }

    public void checkEmailIsNotInTheFolder(Emails autotestEmail, MailRuData folderName) {
        openFolder(folderName);
        if (numberOfEmailsInFolderCounter == 2) {
            new WebDriverWait(driver, 10).until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[contains(@class, 'dataset__items')]/a"), 0));
        } else {
            new WebDriverWait(driver, 10).until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[contains(@class, 'dataset__items')]/a"), numberOfEmailsInFolderCounter - 1));
        }
        assertFalse(folderBasicTable.getText().contains(autotestEmail.getSubject()));
    }

    public void checkEmailIsPresentInTheFolder(Emails emailName, MailRuData folderName) throws NoSuchEMailException {
        openFolder(folderName);
        if (!(folderBasicTable.getText().contains(emailName.getSubject()))) throw new NoSuchEMailException(emailName, folderName);
    }
}
