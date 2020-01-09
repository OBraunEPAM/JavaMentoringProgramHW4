package HW5;

import hooks.Hooks;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.exceptions.NoSuchEMailException;

import static utils.enums.Credentials.AUTOTEST_USER;
import static utils.enums.Emails.AUTOTEST_EMAIL;
import static utils.enums.MailRuData.*;

public class POSendMailTest extends Hooks {

    @BeforeMethod(alwaysRun = true)
    private void loginAsDefaultUser() {
        commonPage.login(AUTOTEST_USER);
    }

    @AfterMethod(alwaysRun = true)
    private void logOff() {
        folderElementsPage.logOff();
    }

    @Test(alwaysRun = true)
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
