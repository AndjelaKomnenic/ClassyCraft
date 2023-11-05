package raf.dsw.view;

import raf.dsw.core.Gui;

public class SwingGui implements Gui {
    private MainFrame instance;

    public SwingGui() {
    }

    @Override
    public void start() {
        instance = MainFrame.getInstance();
        instance.setVisible(true);
    }

}
