package HW5;

import tests.Tests;
import utils.exceptions.NoSuchEMailException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static utils.enums.Credentials.AUTOTEST_USER;
import static utils.enums.Emails.AUTOTEST_EMAIL;

public class POSendMailTest extends Tests {

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

        onFolderElementsPage().closeEmail();

        onFolderElementsPage().openDraftFolder();

        onFolderElementsPage().checkEmailIsInDraftFolder(AUTOTEST_EMAIL);

        onFolderElementsPage().checkContentOfEmailInDraft(AUTOTEST_EMAIL);

        onFolderElementsPage().closeEmail();
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
