package raf.dsw.state;

import raf.dsw.classyrepository.implementation.Diagram;
import raf.dsw.components.InterClass;
import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;

import java.awt.*;

public class BrisanjeState implements State{

    @Override
    public void misKliknut(int x, int y, DiagramView currDiagram, PackageView pkg) {

    }

    @Override
    public void misOtpusten(int x, int y, DiagramView currDiagram, PackageView pkg) {

    }

    @Override
    public void misPrevucen(int x, int y, DiagramView currDiagram, PackageView pkg) {

    }
    public void zavrsenaSelekcija(InterClass noviElement, PackageView pkg){}
}
