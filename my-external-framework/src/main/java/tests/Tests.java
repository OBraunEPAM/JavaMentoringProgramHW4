package tests;

import config.Setup;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import step_defs.CommonPageStepDefs;
import step_defs.FolderPageStepDefs;

import static utils.enums.SetupEnums.WEB_DRIVER;

public class Tests extends Setup {

//    @BeforeSuite(alwaysRun = true)
//    public void setDriverPath() {
//
//    }

    @BeforeSuite(alwaysRun = true)
    private void setWebDriverType() {
        // parameter could be either WEB_DRIVER or REMOTE_WEB_DRIVER. In last case Selenium grid must be running
        setupDriver(WEB_DRIVER);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        getDriver().quit();
    }

    public CommonPageStepDefs commonPage = new CommonPageStepDefs();
    public FolderPageStepDefs folderElementsPage = new FolderPageStepDefs();

    protected CommonPageStepDefs onCommonPage() {
        return commonPage;
    }

    protected FolderPageStepDefs onFolderElementsPage() {
        return folderElementsPage;
    }
}
