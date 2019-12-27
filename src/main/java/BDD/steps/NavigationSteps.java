package BDD.steps;

import BDD.Runner;
import cucumber.api.java.en.Given;
import page_objects.MailRuCommonPage;
import utils.enums.MailRuData;

public class NavigationSteps extends Runner {

    MailRuCommonPage commonPage = setup.initPage(MailRuCommonPage.class);

    @Given("^I open '([^\"]*)' site$")
    public void openSite(MailRuData site) {
        commonPage.open(site.value);
    }
}
