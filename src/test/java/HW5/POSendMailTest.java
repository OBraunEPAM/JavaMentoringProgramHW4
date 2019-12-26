package HW5;

import org.testng.annotations.*;
import utils.exceptions.NoSuchEMailException;
import page_objects.FolderElementsPage;
import page_objects.MailRuCommonPage;
import config.Setup;

import static org.openqa.selenium.remote.BrowserType.CHROME;
import static utils.enums.Credentials.AUTOTEST_USER;
import static utils.enums.Emails.AUTOTEST_EMAIL;
import static utils.enums.MailRuData.*;

public class POSendMailTest extends Setup {

    private MailRuCommonPage commonPage;
    private FolderElementsPage folderElementsPage;

    @BeforeClass(alwaysRun = true)
    private void setWebDriverType() throws Exception {
        setupDriver(CHROME);
        commonPage = initPage(MailRuCommonPage.class);
        folderElementsPage = initPage(FolderElementsPage.class);
        commonPage.open(MAIL_RU_URL.value);
    }

    @BeforeMethod(alwaysRun = true)
    private void loginAsDefaultUser() {
        commonPage.login(AUTOTEST_USER);
    }

    @AfterMethod(alwaysRun = true)
    private void logOff() {
        folderElementsPage.logOff();
    }

    @AfterClass(alwaysRun = true)
    private void killDriver() throws Exception {
        getDriver().quit();
    }

    @Test
    public void checkEMailCanBeCreated() throws NoSuchEMailException {
        folderElementsPage.createNewEmail(AUTOTEST_EMAIL, SAVE_AS_DRAFT);

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
        folderElementsPage.createNewEmail(AUTOTEST_EMAIL, SEND);

        folderElementsPage.checkEmailIsPresentInTheFolder(AUTOTEST_EMAIL, INBOX);

        folderElementsPage.checkContentOfEmail(AUTOTEST_EMAIL, INBOX);

        folderElementsPage.clearFolder(SENT, DRAFT, INBOX);
    }
}
