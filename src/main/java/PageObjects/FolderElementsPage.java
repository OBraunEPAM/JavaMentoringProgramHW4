package PageObjects;

import Exceptions.NoSuchEMailException;
import enums.Emails;
import enums.MailRuData;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static enums.MailRuData.INBOX;
import static enums.MailRuData.DRAFT;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class FolderElementsPage extends MailRuCommonPage {

    private static int numberOfEmailsInFolderCounter;

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

    @FindBy(xpath = "//span[@class='notify__message__text']")
    private WebElement actionToaster;

    @FindBy(xpath = "//div[@data-type='to']//input[@type='text']")
    private WebElement addresseeTextfield;

    @FindBy(xpath = "//div[contains(@class, 'contactsContainer')]")
    private WebElement filledAddresseeTextfield;

    @FindBy(xpath = "//input[@name='Subject']")
    private WebElement subjectTextfield;

    @FindBy(xpath = "//div[contains(@class, 'editable-container')]/div[1]")
    private WebElement newEMailTextarea;

    @FindBy(xpath = "//div[contains(@class, 'letter__body')]")
    private WebElement eMailBody;

    @FindBy(xpath = "//*[@class='thread__subject']")
    private WebElement eMailSubjectField;

    @FindBy(xpath = "//*[@class='letter__contact-item']")
    private List<WebElement> eMailInterlocutors;

    @FindBy(xpath = "//div[contains(@style, 'display: table;')]")
    public WebElement folderBasicTable;

    @FindBy(xpath = "//div[contains(@class,'sidebar_closed')]/div/div[2]//a/div/div[2]")
    public List<WebElement> foldersSidebar;

    @FindBy(xpath = "//div[contains(@class, 'dataset__items')]/a")
    public List<WebElement> numberOfEmailsInFolder;

    @FindBy(xpath = "//span[@title='Написать письмо']")
    public WebElement createNewEmailButton;

    @FindBy(xpath = "//*[@title='Выделить все']")
    public WebElement selectAllButton;

    @FindBy(xpath = "//*[@title='Удалить']")
    public WebElement deleteButton;

    @FindBy(xpath = "//div[@class='dimmer']/..//*[text()='Очистить']")
    public WebElement clearButton;

    @FindBy(xpath = "//div[contains(@class, 'dimmer')]")
    private WebElement newDialogWindow;

    @FindBy(xpath = "//div[contains(@class,'octopus_full')]")
    public WebElement emptyFolderLogo;

    public FolderElementsPage(WebDriver driver) {
        super(driver);
    }

    public void openFolder(MailRuData folderName) {
        for (WebElement link : foldersSidebar) {
            if (link.getText().contains(folderName.value)) {
                link.click();
                driverWait.until(ExpectedConditions.visibilityOf(folderBasicTable));
            }
        }
    }

    public void clearFolder(MailRuData... folderName) {
        WebDriverWait driverWait = new WebDriverWait(driver, 5);
        for (MailRuData folder:folderName) {
            openFolder(folder);
            try {
                selectAllButton.click();
            } catch (NoSuchElementException e) {
                System.out.printf("%s folder is already empty", folder.value);
                continue;
            }
            driverWait.until(ExpectedConditions.visibilityOf(deleteButton));
            deleteButton.click();
            if (folder.value.equals(INBOX.value)) {
                driverWait.until(ExpectedConditions.visibilityOf(newDialogWindow));
                clickElementUsingJS(clearButton);
            }
            driverWait.until(ExpectedConditions.visibilityOf(actionToaster));
            driverWait.until(ExpectedConditions.visibilityOf(emptyFolderLogo));
        }
    }

    private void fillNewEmailData(Emails autotestEmail) {
        addresseeTextfield.sendKeys(autotestEmail.getAddressee());
        subjectTextfield.sendKeys(autotestEmail.getSubject());
        newEMailTextarea.sendKeys(autotestEmail.getText());
    }

    public void createNewEmail(Emails autotestEmail, MailRuData mailOption) {
        createNewEmailButton.click();
        driverWait.until(ExpectedConditions.visibilityOf(newDialogWindow));
        fillNewEmailData(autotestEmail);
        switch (mailOption) {
            case NEW_EMAIL_SEND:
                sendNewEmailButton.click();
                driverWait.until(ExpectedConditions.visibilityOf(closeConfirmationWindowButton));
                closeConfirmationWindowButton.click();
                break;
            case NEW_EMAIL_SAVE_AS_DRAFT:
                saveNewEmailButton.click();
                driverWait.until(ExpectedConditions.visibilityOf(actionToaster));
                closeNewEmailWindowButton.click();
                break;
            case NEW_EMAIL_CANCEL:
                abortNewEmailButton.click();
                break;
        }
        // for some reason invisibilityOf method doesn't work when WebElement is passed as an argument
        //driverWait.until(ExpectedConditions.invisibilityOf(newDialogWindow));
        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'dimmer')]")));
    }

    public void checkContentOfEmail(Emails autotestEmail, MailRuData folderName) throws NoSuchEMailException {
        checkEmailIsPresentInTheFolder(autotestEmail, folderName);
        driver.findElement(By.xpath("//*[text()='" + autotestEmail.getSubject() + "']")).click();
        if (folderName.value.equals(DRAFT.value)) {
            driverWait.until(ExpectedConditions.visibilityOf(newEMailTextarea));
            assertEquals(filledAddresseeTextfield.getText(), autotestEmail.getAddressee());
            assertEquals(getElementAttributeUsingJS("value", subjectTextfield).toString(), autotestEmail.getSubject());
            assertTrue(newEMailTextarea.getText().contains(autotestEmail.getText()));
            closeNewEmailWindowButton.click();
            driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'dimmer')]")));
        } else {
            driverWait.until(ExpectedConditions.visibilityOf(eMailBody));
            assertEquals(getElementAttributeUsingJS("title", eMailInterlocutors.get(0)), autotestEmail.getAddressee());
            assertEquals(eMailSubjectField.getText(), autotestEmail.getSubject());
            assertTrue(eMailBody.getText().contains(autotestEmail.getText()));
        }
    }

    public void sendEmailFromDraftFolder(Emails autotestEmail) throws NoSuchEMailException {
        findAndOpenEMail(autotestEmail, DRAFT);
        numberOfEmailsInFolderCounter = numberOfEmailsInFolder.size();
        sendNewEmailButton.click();
        driverWait.until(ExpectedConditions.visibilityOf(closeConfirmationWindowButton));
        closeConfirmationWindowButton.click();
        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'dimmer')]")));
    }

    private void findAndOpenEMail(Emails eMailName, MailRuData folderName) throws NoSuchEMailException {
        checkEmailIsPresentInTheFolder(eMailName, folderName);
        driver.findElement(By.xpath("//*[text()='" + eMailName.getSubject() + "']")).click();
        driverWait.until(ExpectedConditions.visibilityOf(newEMailTextarea));
    }

    public void checkEmailIsNotInTheFolder(Emails autotestEmail, MailRuData folderName) {
        openFolder(folderName);
        if (numberOfEmailsInFolderCounter == 2) {
            driverWait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[contains(@class, 'dataset__items')]/a"), 0));
        } else if (numberOfEmailsInFolderCounter != 0){
            driverWait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[contains(@class, 'dataset__items')]/a"), numberOfEmailsInFolderCounter - 1));
        }
        assertFalse(folderBasicTable.getText().contains(autotestEmail.getSubject()));
    }

    public void checkEmailIsPresentInTheFolder(Emails emailName, MailRuData folderName) throws NoSuchEMailException {
        openFolder(folderName);
        if (!(folderBasicTable.getText().contains(emailName.getSubject()))) throw new NoSuchEMailException(emailName, folderName);
    }
}
