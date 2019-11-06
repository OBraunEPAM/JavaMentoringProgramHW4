package HW5;

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
    public void checkEMailCanBeCreated() {
        commonPage.createNewEmail(AUTOTEST_EMAIL, NEW_EMAIL_SAVE);

        commonPage.checkEmailIsPresentInTheFolder(AUTOTEST_EMAIL, DRAFT);

        commonPage.checkContentOfDraftEmail(AUTOTEST_EMAIL);
    }

    @Test
    public void checkEMailCanBeSent() {
        commonPage.sendEmailFromDraftFolder(AUTOTEST_EMAIL);

        commonPage.checkEmailIsNotInTheFolder(AUTOTEST_EMAIL, DRAFT);

        commonPage.checkEmailIsPresentInTheFolder(AUTOTEST_EMAIL, SENT);
    }
}
