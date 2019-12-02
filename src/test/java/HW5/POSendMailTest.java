package HW5;

import Exceptions.NoSuchEMailException;
import PageObjects.FolderElementsPage;
import PageObjects.MailRuCommonPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.Setup;

import static enums.Credentials.AUTOTEST_USER;
import static enums.Emails.AUTOTEST_EMAIL;
import static enums.MailRuData.DRAFT;
import static enums.MailRuData.NEW_EMAIL_SAVE_AS_DRAFT;
import static enums.MailRuData.SENT;
import static enums.MailRuData.NEW_EMAIL_SEND;
import static enums.MailRuData.INBOX;
import static enums.SetupEnums.WEB_DRIVER;

public class POSendMailTest extends Setup {

    private MailRuCommonPage commonPage;
    private FolderElementsPage folderElementsPage;

    @BeforeClass(alwaysRun = true)
    private void setWebDriverType() throws Exception {
        // parameter could be either WEB_DRIVER or REMOTE_WEB_DRIVER. In last case Selenium grid must be running
        setupDriver(WEB_DRIVER);
        commonPage = initPage(MailRuCommonPage.class);
        folderElementsPage = initPage(FolderElementsPage.class);
    }

    @BeforeMethod(alwaysRun = true)
    private void loginAsDefaultUser() {
        commonPage.login(AUTOTEST_USER);
    }

    @AfterMethod(alwaysRun = true)
    private void logOff() {
        commonPage.logOff();
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
