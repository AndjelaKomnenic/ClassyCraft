package raf.dsw.state;

//import lombok.var;
import lombok.var;
import raf.dsw.classyrepository.implementation.Diagram;
import raf.dsw.components.*;
import raf.dsw.paint.ClassPainter;
import raf.dsw.paint.ConnectionPainter;
import raf.dsw.paint.ElementPainter;
import raf.dsw.paint.InterClassPainter;
import raf.dsw.popUps.PopUpChooseCon;
import raf.dsw.view.MainFrame;
import raf.dsw.workspace.WorkSpaceImplementation;
import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;

import javax.swing.*;
import java.awt.*;

public class DodavanjeVezaState implements State{
    private Connection connection;
    private ElementPainter painter;
    InterClass from;

    @Override
    public void misKliknut(int x, int y, DiagramView currDiagram, PackageView pkg) {
        from = nadjiInterClass(x,y, currDiagram);
        if(from == null)
        {
            return;
        }

        connection = new TemporaryConnection("temp", currDiagram.getDiagram());
        connection.tempSetFrom(x, y);
        connection.tempSetTo(x, y);
        painter = new ConnectionPainter(connection);
        pkg.addPainterForCurrent(painter);
    }

    @Override
    public void misOtpusten(int x, int y, DiagramView currDiagram, PackageView pkg) {
        if (connection == null){
            return;
        }
        connection.setFromX(0);
        connection.setFromY(0);
        connection.setToY(0);
        connection.setToX(0);
        pkg.repaint();
        pkg.removePainter(painter);
        connection = null;
        painter = null;

        InterClass to = nadjiInterClass(x,y, currDiagram);
        if(to == null)
        {
            return;
        }

        PopUpChooseCon popCon = new PopUpChooseCon(from, to, this);
        from = null;
    }

    @Override
    public void misPrevucen(int x, int y, DiagramView currDiagram, PackageView pkg) {
        if (connection == null)
            return;
        connection.tempSetTo(x, y);
        pkg.repaint();
    }


    @Override
    public void zavrsenaSelekcija(DiagramElement inter, PackageView pkg) {

        Connection c = (Connection) inter;
        ElementPainter conPain = new ConnectionPainter(c);
        pkg.addPainterForCurrent(conPain);
    }
    public void neispravnoCrtanje(){
        connection.setFromX(0);
        connection.setFromY(0);
        connection.setToY(0);
        connection.setToX(0);

    }

    @Override
    public void duplikacija(DiagramElement de, int x, int y, int w, int h, PackageView pkg) {

    }

    InterClass nadjiInterClass(int x, int y, DiagramView currentDiagram)
    {
        for(var ep: currentDiagram.getDiagram().getChildren()){
            if(ep instanceof InterClass){
                InterClass interClass = (InterClass) ep;

                    var classLeftX = (int) interClass.getX();
                    var classRightX = (int) interClass.getX() + (int) interClass.getWidth();
                    var classTopY = (int) interClass.getY();
                    var classBotY = (int) interClass.getY() + (int) interClass.getHeight();

                    if (x >= classLeftX && x <= classRightX && y >= classTopY && y <= classBotY)
                        return interClass;

            }
        }
        return null;
    }


}