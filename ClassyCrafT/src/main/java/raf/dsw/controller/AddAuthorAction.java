package raf.dsw.controller;

import raf.dsw.composite.Project;
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
        ClassyTreeItem selected = (ClassyTreeItem) MainFrame.getInstance().getClassyTree().getSelectedNode();
        if(selected.getClassyNode() instanceof Project) {
            String author = JOptionPane.showInputDialog("Unesite autora za projekat");
            ((Project) selected.getClassyNode()).setAuthor(author);
        }
        else{
            JOptionPane.showMessageDialog(null, "Postavljanje autora je moguce samo za projekat", "Obavestenje", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
