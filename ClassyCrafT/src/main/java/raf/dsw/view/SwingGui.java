package raf.dsw.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.core.Gui;
import raf.dsw.view.MainFrame;

@Getter
@Setter
public class SwingGui implements Gui {
    private MainFrame instance;

    public SwingGui() {}

    @Override
    public void start() {
        instance = MainFrame.getInstance();
        disableRedoAction();
        disableUndoAction();
        instance.setVisible(true);
    }

    @Override
    public void enableUndoAction() {
        MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(true);
    }

    @Override
    public void disableUndoAction() {
        MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(false);
    }

    @Override
    public void disableRedoAction() {
        MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(false);
    }
    @Override
    public void enableRedoAction() {
        MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(true);
    }
}
