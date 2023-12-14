package raf.dsw.state;

import raf.dsw.classyrepository.implementation.Diagram;
import raf.dsw.components.DiagramElement;
import raf.dsw.components.InterClass;
import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;

import java.awt.*;

public interface  State {
    public abstract void misKliknut(int x, int y, DiagramView currDiagramView, PackageView pkg);
    public abstract void misOtpusten(int x, int y, DiagramView currDiagram, PackageView pkg);
    public abstract void misPrevucen(int x, int y, DiagramView currDiagram, PackageView pkg);
    public abstract void zavrsenaSelekcija(DiagramElement inter, PackageView pkg);
    public abstract void neispravnoCrtanje();
    public void duplikacija(DiagramElement de, int x, int y, int w, int h, PackageView pkg);
}
