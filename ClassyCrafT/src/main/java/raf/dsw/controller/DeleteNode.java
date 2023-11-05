package raf.dsw.controller;

import raf.dsw.composite.ClassyNode;
import raf.dsw.composite.ClassyNodeComposite;
import raf.dsw.tree.model.ClassyTreeItem;
import raf.dsw.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteNode extends AbstractClassyAction{
    public DeleteNode(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/remove.png")); // za sada ima exit slicicu posle treba dodati odgovarajucu!
        putValue(NAME, "RemoveNode");
        putValue(SHORT_DESCRIPTION, "RemoveNode");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = (ClassyTreeItem) MainFrame.getInstance().getClassyTree().getSelectedNode();
        ClassyNodeComposite parent = (ClassyNodeComposite) selected.getClassyNode().getParent();
        parent.removeChild(selected.getClassyNode());
        MainFrame.getInstance().getClassyTree().deleteChild(selected);
    }
}
