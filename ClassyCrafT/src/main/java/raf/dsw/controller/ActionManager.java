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
    private DuplicateAction duplicateAction;

    private ZoomInAction zoomInAction;
    private ZoomOutAction zoomOutAction;

    private UndoAction undoAction;
    private RedoAction redoAction;

    private ScreenshotAction screenshotAction;
    private SaveProjectAction saveProjectAction;
    private SaveAsProjectAction saveAsProjectAction;
    private OpenProjectAction openProjectAction;

    private SaveTemplateAction saveTemplateAction;
    private OpenTemplateAction openTemplateAction;

    private CodeGeneratorAction codeGeneratorAction;
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
        duplicateAction = new DuplicateAction();
        zoomInAction = new ZoomInAction();
        zoomOutAction = new ZoomOutAction();
        undoAction = new UndoAction();
        redoAction = new RedoAction();
        screenshotAction = new ScreenshotAction();
        saveProjectAction = new SaveProjectAction();
        saveAsProjectAction = new SaveAsProjectAction();
        openProjectAction = new OpenProjectAction();
        saveTemplateAction = new SaveTemplateAction();
        openTemplateAction = new OpenTemplateAction();
        codeGeneratorAction = new CodeGeneratorAction();
    }

}