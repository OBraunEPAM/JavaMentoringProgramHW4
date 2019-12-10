package step_defs;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.enums.Credentials;

import static utils.enums.MailRuData.MAIL_RU_TITLE;
import static utils.enums.MailRuData.MAIL_RU_URL;

public class CommonPageStepDefs extends StepDefs {

    public void login(Credentials autotestUser) {
        commonPage.open(MAIL_RU_URL.value);
        commonPage.checkTitle(MAIL_RU_TITLE);
        commonPage.fillLoginTextfield(autotestUser);
        //commonPage.fillTextfieldWithData(usernameTextfield, autotestUser.getUsername().substring(0, autotestUser.getUsername().indexOf("@")));
        commonPage.pressEnterOnLoginForm();
        commonPage.waitUntilPasswordFieldIsVisible();
        commonPage.filldPasswordTextfield(autotestUser);
        commonPage.pressEnterOnPasswordForm();
        commonPage.checkLoginIsSuccessful();
    }

    public void logOff() {
        commonPage.logOff();
    }

}
