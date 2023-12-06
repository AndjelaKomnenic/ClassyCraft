package raf.dsw.controller.sidebar;

import raf.dsw.controller.AbstractClassyAction;
import raf.dsw.view.MainFrame;
import raf.dsw.workspace.WorkSpaceImplementation;
import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ZoomOutAction extends AbstractClassyAction {
    public ZoomOutAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.CTRL_DOWN_MASK, KeyEvent.VK_MINUS));
        putValue(SMALL_ICON, loadIcon("/images/zoomOut.png"));
        putValue(NAME, "Zoom Out");
        putValue(SHORT_DESCRIPTION, "Zoom Out");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PackageView packageView = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getPackageView();
        int selectedDiagramIndex = packageView.getTabbedPane().getSelectedIndex();
        DiagramView currDiagramView = packageView.getTabs().get(selectedDiagramIndex);
        currDiagramView.zoomOut();
    }
}
