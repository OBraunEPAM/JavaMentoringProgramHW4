package step_defs;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.enums.Emails;
import utils.enums.MailRuData;
import utils.exceptions.NoSuchEMailException;

import static utils.enums.MailRuData.DRAFT;
import static utils.enums.MailRuData.INBOX;

public class FolderPageStepDefs extends StepDefs {

//    public void openFolder(MailRuData folderName) {
//        folderElementsPage.openFolder(folderName);
//    }

    public void openDraftFolder() {
        folderElementsPage.clickOnDraftFolder();
    }

//    public void createNewEmailAsDraft(Emails autotestEmail) {
//        folderElementsPage.fillNewEmailDataAndSaveAsDraft(autotestEmail);
//    }

    public void openNewEmailForm() {
        folderElementsPage.clickNewEmailButton();
    }

    public void fillNewEmail(Emails autotestEmail) {
        folderElementsPage.fillAddresseeTextfield(autotestEmail.getAddressee());
        folderElementsPage.fillSubjectTextfield(autotestEmail.getSubject());
        folderElementsPage.fillEmailBodyTextfield(autotestEmail.getText());
    }

    public void saveEmailAsDraft() {
        folderElementsPage.clickSaveAsDraftButton();
    }

    public void checkEmailIsInDraftFolder(Emails email) throws NoSuchEMailException {
        folderElementsPage.checkEmailInDraftFolder(email);
    }

    public void checkContentOfEmailInDraft(Emails email) {
        folderElementsPage.openEmailInDraft(email.getSubject());
        folderElementsPage.checkEmailSubject(email.getSubject());
        folderElementsPage.checkEmailText(email.getText());
        folderElementsPage.checkEmailAddresse(email.getAddressee());
    }

    public void closeEmail() {
        folderElementsPage.clickCloseButton();
    }

    //    public void clearFolder(MailRuData... folderName) {
//        for (MailRuData folder:folderName) {
//            openFolder(folder);
//            try {
//                selectAllButton.click();
//            } catch (NoSuchElementException e) {
//                System.out.printf("%s folder is already empty", folder.value);
//                continue;
//            }
//            driverWait.until(ExpectedConditions.visibilityOf(deleteButton));
//            deleteButton.click();
//            if (folder.value.equals(INBOX.value)) {
//                driverWait.until(ExpectedConditions.visibilityOf(newDialogWindow));
//                folderElementsPage.clickElementUsingJS(clearButton);
//            }
//            driverWait.until(ExpectedConditions.visibilityOf(actionToaster));
//            driverWait.until(ExpectedConditions.visibilityOf(emptyFolderLogo));
//        }
//    }

//    public void checkContentOfEmailInDraft(Emails autotestEmail, MailRuData folderName) throws NoSuchEMailException {
//        checkEmailIsInTheFolder(autotestEmail, folderName);
//        getDriver().findElement(By.xpath("//*[text()='" + autotestEmail.getSubject() + "']")).click();
//        if (folderName.value.equals(DRAFT.value)) {
//            driverWait.until(ExpectedConditions.visibilityOf(newEMailTextarea));
//            assertEquals(filledAddresseeTextfield.getText(), autotestEmail.getAddressee());
//            assertEquals(folderElementsPage.getElementAttributeUsingJS("value", subjectTextfield).toString(), autotestEmail.getSubject());
//            assertTrue(newEMailTextarea.getText().contains(autotestEmail.getText()));
//            closeNewEmailWindowButton.click();
//            driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'dimmer')]")));
//        } else {
//            driverWait.until(ExpectedConditions.visibilityOf(eMailBody));
//            assertEquals(getElementAttributeUsingJS("title", eMailInterlocutors.get(0)), autotestEmail.getAddressee());
//            assertEquals(eMailSubjectField.getText(), autotestEmail.getSubject());
//            assertTrue(eMailBody.getText().contains(autotestEmail.getText()));
//        }
//    }

//    public void checkEmailIsInTheFolder(Emails autotestEmail, MailRuData folderName, boolean status) throws NoSuchEMailException {
//        folderElementsPage.openFolder(folderName);
//        folderElementsPage.checkEmail
//        if (status == true) {
//            folderElementsPage.checkEmailIsPresentInTheFolder(autotestEmail, folderName);
//        } else {
//            folderElementsPage.checkEmailIsNotInTheFolder(autotestEmail, folderName);
//        }
//    }
//
//    public void sendEmailFromDraftFolder(Emails autotestEmail) throws NoSuchEMailException {
//        folderElementsPage.findAndOpenEMail(autotestEmail, DRAFT);
//        folderElementsPage.clickOnDraftFolder();
//        sendNewEmailButton.click();
//        driverWait.until(ExpectedConditions.visibilityOf(closeConfirmationWindowButton));
//        closeConfirmationWindowButton.click();
//        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'dimmer')]")));
//    }
}
