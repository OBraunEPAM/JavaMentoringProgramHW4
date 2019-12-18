package HW5;

import utils.exceptions.NoSuchEMailException;
import page_objects.FolderElementsPage;
import page_objects.MailRuCommonPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import config.Setup;

import static org.openqa.selenium.remote.BrowserType.CHROME;
import static utils.enums.Credentials.AUTOTEST_USER;
import static utils.enums.Emails.AUTOTEST_EMAIL;
import static utils.enums.MailRuData.DRAFT;
import static utils.enums.MailRuData.NEW_EMAIL_SAVE_AS_DRAFT;
import static utils.enums.MailRuData.SENT;
import static utils.enums.MailRuData.NEW_EMAIL_SEND;
import static utils.enums.MailRuData.INBOX;

public class POSendMailTest extends Setup {

    private MailRuCommonPage commonPage;
    private FolderElementsPage folderElementsPage;

    @BeforeClass(alwaysRun = true)
    private void setWebDriverType() throws Exception {
        setupDriver(CHROME);
        commonPage = initPage(MailRuCommonPage.class);
        folderElementsPage = initPage(FolderElementsPage.class);
    }

    @BeforeMethod(alwaysRun = true)
    private void loginAsDefaultUser() {
        commonPage.login(AUTOTEST_USER);
    }

    @AfterMethod(alwaysRun = true)
    private void logOff() {
        folderElementsPage.logOff();
    }

    @Test
    public void checkEMailCanBeCreated() throws NoSuchEMailException {
        folderElementsPage.createNewEmail(AUTOTEST_EMAIL, NEW_EMAIL_SAVE_AS_DRAFT);

        folderElementsPage.checkEmailIsPresentInTheFolder(AUTOTEST_EMAIL, DRAFT);

        folderElementsPage.checkContentOfEmail(AUTOTEST_EMAIL, DRAFT);
    }

    @Test
    public void checkEMailCanBeSent() throws NoSuchEMailException {
        folderElementsPage.sendEmailFromDraftFolder(AUTOTEST_EMAIL);

        folderElementsPage.checkEmailIsNotInTheFolder(AUTOTEST_EMAIL, DRAFT);

        folderElementsPage.checkEmailIsPresentInTheFolder(AUTOTEST_EMAIL, SENT);
    }

    @Test
    public void checkSentEMailIsPresentInInbox() throws NoSuchEMailException {
        folderElementsPage.createNewEmail(AUTOTEST_EMAIL, NEW_EMAIL_SEND);

        folderElementsPage.checkEmailIsPresentInTheFolder(AUTOTEST_EMAIL, INBOX);

        folderElementsPage.checkContentOfEmail(AUTOTEST_EMAIL, INBOX);

        folderElementsPage.clearFolder(SENT, DRAFT, INBOX);
    }
}
