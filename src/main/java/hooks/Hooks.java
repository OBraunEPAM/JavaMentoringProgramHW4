package hooks;

import config.Setup;
import org.testng.annotations.BeforeClass;
import page_objects.FolderElementsPage;
import page_objects.MailRuCommonPage;

import static org.openqa.selenium.remote.BrowserType.CHROME;

public class Hooks extends Setup {

    protected MailRuCommonPage commonPage;
    protected FolderElementsPage folderElementsPage;

    @BeforeClass(alwaysRun = true)
    protected void initPages() throws Exception {
        setupDriver(CHROME);
        commonPage = initPage(MailRuCommonPage.class);
        folderElementsPage = initPage(FolderElementsPage.class);
    }
}
