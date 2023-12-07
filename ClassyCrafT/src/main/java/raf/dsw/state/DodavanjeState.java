package raf.dsw.state;

import raf.dsw.components.DiagramElement;
import raf.dsw.components.InterClass;
import raf.dsw.components.Klasa;

import raf.dsw.paint.ElementPainter;
import raf.dsw.paint.InterClassPainter;

import raf.dsw.popUps.PopUpChooseIC;
import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;

import javax.swing.*;

//klase, interfejsa, enuma
public class DodavanjeState implements State{
    private int x;
    private int y;
    @Override
    public void misKliknut(int x, int y, DiagramView currDiagramView, PackageView pkg) {
        PopUpChooseIC popUp = new PopUpChooseIC(this);
        this.x = x;
        this.y = y;
        //String nameString = JOptionPane.showInputDialog(new JFrame(), "Unesite pojam", "Pojam", JOptionPane.PLAIN_MESSAGE);
        System.out.println("da");
    }

    @Override
    public void misOtpusten(int x, int y, DiagramView currDiagramView, PackageView pkg) {

    }
    @Override
    public void misPrevucen(int x, int y, DiagramView currDiagramView, PackageView pkg) {
    }
    public void zavrsenaSelekcija(DiagramElement novi, PackageView pkg){
        InterClass noviElement = (InterClass) novi;
        noviElement.setX(x);
        noviElement.setY(y);
        ElementPainter elementPainter = new InterClassPainter(noviElement);
        pkg.addPainterForCurrent(elementPainter);
        //currDiagramView.getParent().addPainterForCurrent(elementPainter);
    }
}
