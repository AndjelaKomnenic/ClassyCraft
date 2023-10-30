package view;

import controller.ExitAction;

import javax.swing.*;

public class MyToolBar extends JToolBar {
    public MyToolBar() {
        super(HORIZONTAL);
        setFloatable(false);

        ExitAction ea = new ExitAction();
        add(ea);
    }
}
