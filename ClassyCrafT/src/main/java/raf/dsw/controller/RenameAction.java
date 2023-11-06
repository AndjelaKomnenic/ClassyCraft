package raf.dsw.controller;

import raf.dsw.composite.ClassyNode;
import raf.dsw.composite.ClassyNodeComposite;
import raf.dsw.tree.model.ClassyTreeItem;
import raf.dsw.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RenameAction extends AbstractClassyAction{
    public RenameAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/rename.png")); // za sada ima exit slicicu posle treba dodati odgovarajucu!
        putValue(NAME, "RenameNode");
        putValue(SHORT_DESCRIPTION, "RenameNode");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = (ClassyTreeItem) MainFrame.getInstance().getClassyTree().getSelectedNode();
        if (!(selected instanceof ClassyTreeItem))
            return;
        boolean ok = true;
        ClassyTreeItem clicked = (ClassyTreeItem) selected;
        String newName = JOptionPane.showInputDialog("Unesite novi naziv elementa");
        ClassyTreeItem parent = (ClassyTreeItem)clicked.getParent();
        for(ClassyNode kid: ((ClassyNodeComposite)parent.getClassyNode()).getChildren()){
            if(kid.getName().equalsIgnoreCase(newName)){
                //pucaj gresku
                ok = false;
            }
        }
        if(ok){
            clicked.setName(newName);
        }
        MainFrame.getInstance().getClassyTree().update();
    }
}
