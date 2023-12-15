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
import java.util.List;

//klase, interfejsa, enuma
public class DodavanjeState implements State{
    private int x;
    private int y;
    @Override
    public void misKliknut(int x, int y, DiagramView currDiagramView, PackageView pkg) {

        PopUpChooseIC popUp = new PopUpChooseIC();
        if(popUp.getSelectedElement() == null) {
            return;
        }
        if(popUp.getSelectedElement().getName() != "") {
            double scaledX = (x - currDiagramView.getTranslateX()) / currDiagramView.getScaling();
            double scaledY = (y - currDiagramView.getTranslateY()) / currDiagramView.getScaling();
            popUp.getSelectedElement().setX(scaledX);
            popUp.getSelectedElement().setY(scaledY);
            currDiagramView.getDiagram().addChild(popUp.getSelectedElement());
            ElementPainter elementPainter;
            if (popUp.getSelectedElement() instanceof Klasa) {
                elementPainter = new ClassPainter(popUp.getSelectedElement(), popUp);
                pkg.addPainterForCurrent(elementPainter);
            } else if (popUp.getSelectedElement() instanceof Interfejs) {
                elementPainter = new InterfacePainter(popUp.getSelectedElement(), popUp);
                pkg.addPainterForCurrent(elementPainter);
                //System.out.println("Usao");
            } else if (popUp.getSelectedElement() instanceof Enum) {
                elementPainter = new EnumPainter(popUp.getSelectedElement(), popUp);
                pkg.addPainterForCurrent(elementPainter);
            }
        }
        else{


        }

    }

    @Override
    public void misOtpusten(int x, int y, DiagramView currDiagramView, PackageView pkg) {

    }
    @Override
    public void misPrevucen(int x, int y, DiagramView currDiagramView, PackageView pkg) {
    }
    public void zavrsenaSelekcija(DiagramElement novi, PackageView pkg){
        if(novi.getName() != "") {
            InterClass noviElement = (InterClass) novi;
            noviElement.setX(x);
            noviElement.setY(y);
            ElementPainter elementPainter = new InterClassPainter(noviElement);
            pkg.addPainterForCurrent(elementPainter);
        }
        else{

        }
    }
    @Override
    public void neispravnoCrtanje() {}

    @Override
    public void duplikacija(DiagramElement de, int x, int y, int w, int h, PackageView pkg) {

    }

}
