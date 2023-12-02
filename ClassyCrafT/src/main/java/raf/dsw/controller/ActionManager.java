package raf.dsw.controller;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.controller.sidebar.NewInterClassAction;
import raf.dsw.controller.topbar.*;

@Getter
@Setter
public class ActionManager {
    private ExitAction exitAction;
    private AboutUs aboutUsAction;
    private AddNodeAction addNode;
    private DeleteNodeAction removeNode;
    private RenameAction renameAction;
    private AddAuthorAction addAuthorAction;
    private PackagePackageAction packagePackageAction;

    private NewInterClassAction newInterClassAction;

    public ActionManager() {
        initialiseActions();
    }

    private void initialiseActions(){
        exitAction = new ExitAction();
        aboutUsAction = new AboutUs();
        addNode = new AddNodeAction();
        removeNode = new DeleteNodeAction();
        renameAction = new RenameAction();
        addAuthorAction = new AddAuthorAction();
        packagePackageAction = new PackagePackageAction();
        newInterClassAction = new NewInterClassAction();
    }

}