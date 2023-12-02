package raf.dsw.state;

import raf.dsw.classyrepository.implementation.Diagram;
import raf.dsw.workspace.view.PackageView;

import java.awt.*;

public abstract class State {

    public abstract void mousePressed(Point e, PackageView packageView, Diagram currDiagram);
    public abstract void mouseReleased(Point e, PackageView packageView, Diagram currDiagram);
    public abstract void mouseDragged(Point e, PackageView packageView, Diagram currDiagram);
}
