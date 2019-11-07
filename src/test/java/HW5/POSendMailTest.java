package HW5;

import Exceptions.NoSuchEMailException;
import common.SetupClass;
import org.testng.annotations.*;

import static enums.Credentials.AUTOTEST_USER;
import static enums.Emails.AUTOTEST_EMAIL;
import static enums.MailRuData.*;

public class POSendMailTest extends SetupClass {

    @BeforeMethod(alwaysRun = true)
    private void loginAsDefaultUser() {
        commonPage.open(MAIL_RU_URL);
        commonPage.checkTitle(MAIL_RU_TITLE);
        commonPage.login(AUTOTEST_USER);
        commonPage.checkLoginIsSuccesful();
    }

    @AfterMethod(alwaysRun = true)
    private void logOff() {
        commonPage.logOff();
    }

    @Test
    public void checkEMailCanBeCreated() throws NoSuchEMailException {
        commonPage.createNewEmail(AUTOTEST_EMAIL, NEW_EMAIL_SAVE_AS_DRAFT);

        commonPage.checkEmailIsPresentInTheFolder(AUTOTEST_EMAIL, DRAFT);

        commonPage.checkContentOfEmail(AUTOTEST_EMAIL, DRAFT);
    }

    @Test
    public void checkEMailCanBeSent() throws NoSuchEMailException {
        commonPage.sendEmailFromDraftFolder(AUTOTEST_EMAIL);

        commonPage.checkEmailIsNotInTheFolder(AUTOTEST_EMAIL, DRAFT);

        commonPage.checkEmailIsPresentInTheFolder(AUTOTEST_EMAIL, SENT);
    }

    @Test
    public void checkSentEMailIsPresentInInbox() throws NoSuchEMailException {
        commonPage.createNewEmail(AUTOTEST_EMAIL, NEW_EMAIL_SEND);

        commonPage.checkEmailIsPresentInTheFolder(AUTOTEST_EMAIL, INBOX);

        commonPage.checkContentOfEmail(AUTOTEST_EMAIL, INBOX);
    }
}
