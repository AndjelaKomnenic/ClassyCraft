package raf.dsw.controller;

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
        //String nodeName = JOptionPane.showInputDialog("Unesite naziv novog elementa");
        MainFrame.getInstance().getClassyTree().addChild(selected);
    }
}
