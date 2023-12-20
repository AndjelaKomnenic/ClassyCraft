package raf.dsw.commands;

import raf.dsw.paint.ElementPainter;
import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;

import javax.swing.*;

public class NewClassCommand extends AbstractCommand{
    private PackageView pkgView;
    private DiagramView dgView;
    private ElementPainter thisClassPainter;

    private int x,y;

    public NewClassCommand(PackageView pkgView, DiagramView dgView, int x, int y) {
        this.pkgView = pkgView;
        this.dgView = dgView;
        this.x = x;
        this.y = y;
    }

    @Override
    public void doCommand() {

    }

    @Override
    public void undoCommand() {
        
    }
}
