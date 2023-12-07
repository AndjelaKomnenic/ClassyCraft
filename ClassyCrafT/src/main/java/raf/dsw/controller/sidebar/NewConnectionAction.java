package raf.dsw.controller.sidebar;

import raf.dsw.controller.AbstractClassyAction;
import raf.dsw.popUps.PopUpChooseCon;
import raf.dsw.popUps.PopUpChooseIC;
import raf.dsw.state.StateManager;
import raf.dsw.view.MainFrame;
import raf.dsw.workspace.WorkSpaceImplementation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewConnectionAction extends AbstractClassyAction {
    public NewConnectionAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/connection.png"));
        putValue(NAME, "New Connection");
        putValue(SHORT_DESCRIPTION, "New Connection");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StateManager stateManager = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getPackageView().getStateManager();
        stateManager.setNewDodavanjeVezeState();
        //PopUpChooseCon popCon = new PopUpChooseCon();
    }
}
