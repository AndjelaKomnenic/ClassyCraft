package raf.dsw.controller;

import raf.dsw.composite.Package;
import raf.dsw.core.ApplicationFramework;
import raf.dsw.message.PossibleErrors;
import raf.dsw.tree.model.ClassyTreeItem;
import raf.dsw.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class PackagePackageAction extends AbstractClassyAction{
    public PackagePackageAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/pp.png")); // za sada ima exit slicicu posle treba dodati odgovarajucu!
        putValue(NAME, "PackagePackage");
        putValue(SHORT_DESCRIPTION, "PackagePackage");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = (ClassyTreeItem) MainFrame.getInstance().getClassyTree().getSelectedNode();
        if(selected.getClassyNode() instanceof Package) {
            MainFrame.getInstance().getClassyTree().addPP(selected);
        }
        else{
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(PossibleErrors.PP_ACTION_FOR_NOTPP);
        }
    }
}
