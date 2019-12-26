package BDD.steps;

import BDD.Runner;
import cucumber.api.java.en.Then;
import page_objects.FolderElementsPage;
import page_objects.MailRuCommonPage;
import utils.enums.Emails;
import utils.enums.MailRuData;
import utils.exceptions.NoSuchEMailException;

public class AssertionSteps extends Runner{

    private MailRuCommonPage commonPage = setup.initPage(MailRuCommonPage.class);
    private FolderElementsPage folderElementsPage = setup.initPage(FolderElementsPage.class);

    @Then("^Title should be '([^\"]*)'$")
    public void titleShouldBe(MailRuData title) {
        commonPage.checkLoginIsSuccessful(title);
    }

    @Then("^'([^\"]*)' should be presented there$")
    public void shouldBePresented(Emails emailName) throws NoSuchEMailException {
        folderElementsPage.checkEmailIsInFolder(emailName);
    }

    @Then("^Content of '([^\"]*)' should match sent e-mail$")
    public void checkContentOfEmail(Emails emailName) {
        folderElementsPage.checkContentOfEmailInboxFolder(emailName);
    }
}
