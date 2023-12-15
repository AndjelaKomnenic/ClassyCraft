package raf.dsw.controller.sidebar;

import raf.dsw.classyrepository.implementation.Diagram;
import raf.dsw.controller.AbstractClassyAction;
import raf.dsw.state.StateManager;
import raf.dsw.view.MainFrame;
import raf.dsw.workspace.WorkSpaceImplementation;
import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;

import java.awt.event.ActionEvent;

public class MoveAction extends AbstractClassyAction {
    public MoveAction(){
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.CTRL_DOWN_MASK, KeyEvent.VK_PLUS));
        putValue(SMALL_ICON, loadIcon("/images/arrows.png"));
        putValue(NAME, "Move");
        putValue(SHORT_DESCRIPTION, "Move");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PackageView packageView = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getPackageView();
        if(packageView == null)
            return;
        if(packageView.getTabbedPane() == null)
            return;
        if(packageView.getTabbedPane().getSelectedComponent() == null)
            return;
        Diagram currDiagram = ((DiagramView) packageView.getTabbedPane().getSelectedComponent()).getDiagram();
        if(currDiagram == null)
            return;
        MainFrame.getInstance().getWorkspace().getPackageView().startMoveState();
    }
}