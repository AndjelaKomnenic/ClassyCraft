package raf.dsw.state;


import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.implementation.Diagram;
import raf.dsw.commands.NewInterClassCommand;
import raf.dsw.components.DiagramElement;

import raf.dsw.components.Enum;

import raf.dsw.components.InterClass;
import raf.dsw.components.Interfejs;
import raf.dsw.components.Klasa;
import raf.dsw.core.ApplicationFramework;
import raf.dsw.message.PossibleErrors;
import raf.dsw.paint.*;

import raf.dsw.popUps.PopUpChooseIC;
import raf.dsw.tree.model.ClassyTreeItem;
import raf.dsw.view.MainFrame;
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
            popUp.getSelectedElement().setX(x);
            popUp.getSelectedElement().setY(y);

            boolean collisionDetected = false;
            for (ClassyNode i : currDiagramView.getDiagram().getChildren()) {
                if (i instanceof InterClass) {
                    if (overlap(x, y, (int) ((InterClass) i).getX(), (int) ((InterClass) i).getY(),
                            (int) popUp.getSelectedElement().getWidth(), (int) ((InterClass) i).getWidth(),
                            (int) popUp.getSelectedElement().getHeight(), (int) ((InterClass) i).getHeight())) {
                        collisionDetected = true;
                        ApplicationFramework.getInstance().getMessageGenerator().createMessage(PossibleErrors.OVERLAP);
                        break;
                    }
                }
            }

            if (!collisionDetected) {
                ElementPainter elementPainter = null;
                currDiagramView.getDiagram().addChild(popUp.getSelectedElement());

                if (popUp.getSelectedElement() instanceof Klasa) {
                    elementPainter = new ClassPainter(popUp.getSelectedElement());
                } else if (popUp.getSelectedElement() instanceof Interfejs) {
                    elementPainter = new InterfacePainter(popUp.getSelectedElement());
                } else if (popUp.getSelectedElement() instanceof Enum) {
                    elementPainter = new EnumPainter(popUp.getSelectedElement());
                }

                currDiagramView.getCommandManager().addCommand(new NewInterClassCommand(pkg, currDiagramView, popUp.getSelectedElement(), elementPainter));
            }

        }
    }


    /*public boolean overlap(int x1, int y1, int x2, int y2, int w1, int w2, int h1, int h2) {
        int right1 = x1 + w1;
        int bottom1 = y1 + h1;
        int right2 = x2 + w2;
        int bottom2 = y2 + h2;

        if (x1 >= right2 || right1 <= x2 || y1 >= bottom2 || bottom1 <= y2) {
            return false;
        }

        return true;
    }*/
    public boolean overlap(int x1, int y1, int x2, int y2, int w1, int w2, int h1, int h2) {
        int expandedBoxSize = 2;

        int right1 = x1 + w1 + expandedBoxSize;
        int bottom1 = y1 + h1 + expandedBoxSize;
        int right2 = x2 + w2 + expandedBoxSize;
        int bottom2 = y2 + h2 + expandedBoxSize;

        x1 -= expandedBoxSize;
        y1 -= expandedBoxSize;
        x2 -= expandedBoxSize;
        y2 -= expandedBoxSize;

        if (x1 >= right2 || right1 <= x2 || y1 >= bottom2 || bottom1 <= y2) {
            return false;
        }

        return true;
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
    }
    @Override
    public void neispravnoCrtanje() {}

    @Override
    public void duplikacija(DiagramElement de, int x, int y, int w, int h, PackageView pkg) {

    }
}