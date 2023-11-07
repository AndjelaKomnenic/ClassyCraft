package raf.dsw.controller;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.core.ApplicationFramework;

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
    }

}