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
    }
}
