package raf.dsw.controller;

import raf.dsw.classyrepository.composite.ProjectExplorer;
import raf.dsw.core.ApplicationFramework;
import raf.dsw.message.PossibleErrors;
import raf.dsw.tree.model.ClassyTreeItem;
import raf.dsw.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteNodeAction extends AbstractClassyAction{
    public DeleteNodeAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/remove.png")); // za sada ima exit slicicu posle treba dodati odgovarajucu!
        putValue(NAME, "RemoveNode");
        putValue(SHORT_DESCRIPTION, "RemoveNode");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = (ClassyTreeItem) MainFrame.getInstance().getClassyTree().getSelectedNode();
        if(selected.getClassyNode() instanceof ProjectExplorer){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(PossibleErrors.NODE_CANNOT_BE_DELETED);
            return;
        }
        MainFrame.getInstance().getClassyTree().deleteChild(selected);
    }
}
