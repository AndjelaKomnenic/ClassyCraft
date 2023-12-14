package raf.dsw.state;


import raf.dsw.components.DiagramElement;

import raf.dsw.components.Enum;

import raf.dsw.components.InterClass;
import raf.dsw.components.Interfejs;
import raf.dsw.components.Klasa;
import raf.dsw.paint.*;

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
        double scaledX = (x - currDiagramView.getTranslateX()) / currDiagramView.getScaling();
        double scaledY = (y - currDiagramView.getTranslateY()) / currDiagramView.getScaling();

        popUp.getSelectedElement().setX(scaledX);
        popUp.getSelectedElement().setY(scaledY);
        //popUp.getSelectedElement().setX((x - currDiagramView.getTranslateY()) / currDiagramView.getScaling());   // ovo radi ovde al mora da se sredi nije lepo...
        //popUp.getSelectedElement().setY((y - currDiagramView.getTranslateY()) / currDiagramView.getScaling());   // neka ideja sa clickcoordinates tracker?   --> cuva poslednje klik na dijagramview i njega onda koristimo u absfact
        currDiagramView.getDiagram().addChild(popUp.getSelectedElement());

        ElementPainter elementPainter;
        if (popUp.getSelectedElement() instanceof Klasa){
            elementPainter = new ClassPainter(popUp.getSelectedElement(), popUp);
            pkg.addPainterForCurrent(elementPainter);
        }else if (popUp.getSelectedElement() instanceof Interfejs){
            elementPainter = new InterfacePainter(popUp.getSelectedElement(), popUp);
            pkg.addPainterForCurrent(elementPainter);
            //System.out.println("Usao");
        }else if (popUp.getSelectedElement() instanceof Enum){
            elementPainter = new EnumPainter(popUp.getSelectedElement(), popUp);
            pkg.addPainterForCurrent(elementPainter);
        }

        //ClassPainter classPainter = new ClassPainter(diagramElementInstance, popUp.getSelectedElement());


        //System.out.println("Radi state");

        /*PopUpChooseIC popUp = new PopUpChooseIC(this);
        this.x = x;
        this.y = y;
        //String nameString = JOptionPane.showInputDialog(new JFrame(), "Unesite pojam", "Pojam", JOptionPane.PLAIN_MESSAGE);
        System.out.println("da");*/

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
    @Override
    public void neispravnoCrtanje() {}

    @Override
    public void duplikacija(DiagramElement de, int x, int y, int w, int h, PackageView pkg) {

    }
}
