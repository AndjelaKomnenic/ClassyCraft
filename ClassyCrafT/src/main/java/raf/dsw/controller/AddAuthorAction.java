package raf.dsw.controller;

import raf.dsw.classyrepository.composite.Project;
import raf.dsw.core.ApplicationFramework;
import raf.dsw.message.PossibleErrors;
import raf.dsw.tree.model.ClassyTreeItem;
import raf.dsw.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AddAuthorAction extends AbstractClassyAction{
    public AddAuthorAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/author.png")); // za sada ima exit slicicu posle treba dodati odgovarajucu!
        putValue(NAME, "AddAuthor");
        putValue(SHORT_DESCRIPTION, "AddAuthor");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectedNode();
        if(selected.getClassyNode() instanceof Project) {
            String author = JOptionPane.showInputDialog("Unesite autora za projekat");
            ((Project) selected.getClassyNode()).setAuthor(author);
        }
        else{
            generateMessage(PossibleErrors.AUTHOR_ACTION_FOR_NOT_PROJECT);
        }
    }
    public void generateMessage(PossibleErrors pe){
        ApplicationFramework.getInstance().getMessageGenerator().createMessage(pe);

    }
}
