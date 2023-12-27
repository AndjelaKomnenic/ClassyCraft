package raf.dsw.state;

import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.commands.MoveCommand;
import raf.dsw.components.DiagramElement;
import raf.dsw.components.InterClass;
import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;

import java.awt.geom.Point2D;
import java.util.HashMap;

public class MoveState implements State {

    int startX = -1;
    int startY = -1;
    HashMap<InterClass, Tacka> originalneTacke = new HashMap<>();
    HashMap<InterClass, Tacka> noveTacke = new HashMap<>();
    boolean movingView = false;
    PackageView pkg;
    int viewStartX;
    int viewStartY;

    @Override
    public void misKliknut(int x, int y, DiagramView currDiagram, PackageView pkg) {
        this.pkg = pkg;
        startX = (int)unscaleX(x, currDiagram);
        startY = (int)unscaleY(y, currDiagram);
        originalneTacke.clear();
        movingView = false;

        for (ClassyNode child : currDiagram.getDiagram().getChildren()) {
            if (child instanceof InterClass) {
                InterClass interClass = (InterClass) child;
                if (interClass.isSelected()) {
                    originalneTacke.put(interClass, new Tacka((int) interClass.getX(), (int) interClass.getY()));
                }
            }
        }


        if (originalneTacke.isEmpty()) {
            movingView = true;
            viewStartX = (int)currDiagram.getTranslateX();
            viewStartY = (int)currDiagram.getTranslateY();
        }
    }

    @Override
    public void misOtpusten(int x, int y, DiagramView currDiagram, PackageView pkg) {


        if(!movingView)
        {
            for (InterClass interClass : originalneTacke.keySet()) {
                Tacka orig = originalneTacke.get(interClass);

                int classLeftX = (int) interClass.getX();
                int classRightX = (int) interClass.getX() + (int) interClass.getWidth();
                int classTopY = (int) interClass.getY();
                int classBotY = (int) interClass.getY() + (int) interClass.getHeight();

                for (ClassyNode drugi : currDiagram.getDiagram().getChildren()) {
                    if(drugi == interClass)
                    {
                        continue;
                    }
                    if (drugi instanceof InterClass) {
                        InterClass drugiClass = (InterClass) drugi;
                        if (interClass.isSelected()) {
                            int class2LeftX2 = (int) drugiClass.getX();
                            int class2RightX = (int) drugiClass.getX() + (int) drugiClass.getWidth();
                            int class2TopY = (int) drugiClass.getY();
                            int class2BotY = (int) drugiClass.getY() + (int) drugiClass.getHeight();
                            if(checkCollision(classLeftX, classTopY, classRightX, classBotY, class2LeftX2, class2TopY, class2RightX, class2BotY))
                            {
                                interClass.setX(orig.getX());
                                interClass.setY(orig.getY());
                            }
                            else{
                                DiagramView dgView = (DiagramView) pkg.getTabbedPane().getSelectedComponent();
                                dgView.getCommandManager().addCommand(new MoveCommand(pkg, dgView, originalneTacke, noveTacke));
                            }
                        }
                    }
                }

            }


        }


        startX = -1;
        startY = -1;
        originalneTacke.clear();
        movingView = false;
    }

    @Override
    public void misPrevucen(int x, int y, DiagramView currDiagram, PackageView pkg) {
        if (movingView) {
            double deltaX = unscaleX(x, currDiagram) - startX;
            double deltaY = unscaleY(y, currDiagram) - startY;

            currDiagram.setTranslateX(viewStartX + deltaX);
            currDiagram.setTranslateY(viewStartY + deltaY);

            currDiagram.getAffineTransform().setToIdentity();
            currDiagram.getAffineTransform().translate(currDiagram.getTranslateX(), currDiagram.getTranslateY());
            currDiagram.getAffineTransform().scale(currDiagram.getScaling(), currDiagram.getScaling());

            currDiagram.repaint();
        } else {
            moveItems(x, y, currDiagram);
        }
    }

    void moveItems(int x, int y, DiagramView currDiagram) {
        int vektorX = x - startX;
        int vektorY = y - startY;

        for (InterClass interClass : originalneTacke.keySet()) {
            Tacka orig = originalneTacke.get(interClass);
            Tacka novaTacka = orig.dodajVektor(vektorX, vektorY);
            noveTacke.put(interClass, novaTacka);
            interClass.setX(novaTacka.getX());
            interClass.setY(novaTacka.getY());
        }
    }


    static boolean checkCollision(int leftX1, int topY1, int rightX1, int bottomY1,
                                  int leftX2, int topY2, int rightX2, int bottomY2) {
        return !(leftX1 > rightX2 || leftX2 > rightX1 || topY1 > bottomY2 || topY2 > bottomY1);
    }


    public void zavrsenaSelekcija(DiagramElement noviElement, PackageView pkg) {
    }

    @Override
    public void neispravnoCrtanje() {
    }

    @Override
    public void duplikacija(DiagramElement de, int x, int y, int w, int h, PackageView pkg) {
    }

    private double unscaleX(double x, DiagramView currDiagramView){
        return (x * currDiagramView.getScaling()) + currDiagramView.getTranslateX();
    }

    private double unscaleY(double y, DiagramView currDiagramView){
        return (y * currDiagramView.getScaling()) + currDiagramView.getTranslateY();
    }

    // ovaj radi
    /*int startX = -1;
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

    @Override
    public void duplikacija(DiagramElement de, int x, int y, int w, int h, PackageView pkg) {

    }*/

}