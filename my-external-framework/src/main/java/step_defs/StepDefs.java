package step_defs;

import config.Setup;
import page_objects.FolderElementsPage;
import page_objects.MailRuCommonPage;

public abstract class StepDefs extends Setup {

    MailRuCommonPage commonPage = initPage(MailRuCommonPage.class);
    FolderElementsPage folderElementsPage = initPage(FolderElementsPage.class);

}
