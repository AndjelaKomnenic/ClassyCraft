package raf.dsw.controller.topbar;

import raf.dsw.controller.AbstractClassyAction;
import raf.dsw.view.MainFrame;
import raf.dsw.workspace.WorkSpaceImplementation;
import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;
import raf.dsw.commands.CommandManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RedoAction extends AbstractClassyAction {

    public RedoAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
        putValue(MNEMONIC_KEY, KeyEvent.VK_R);
        putValue(SMALL_ICON, loadIcon("/images/redo.png"));
        putValue(NAME, "Redo");
        putValue(SHORT_DESCRIPTION, "Redo");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PackageView currPv = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getPackageView();
        DiagramView currDv = (DiagramView) currPv.getTabbedPane().getSelectedComponent();
        currDv.getCommandManager().doCommand();
    }
}
