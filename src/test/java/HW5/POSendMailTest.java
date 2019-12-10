package HW5;

import tests.Tests;
import utils.exceptions.NoSuchEMailException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static utils.enums.Credentials.AUTOTEST_USER;
import static utils.enums.Emails.AUTOTEST_EMAIL;
import static utils.enums.MailRuData.DRAFT;
import static utils.enums.MailRuData.SENT;
import static utils.enums.MailRuData.NEW_EMAIL_SEND;
import static utils.enums.MailRuData.INBOX;
import static utils.enums.SetupEnums.WEB_DRIVER;

public class POSendMailTest extends Tests {

    @BeforeClass(alwaysRun = true)
    private void setWebDriverType() {
        // parameter could be either WEB_DRIVER or REMOTE_WEB_DRIVER. In last case Selenium grid must be running
        setupDriver(WEB_DRIVER);
    }

    @BeforeMethod(alwaysRun = true)
    private void loginAsDefaultUser() {
        onCommonPage().login(AUTOTEST_USER);
    }

    @AfterMethod(alwaysRun = true)
    private void logOff() {
        onCommonPage().logOff();
    }

    @Test
    public void checkEMailCanBeCreated() throws NoSuchEMailException {

        onFolderElementsPage().openNewEmailForm();

        onFolderElementsPage().fillNewEmail(AUTOTEST_EMAIL);

        onFolderElementsPage().saveEmailAsDraft();

        onFolderElementsPage().openDraftFolder();

        onFolderElementsPage().checkEmailIsInDraftFolder(AUTOTEST_EMAIL);

        onFolderElementsPage().checkContentOfEmailInDraft(AUTOTEST_EMAIL);

        onFolderElementsPage().closeEmail();

//        onFolderElementsPage().checkEmailIsInTheFolder(AUTOTEST_EMAIL, DRAFT, true);
    }

//    @Test
//    public void checkEMailCanBeSent() throws NoSuchEMailException {
//
//        onFolderElementsPage().openDraftFolder();
//
//        onFolderElementsPage().getElementVisibility(AUTOTEST_EMAIL);
//
//        onFolderElementsPage().sendEmailFromDraftFolder(AUTOTEST_EMAIL);
//
//        onFolderElementsPage().checkEmailIsInTheFolder(AUTOTEST_EMAIL, DRAFT, false);
//
//        onFolderElementsPage().checkEmailIsInTheFolder(AUTOTEST_EMAIL, SENT, true);
//    }
//
//    @Test
//    public void checkSentEMailIsPresentInInbox() throws NoSuchEMailException {
//        onFolderElementsPage().createNewEmail(AUTOTEST_EMAIL, NEW_EMAIL_SEND);
//
//        onFolderElementsPage().checkEmailIsInTheFolder(AUTOTEST_EMAIL, INBOX, true);
//
//        onFolderElementsPage().checkContentOfEmailInDraft(AUTOTEST_EMAIL, INBOX);
//
//        onFolderElementsPage().clearFolder(SENT, DRAFT, INBOX);
//    }
}
