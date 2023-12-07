package raf.dsw.state;

import raf.dsw.components.InterClass;
import raf.dsw.components.Klasa;
import raf.dsw.paint.ClassPainter;
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

        /*String nameString = JOptionPane.showInputDialog(new JFrame(), "Unesite pojam", "Pojam", JOptionPane.PLAIN_MESSAGE);

        //PopUpChooseIC popUp = new PopUpChooseIC();

        Klasa newKlasa = new Klasa(nameString, currDiagramView.getDiagram(),x, y);
        currDiagramView.getDiagram().addChild(newKlasa);

        ElementPainter elementPainter = new InterClassPainter(newKlasa);
        //currDiagramView.getParent().addPainterForCurrent(elementPainter);

        pkg.addPainterForCurrent(elementPainter);*/

        PopUpChooseIC popUp = new PopUpChooseIC();
        //System.out.println(x + '+' + y);
        popUp.getSelectedElement().setX(x);   // ovo radi ovde al mora da se sredi nije lepo...
        popUp.getSelectedElement().setY(y);   // neka ideja sa clickcoordinates tracker?   --> cuva poslednje klik na dijagramview i njega onda koristimo u absfact
        currDiagramView.getDiagram().addChild(popUp.getSelectedElement());

        ElementPainter elementPainter = new ClassPainter(popUp.getSelectedElement(), popUp);
        //ClassPainter classPainter = new ClassPainter(diagramElementInstance, popUp.getSelectedElement());
        pkg.addPainterForCurrent(elementPainter);

        System.out.println("Radi state");

        /*PopUpChooseIC popUp = new PopUpChooseIC(this);
        this.x = x;
        this.y = y;
        //String nameString = JOptionPane.showInputDialog(new JFrame(), "Unesite pojam", "Pojam", JOptionPane.PLAIN_MESSAGE);
        System.out.println("da");*/

    }

    @Override
    public void misOtpusten(int x, int y, DiagramView currDiagramView) {

    }
    @Override
    public void misPrivucen(int x, int y, DiagramView currDiagramView) {
    }
    public void zavrsenaSelekcija(InterClass noviElement, PackageView pkg){
        noviElement.setX(x);
        noviElement.setY(y);
        ElementPainter elementPainter = new InterClassPainter(noviElement);
        pkg.addPainterForCurrent(elementPainter);
        //currDiagramView.getParent().addPainterForCurrent(elementPainter);

    }
}
