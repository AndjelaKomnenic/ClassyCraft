package raf.dsw.tree.controller;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.composite.Package;
import raf.dsw.tree.model.ClassyTreeItem;
import raf.dsw.view.MainFrame;
import raf.dsw.workspace.IWorkspace;
import raf.dsw.workspace.WorkSpaceImplementation;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@Getter
@Setter
public class MouseControl implements MouseListener {
    private JTree tree;

    public MouseControl(JTree tree) {
        this.tree = tree;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        int selected = tree.getRowForLocation(e.getX(), e.getY());

        if (selected != -1 && e.getClickCount() == 2){
            ClassyTreeItem selectWrapper = (ClassyTreeItem) tree.getLastSelectedPathComponent();
            ClassyNode selectedNode = selectWrapper.getClassyNode();

            IWorkspace workspace = MainFrame.getInstance().getWorkspace();
            if (workspace instanceof WorkSpaceImplementation && selectedNode instanceof Package)
                ((WorkSpaceImplementation) workspace).getPackageView().updateWorkspace(selectedNode);

            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());

        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
