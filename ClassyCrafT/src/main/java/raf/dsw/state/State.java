package raf.dsw.state;

import raf.dsw.classyrepository.implementation.Diagram;
import raf.dsw.components.DiagramElement;
import raf.dsw.components.InterClass;
import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;

import java.awt.*;

public interface  State {
     void misKliknut(int x, int y, DiagramView currDiagramView, PackageView pkg);
     void misOtpusten(int x, int y, DiagramView currDiagram, PackageView pkg);
     void misPrevucen(int x, int y, DiagramView currDiagram, PackageView pkg);
     void zavrsenaSelekcija(DiagramElement inter, PackageView pkg);
     void neispravnoCrtanje();
}
