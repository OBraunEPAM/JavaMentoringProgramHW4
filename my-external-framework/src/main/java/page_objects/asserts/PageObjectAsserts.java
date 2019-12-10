package page_objects.asserts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.enums.Emails;
import utils.enums.MailRuData;
import utils.exceptions.NoSuchEMailException;

import static org.testng.Assert.*;
import static utils.enums.MailRuData.DRAFT;


public class PageObjectAsserts {

    protected WebDriver driver;
    protected WebDriverWait driverWait;

    protected static int numberOfEmailsInFolderCounter;

    @FindBy(xpath = "//div[contains(@style, 'display: table;')]")
    public WebElement folderBasicTable;

    public PageObjectAsserts(WebDriver driver) {
        this.driver = driver;
    }

    public <E extends Enum<E>> void checkTitle(Enum<E> page) {
        assertTrue(driver.getTitle().contains(page.toString()));
    }

    public void checkEmailIsPresentInTheFolder(Emails emailName, MailRuData folderName) throws NoSuchEMailException {
        if (!(folderBasicTable.getText().contains(emailName.getSubject()))) throw new NoSuchEMailException(emailName, folderName);
    }

    public void checkEmailIsNotInTheFolder(Emails autotestEmail) {
        if (numberOfEmailsInFolderCounter == 2) {
            driverWait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[contains(@class, 'dataset__items')]/a"), 0));
        } else if (numberOfEmailsInFolderCounter != 0){
            driverWait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[contains(@class, 'dataset__items')]/a"), numberOfEmailsInFolderCounter - 1));
        }
        assertFalse(folderBasicTable.getText().contains(autotestEmail.getSubject()));
    }

    public void checkEmailInDraftFolder(Emails emailName) throws NoSuchEMailException {
        if (!(folderBasicTable.getText().contains(emailName.getSubject()))) throw new NoSuchEMailException(emailName, DRAFT);
    }
}
