package raf.dsw.controller.sidebar;

import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.implementation.Diagram;
import raf.dsw.components.Connection;
import raf.dsw.components.DiagramElement;
import raf.dsw.components.InterClass;
import raf.dsw.controller.AbstractClassyAction;
import raf.dsw.paint.ElementPainter;
import raf.dsw.popUps.PopUpChooseCon;
import raf.dsw.state.StateManager;
import raf.dsw.tree.ClassyTree;
import raf.dsw.tree.model.ClassyTreeItem;
import raf.dsw.view.MainFrame;
import raf.dsw.workspace.WorkSpaceImplementation;
import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteRightAction extends AbstractClassyAction {
    public DeleteRightAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/remove.png"));
        putValue(NAME, "Delete");
        putValue(SHORT_DESCRIPTION, "Delete");
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
        MainFrame.getInstance().getWorkspace().getPackageView().startBrisanjeState();
        for(DiagramElement ep: packageView.getSelectedComponents()){
            ep.setSelected(false);
            packageView.removePainter(packageView.getPainter(ep));
            if(ep instanceof InterClass) {
                for (Connection c : ((InterClass)ep).getListaVeza()) {
                    if (packageView.getPainter(c) != null) {
                        packageView.removePainter(packageView.getPainter(c));
                        ClassyTreeItem treeItemZaBrsianje = MainFrame.getInstance().getClassyTree().getRoot().findClassyTreeItem(c);
                        if (treeItemZaBrsianje != null)
                            MainFrame.getInstance().getClassyTree().deleteChild(treeItemZaBrsianje);
                        else
                            System.out.println("Nije nadjen");
                    }
                    else{
                    }
                }
            }
            ClassyTreeItem treeItemZaBrsianje = MainFrame.getInstance().getClassyTree().getRoot().findClassyTreeItem(ep);
            if(treeItemZaBrsianje != null)
                MainFrame.getInstance().getClassyTree().deleteChild(treeItemZaBrsianje);
            else
                System.out.println("Nije nadjen");
        }
        packageView.getSelectedComponents().clear();
    }
}