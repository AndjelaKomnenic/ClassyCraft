package raf.dsw.state;

import raf.dsw.components.Klasa;

import raf.dsw.paint.ElementPainter;
import raf.dsw.paint.InterClassPainter;

import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;

import javax.swing.*;

//klase, interfejsa, enuma
public class DodavanjeState implements State{
    @Override
    public void misKliknut(int x, int y, DiagramView currDiagramView, PackageView pkg) {
        String nameString = JOptionPane.showInputDialog(new JFrame(), "Unesite pojam", "Pojam", JOptionPane.PLAIN_MESSAGE);
        Klasa newKlasa = new Klasa(nameString, currDiagramView.getDiagram(),x, y);
        currDiagramView.getDiagram().addChild(newKlasa);

        ElementPainter elementPainter = new InterClassPainter(newKlasa);
        //currDiagramView.getParent().addPainterForCurrent(elementPainter);

        pkg.addPainterForCurrent(elementPainter);

        System.out.println("da");
    }

    @Override
    public void misOtpusten(int x, int y, DiagramView currDiagramView) {

    }
    @Override
    public void misPrivucen(int x, int y, DiagramView currDiagramView) {


    }
}
