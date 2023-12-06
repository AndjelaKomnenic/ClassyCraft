package raf.dsw.controller.sidebar;

import raf.dsw.classyrepository.implementation.Diagram;
import raf.dsw.controller.AbstractClassyAction;
import raf.dsw.core.ApplicationFramework;
import raf.dsw.message.PossibleErrors;
import raf.dsw.popUps.PopUpChooseIC;
import raf.dsw.popUps.PopUpSetUpParameters;
import raf.dsw.state.StateManager;
import raf.dsw.tree.model.ClassyTreeItem;
import raf.dsw.view.MainFrame;
import raf.dsw.workspace.WorkSpaceImplementation;
import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewInterClassAction extends AbstractClassyAction {

    public NewInterClassAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/add.png"));
        putValue(NAME, "New Inter Class");
        putValue(SHORT_DESCRIPTION, "New Inter Class");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StateManager stateManager = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getPackageView().getStateManager();
        stateManager.setNewDodavanjeState();
        PackageView packageView = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getPackageView();
        /*if(packageView == null){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(PossibleErrors.NO_NODE_SELECTED_FOR_ADD_CHILD);
            return;
        }
        Diagram currDiagram = ((DiagramView) packageView.getTabbedPane().getSelectedComponent()).getDiagram();
        if(currDiagram == null){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(PossibleErrors.NO_NODE_SELECTED_FOR_ADD_CHILD);
            return;
        }*/
        PopUpChooseIC popUp = new PopUpChooseIC();

    }
}
