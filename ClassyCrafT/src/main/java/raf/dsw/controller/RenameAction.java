package raf.dsw.controller;

import raf.dsw.classyrepository.composite.ClassyNodeComposite;
import raf.dsw.core.ApplicationFramework;
import raf.dsw.message.PossibleErrors;
import raf.dsw.tree.model.ClassyTreeItem;
import raf.dsw.view.MainFrame;
import raf.dsw.classyrepository.composite.ClassyNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;


public class RenameAction extends AbstractClassyAction{
    public RenameAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/rename.png"));
        putValue(NAME, "RenameNode");
        putValue(SHORT_DESCRIPTION, "RenameNode");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selectedWrapper = MainFrame.getInstance().getClassyTree().getSelectedNode();

        if(selectedWrapper == null){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(PossibleErrors.NODE_NOT_SELECTED);
            return;
        }
        ClassyNode selected = selectedWrapper.getClassyNode();

        String newName = JOptionPane.showInputDialog(new JFrame(), "Novo ime: ", "Izmeni ime komponente", JOptionPane.PLAIN_MESSAGE);
        if(newName.trim().equals("")){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(PossibleErrors.NAME_CANNOT_BE_EMPTY);
            return;
        } else if(((ClassyNodeComposite)selected.getParent()).cotainsSameNameComponent(newName)){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(PossibleErrors.EXISTS_SAME_NAME_COMPONENT);
            return;
        }
        selected.setName(newName);
        MainFrame.getInstance().getClassyTree().update();
    }
}
