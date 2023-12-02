package raf.dsw.controller.topbar;

import raf.dsw.classyrepository.implementation.Package;
import raf.dsw.controller.AbstractClassyAction;
import raf.dsw.core.ApplicationFramework;
import raf.dsw.message.PossibleErrors;
import raf.dsw.tree.model.ClassyTreeItem;
import raf.dsw.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class PackagePackageAction extends AbstractClassyAction {
    public PackagePackageAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/pp.png"));
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
