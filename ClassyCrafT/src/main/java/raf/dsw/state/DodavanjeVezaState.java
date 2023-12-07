package raf.dsw.state;

import raf.dsw.classyrepository.implementation.Diagram;
import raf.dsw.components.*;
import raf.dsw.paint.ConnectionPainter;
import raf.dsw.paint.ElementPainter;
import raf.dsw.paint.InterClassPainter;
import raf.dsw.popUps.PopUpChooseCon;
import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;

import javax.swing.*;
import java.awt.*;

public class DodavanjeVezaState implements State{

    private int pocetnaTackaX;
    private int pocetnaTackaY;
    private int zavrsnaTackaX;
    private int zavrsnaTackaY;
    private Connection connection;
    private PackageView pkg;
    private DiagramView diag;

    @Override
    public void misKliknut(int x, int y, DiagramView currDiagram, PackageView pkg) {
        pocetnaTackaY = y;
        pocetnaTackaX = x;
        this.pkg = pkg;
        this.diag = diag;
        connection = new TemporaryConnection("temp", currDiagram.getDiagram(), null);
        connection.tempSetFrom(pocetnaTackaX, pocetnaTackaY);
        connection.tempSetTo(pocetnaTackaX, pocetnaTackaY);
        ElementPainter elementPainter = new ConnectionPainter(connection);
        pkg.addPainterForCurrent(elementPainter);
    }

    @Override
    public void misOtpusten(int x, int y, DiagramView currDiagram, PackageView pkg) {
        PopUpChooseCon popCon = new PopUpChooseCon(pocetnaTackaX, pocetnaTackaY, zavrsnaTackaX, zavrsnaTackaY, this);
    }

    @Override
    public void misPrevucen(int x, int y, DiagramView currDiagram, PackageView pkg) {
        zavrsnaTackaX = x;
        zavrsnaTackaY = y;
        connection.tempSetTo(zavrsnaTackaX, zavrsnaTackaY);
        pkg.repaint();
    }

    @Override
    public void zavrsenaSelekcija(DiagramElement inter, PackageView pkg) {
        connection.setFromX(0);
        connection.setFromY(0);
        connection.setToY(0);
        connection.setToX(0);
        Connection c = (Connection) inter;
        ElementPainter conPain = new ConnectionPainter(c);
        pkg.addPainterForCurrent(conPain);
    }
}
