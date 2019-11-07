package HW5;

import Exceptions.NoSuchEMailException;
import common.SetupClass;
import org.testng.annotations.*;

import static enums.Credentials.AUTOTEST_USER;
import static enums.Emails.AUTOTEST_EMAIL;
import static enums.MailRuData.*;

public class POSendMailTest extends SetupClass {

    @BeforeClass(alwaysRun = true)
    private void loginAsDefaultUser() {
        commonPage.open(MAIL_RU_URL);
        commonPage.checkTitle(MAIL_RU_TITLE);
        commonPage.login(AUTOTEST_USER);
        commonPage.checkLoginIsSuccesful();
    }

    @AfterClass(alwaysRun = true)
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
}
