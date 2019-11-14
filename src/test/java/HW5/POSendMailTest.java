package HW5;

import Exceptions.NoSuchEMailException;
import common.SetupClass;
import org.testng.annotations.*;

import static enums.Credentials.AUTOTEST_USER;
import static enums.Emails.AUTOTEST_EMAIL;
import static enums.MailRuData.*;
import static enums.SetupEnums.*;

public class POSendMailTest extends SetupClass {

    @BeforeClass(alwaysRun = true)
    private void setWebDriverType() throws Exception {
        // parameter could be either WEB_DRIVER or REMOTE_WEB_DRIVER. In last case Selenium grid must be running
        setupDriver(REMOTE_WEB_DRIVER);
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

        commonPage.clearFolder(SENT, DRAFT, INBOX);
    }
}
