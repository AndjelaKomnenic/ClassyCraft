package raf.dsw.state;

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
    }

    @Override
    public void misOtpusten(int x, int y, DiagramView currDiagramView) {

    }
    @Override
    public void misPrivucen(int x, int y, DiagramView currDiagramView) {


    }
}
