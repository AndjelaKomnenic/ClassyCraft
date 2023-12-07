package raf.dsw.state;

import raf.dsw.classyrepository.implementation.Diagram;
import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;

import java.awt.*;

public class BrisanjeState implements State{

    @Override
    public void misKliknut(int x, int y, DiagramView currDiagram, PackageView pkg) {
        System.out.println("Brise");
    }

    @Override
    public void misOtpusten(int x, int y, DiagramView currDiagram) {

    }

    @Override
    public void misPrivucen(int x, int y, DiagramView currDiagram) {

    }
}
