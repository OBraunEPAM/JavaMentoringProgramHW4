package BDD.steps;

import BDD.Runner;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import page_objects.FolderElementsPage;
import page_objects.MailRuCommonPage;
import utils.enums.Credentials;
import utils.enums.Emails;
import utils.enums.MailRuData;
import utils.exceptions.NoSuchEMailException;

public class ActionSteps extends Runner {

    private MailRuCommonPage commonPage = setup.initPage(MailRuCommonPage.class);
    private FolderElementsPage folderElementsPage = setup.initPage(FolderElementsPage.class);

    @When("^I login as '([^\"]*)'$")
    public void login(Credentials user) {
        commonPage.login(user);
    }

    @When("^I open create new e-mail window$")
    public void iOpenCreateNewEMailWindow() {
        folderElementsPage.openNewEmailWindow();
    }

    @And("^Fill e-mail with '([^\"]*)' data$")
    public void fillEMailWithData(Emails emailData) {
        folderElementsPage.fillNewEmailData(emailData);
    }

    @And("^Click '([^\"]*)' button$")
    public void clickNewEmailActionButton(MailRuData mailOptions) {
        folderElementsPage.newEmailOptions(mailOptions);
    }

    @And("^Open '([^\"]*)' folder$")
    public void openFolder(MailRuData folderName) {
        folderElementsPage.openFolder(folderName);
    }

    @When("^I open '([^\"]*)'$")
    public void iOpenEmail(Emails emailName) throws NoSuchEMailException {
        folderElementsPage.openEmail(emailName);
    }
}
