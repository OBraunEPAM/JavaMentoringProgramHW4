package HW5;

import common.SetupClass;
import org.testng.annotations.Test;

import static enums.Credentials.AUTOTEST_USER;
import static enums.Emails.AUTOTEST_EMAIL;
import static enums.MailRuData.*;

public class POSendMailTest extends SetupClass {

    @Test
    public void sendMailTest() {

        // 1 open site
        commonPage.open(MAIL_RU_URL);
        commonPage.checkTitle(MAIL_RU_TITLE);

        //2 login
        commonPage.login(AUTOTEST_USER);

        //3 assert login is successful
        commonPage.checkLoginIsSuccesful();

        //4 create new e-mail
        commonPage.createNewEmail(AUTOTEST_EMAIL);

        //5 save the mail as a draft
        commonPage.saveAsDraftAndClose();

        //6 verify that e-mail has been saved as a draft
        commonPage.checkEmailIsPresentInDraftFolder(AUTOTEST_EMAIL);

        // 7 verify that content of e-mail is correct
        commonPage.checkContentOfDraftEmail(AUTOTEST_EMAIL);

        // 8 send the e-mail
        commonPage.sendEmailFromDraft(AUTOTEST_EMAIL);

        // 9 verify that the e-mail disappeared from 'Drafts' folder
        commonPage.checkEmailIsNotInTheDraftFolder(AUTOTEST_EMAIL);

        // 10 verify the the e-mail is in 'Sent' folder
        commonPage.checkEmailIsPresentInSentFolder(AUTOTEST_EMAIL);

        // 11 log off
        commonPage.logOff();
    }
}
