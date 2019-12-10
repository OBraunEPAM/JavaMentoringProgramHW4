package page_objects;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Factory;
import utils.exceptions.NoSuchEMailException;
import utils.enums.Emails;
import utils.enums.MailRuData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static utils.enums.MailRuData.DRAFT;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class FolderElementsPage extends PageObject {

    @FindBy(xpath = "//div[@data-type='to']//input[@type='text']")
    private WebElement addresseeTextfield;

    @FindBy(xpath = "//input[@name='Subject']")
    private WebElement subjectTextfield;

    @FindBy(xpath = "//div[contains(@class, 'editable-container')]/div[1]")
    private WebElement newEMailTextarea;

    @FindBy(xpath = "//div[contains(@class, 'dataset__items')]/a")
    private List<WebElement> numberOfEmailsInFolder;

    @FindBy(xpath = "//span[@title='Отправить']")
    private WebElement sendNewEmailButton;

    @FindBy(xpath = "//div[contains(@class, 'contactsContainer')]")
    private WebElement filledAddresseeTextfield;

    @FindBy(xpath = "//*[@title='Закрыть']")
    private WebElement closeNewEmailWindowButton;

    @FindBy(xpath = "//span[@title='Закрыть']")
    private WebElement closeConfirmationWindowButton;

    @FindBy(xpath = "//span[@title='Сохранить']")
    private WebElement saveNewEmailButton;

    @FindBy(xpath = "//span[@title='Отменить']")
    private WebElement abortNewEmailButton;

    @FindBy(xpath = "//span[@class='notify__message__text']")
    private WebElement actionToaster;

    @FindBy(xpath = "//div[contains(@class, 'letter__body')]")
    private WebElement eMailBody;

    @FindBy(xpath = "//*[@class='thread__subject']")
    private WebElement eMailSubjectField;

    @FindBy(xpath = "//*[@class='letter__contact-item']")
    private List<WebElement> eMailInterlocutors;

    @FindBy(xpath = "//span[@title='Написать письмо']")
    private WebElement createNewEmailButton;

    @FindBy(xpath = "//*[@title='Выделить все']")
    private WebElement selectAllButton;

    @FindBy(xpath = "//*[@title='Удалить']")
    private WebElement deleteButton;

    @FindBy(xpath = "//div[@class='dimmer']/..//*[text()='Очистить']")
    private WebElement clearButton;

    @FindBy(xpath = "//div[contains(@class, 'dimmer')]")
    private WebElement newDialogWindow;

    @FindBy(xpath = "//div[contains(@class,'octopus_full')]")
    private WebElement emptyFolderLogo;

    @FindBy(xpath = "//div[text()='Черновики']")
    private WebElement draftFolderOpeningButton;

    public FolderElementsPage(WebDriver driver) {
        super(driver);
        this.driverWait = new WebDriverWait(driver, 10);
    }

    public void fillEmailBodyTextfield(String text) {
        newEMailTextarea.sendKeys(text);
    }

    public void fillSubjectTextfield(String subject) {
        subjectTextfield.sendKeys(subject);
    }

    public void fillAddresseeTextfield(String addressee) {
        addresseeTextfield.sendKeys(addressee);
    }

    public void clickNewEmailButton() {
        createNewEmailButton.click();
        driverWait.until(ExpectedConditions.visibilityOf(newDialogWindow));
    }

    public void clickSaveAsDraftButton() {
        saveNewEmailButton.click();
        driverWait.until(ExpectedConditions.visibilityOf(actionToaster));
    }

    public void clickOnDraftFolder() {
        draftFolderOpeningButton.click();
        waitUntilElementIsVisible(folderBasicTable);
    }

    public void openEmailInDraft(String subject) {
        driver.findElement(By.xpath("//*[text()='" + subject + "']")).click();
        driverWait.until(ExpectedConditions.visibilityOf(newEMailTextarea));
    }

    public void checkEmailSubject(String subject) {
        assertEquals(this.getElementAttributeUsingJS("value", subjectTextfield).toString(), subject);
    }

    public void checkEmailText(String text) {
        assertTrue(newEMailTextarea.getText().contains(text));
    }

    public void checkEmailAddresse(String addressee) {
        assertEquals(filledAddresseeTextfield.getText(), addressee);
    }

    public void clickCloseButton() {
        closeNewEmailWindowButton.click();
        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'dimmer')]")));
    }

//    public void openFolder(MailRuData folderName) {
//        for (WebElement link : foldersSidebar) {
//            if (link.getText().contains(folderName.value)) {
//                link.click();
//                driverWait.until(ExpectedConditions.visibilityOf(folderBasicTable));
//            }
//        }
//    }

//    public void fillNewEmailDataAndSaveAsDraft(Emails autotestEmail) {
//        addresseeTextfield.sendKeys(autotestEmail.getAddressee());
//        subjectTextfield.sendKeys(autotestEmail.getSubject());
//        newEMailTextarea.sendKeys(autotestEmail.getText());
//        saveNewEmailButton.click();
//        driverWait.until(ExpectedConditions.visibilityOf(actionToaster));
//        closeNewEmailWindowButton.click();
//    }

//    public void createNewEmail(Emails autotestEmail, MailRuData mailOption) {
//        switch (mailOption) {
//            case NEW_EMAIL_SEND:
//                sendNewEmailButton.click();
//                driverWait.until(ExpectedConditions.visibilityOf(closeConfirmationWindowButton));
//                closeConfirmationWindowButton.click();
//                break;
//            case NEW_EMAIL_SAVE_AS_DRAFT:
//                saveNewEmailButton.click();
//                driverWait.until(ExpectedConditions.visibilityOf(actionToaster));
//                closeNewEmailWindowButton.click();
//                break;
//            case NEW_EMAIL_CANCEL:
//                abortNewEmailButton.click();
//                break;
//        }
//        // for some reason invisibilityOf method doesn't work when WebElement is passed as an argument
//        //driverWait.until(ExpectedConditions.invisibilityOf(newDialogWindow));
//        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'dimmer')]")));
//    }

//    public void sendEmailFromDraftFolder(Emails autotestEmail) throws NoSuchEMailException {
//        findAndOpenEMail(autotestEmail, DRAFT);
//        numberOfEmailsInFolderCounter = numberOfEmailsInFolder.size();
//        sendNewEmailButton.click();
//        driverWait.until(ExpectedConditions.visibilityOf(closeConfirmationWindowButton));
//        closeConfirmationWindowButton.click();
//        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'dimmer')]")));
//    }

//    public void findAndOpenEMail(Emails eMailName, MailRuData folderName) throws NoSuchEMailException {
//        checkEmailIsPresentInTheFolder(eMailName, folderName);
//        driver.findElement(By.xpath("//*[text()='" + eMailName.getSubject() + "']")).click();
//        driverWait.until(ExpectedConditions.visibilityOf(newEMailTextarea));
//        if (folderName.equals(DRAFT)) numberOfEmailsInFolderCounter = numberOfEmailsInFolder.size();
//    }

}
