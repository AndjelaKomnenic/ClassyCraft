package raf.dsw.state;

//import lombok.var;

import raf.dsw.components.DiagramElement;
import raf.dsw.components.InterClass;
import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Vector;

public class MoveState implements State{


    int startX = -1;
    int startY = -1;
    HashMap<InterClass, Tacka> originalneTacke = new HashMap<>();
    @Override
    public void misKliknut(int x, int y, DiagramView currDiagram, PackageView pkg) {
        startX = x;
        startY = y;
        originalneTacke.clear();
        for(var child: currDiagram.getDiagram().getChildren())
        {
            if (child instanceof InterClass)
            {
                var interClass = (InterClass)child;
                if(interClass.isSelected())
                {
                    originalneTacke.put(interClass, new Tacka((int)interClass.getX(), (int)interClass.getY()));
                }
            }
        }
    }

    @Override
    public void misOtpusten(int x, int y, DiagramView currDiagram, PackageView pkg) {
        startX = -1;
        startY = -1;
        originalneTacke.clear();
    }

    @Override
    public void misPrevucen(int x, int y, DiagramView currDiagram, PackageView pkg)
    {
        moveItems(x,y, currDiagram);
    }

    void moveItems(int x, int y, DiagramView currDiagram)
    {
        var vektorX = x - startX;
        var vektorY = y - startY;

        for(var interClass: originalneTacke.keySet())
        {
            var orig = originalneTacke.get(interClass);
            var novaTacka = orig.dodajVektor(vektorX, vektorY);
            interClass.setX(novaTacka.getX());
            interClass.setY(novaTacka.getY());
        }
    }

    public void zavrsenaSelekcija(DiagramElement noviElement, PackageView pkg){}
    @Override
    public void neispravnoCrtanje() {}
}