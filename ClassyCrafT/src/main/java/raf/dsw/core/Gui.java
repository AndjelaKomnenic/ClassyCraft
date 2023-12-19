package raf.dsw.core;

public interface Gui {
    void start();

    void disableUndoAction();
    void disableRedoAction();

    void enableUndoAction();
    void enableRedoAction();

}
