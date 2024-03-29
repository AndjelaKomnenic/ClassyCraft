package raf.dsw.view;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter
@Setter
public class MySideBar extends JToolBar {
    public MySideBar() {
        super(VERTICAL);
        setFloatable(false);

        add(MainFrame.getInstance().getActionManager().getNewInterClassAction());
        add(MainFrame.getInstance().getActionManager().getNewConnectionAction());
        add(MainFrame.getInstance().getActionManager().getDeleteRightAction());
        add(MainFrame.getInstance().getActionManager().getSelectAction());
        add(MainFrame.getInstance().getActionManager().getMoveAction());
        add(MainFrame.getInstance().getActionManager().getAddClassContentAction());
        add(MainFrame.getInstance().getActionManager().getDuplicateAction());
        add(MainFrame.getInstance().getActionManager().getZoomInAction());
        add(MainFrame.getInstance().getActionManager().getZoomOutAction());
    }
}