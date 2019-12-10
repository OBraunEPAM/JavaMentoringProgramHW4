package tests;

import config.Setup;
import step_defs.CommonPageStepDefs;
import step_defs.FolderPageStepDefs;

public abstract class Tests extends Setup {

    public CommonPageStepDefs commonPage = new CommonPageStepDefs();
    public FolderPageStepDefs folderElementsPage = new FolderPageStepDefs();

    protected CommonPageStepDefs onCommonPage() {
        return commonPage;
    }

    protected FolderPageStepDefs onFolderElementsPage() {
        return folderElementsPage;
    }
}
