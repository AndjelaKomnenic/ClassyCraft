package raf.dsw.controller;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.controller.sidebar.*;
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
    private NewConnectionAction newConnectionAction;
    private DeleteRightAction deleteRightAction;
    private SelectAction selectAction;
    private MoveAction moveAction;
    private AddClassContentAction addClassContentAction;

    private ZoomInAction zoomInAction;
    private ZoomOutAction zoomOutAction;

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
        newConnectionAction = new NewConnectionAction();
        deleteRightAction = new DeleteRightAction();
        selectAction = new SelectAction();
        moveAction = new MoveAction();
        addClassContentAction = new AddClassContentAction();
        zoomInAction = new ZoomInAction();
        zoomOutAction = new ZoomOutAction();
    }

}