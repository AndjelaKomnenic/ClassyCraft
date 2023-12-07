package raf.dsw.state;

import raf.dsw.classyrepository.implementation.Diagram;
import raf.dsw.components.InterClass;
import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;

import java.awt.*;

public interface  State {

    public abstract void misKliknut(int x, int y, DiagramView currDiagramView, PackageView pkg);
    public abstract void misOtpusten(int x, int y, DiagramView currDiagram);
    public abstract void misPrivucen(int x, int y, DiagramView currDiagram);
    public abstract void zavrsenaSelekcija(InterClass inter);

}
