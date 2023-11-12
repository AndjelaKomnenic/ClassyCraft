package raf.dsw.controller;

import raf.dsw.classyrepository.composite.ClassyNodeComposite;
import raf.dsw.core.ApplicationFramework;
import raf.dsw.message.PossibleErrors;
import raf.dsw.tree.model.ClassyTreeItem;
import raf.dsw.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AddNodeAction extends AbstractClassyAction {
    public AddNodeAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/add.png")); // za sada ima exit slicicu posle treba dodati odgovarajucu!
        putValue(NAME, "AddChild");
        putValue(SHORT_DESCRIPTION, "AddChild");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = (ClassyTreeItem) MainFrame.getInstance().getClassyTree().getSelectedNode();
        if(selected == null)
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(PossibleErrors.NO_NODE_SELECTED_FOR_ADD_CHILD);
        if (!(selected.getClassyNode() instanceof ClassyNodeComposite)){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(PossibleErrors.CANT_ADD_CHILD);
            return;
        }
        MainFrame.getInstance().getClassyTree().addChild(selected);

    }
}
