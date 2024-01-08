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
                var orig = originalneTacke.get(interClass);

                var classLeftX = (int) interClass.getX();
                var classRightX = (int) interClass.getX() + (int) interClass.getWidth();
                var classTopY = (int) interClass.getY();
                var classBotY = (int) interClass.getY() + (int) interClass.getHeight();

                for (var drugi : currDiagram.getDiagram().getChildren()) {
                    if(drugi == interClass)
                    {
                        continue;
                    }
                    if (drugi instanceof InterClass) {
                        var drugiClass = (InterClass) drugi;
                        if (interClass.isSelected()) {
                            var class2LeftX2 = (int) drugiClass.getX();
                            var class2RightX = (int) drugiClass.getX() + (int) drugiClass.getWidth();
                            var class2TopY = (int) drugiClass.getY();
                            var class2BotY = (int) drugiClass.getY() + (int) drugiClass.getHeight();
                            if(checkCollision(classLeftX, classTopY, classRightX, classBotY, class2LeftX2, class2TopY, class2RightX, class2BotY))
                            {
                                interClass.setX(orig.getX());
                                interClass.setY(orig.getY());
                            }else{
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
        int vektorX = (int)unscaleX(x, currDiagram) - startX;
        int vektorY = (int)unscaleY(y, currDiagram) - startY;

        for (InterClass interClass : originalneTacke.keySet()) {
            Tacka orig = originalneTacke.get(interClass);
            Tacka novaTacka = orig.dodajVektor((int)(vektorX / currDiagram.getScaling()), (int)(vektorY / currDiagram.getScaling()));
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



}