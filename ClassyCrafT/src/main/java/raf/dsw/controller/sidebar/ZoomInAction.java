package raf.dsw.controller.sidebar;

import raf.dsw.classyrepository.implementation.Diagram;
import raf.dsw.controller.AbstractClassyAction;
import raf.dsw.view.MainFrame;
import raf.dsw.workspace.WorkSpaceImplementation;
import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ZoomInAction extends AbstractClassyAction {
    public ZoomInAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.CTRL_DOWN_MASK, KeyEvent.VK_PLUS));
        putValue(SMALL_ICON, loadIcon("/images/zoomIn.png"));
        putValue(NAME, "Zoom In");
        putValue(SHORT_DESCRIPTION, "Zoom In");
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
        /*PackageView packageView = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getPackageView();
        int selectedDiagramIndex = packageView.getTabbedPane().getSelectedIndex();
        DiagramView currDiagramView = packageView.getTabs().get(selectedDiagramIndex);
        currDiagramView.zoomIn();*/
        MainFrame.getInstance().getWorkspace().getPackageView().startZoomInState();
    }
}
