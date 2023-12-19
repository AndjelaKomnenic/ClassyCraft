package raf.dsw.view;

import javax.swing.*;

public class MyToolBar extends JToolBar {
    public MyToolBar() {
        super(HORIZONTAL);
        setFloatable(false);

        add(MainFrame.getInstance().getActionManager().getExitAction());
        add(MainFrame.getInstance().getActionManager().getAboutUsAction());
        add(MainFrame.getInstance().getActionManager().getAddNode());
        add(MainFrame.getInstance().getActionManager().getRemoveNode());
        add(MainFrame.getInstance().getActionManager().getRenameAction());
        add(MainFrame.getInstance().getActionManager().getAddAuthorAction());
        add(MainFrame.getInstance().getActionManager().getPackagePackageAction());
        add(MainFrame.getInstance().getActionManager().getUndoAction());
        add(MainFrame.getInstance().getActionManager().getRedoAction());
    }
}