package raf.dsw.controller.sidebar;

import raf.dsw.controller.AbstractClassyAction;
import raf.dsw.state.StateManager;
import raf.dsw.view.MainFrame;
import raf.dsw.workspace.WorkSpaceImplementation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AddClassContentAction extends AbstractClassyAction {
    public AddClassContentAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/rename.png"));
        putValue(NAME, "Add Class Content");
        putValue(SHORT_DESCRIPTION, "Add Class Content");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StateManager stateManager = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getPackageView().getStateManager();
        stateManager.setNewDodavanjeSadrzajaState();
    }
}
