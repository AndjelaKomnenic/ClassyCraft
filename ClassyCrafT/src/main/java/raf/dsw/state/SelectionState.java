package raf.dsw.state;

//import lombok.var;
import raf.dsw.components.DiagramElement;
import raf.dsw.components.InterClass;
import raf.dsw.state.State;
import raf.dsw.view.MainFrame;
import raf.dsw.workspace.WorkSpaceImplementation;
import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class SelectionState implements State {

    int startX = -1;
    int startY = -1;
    int endX = -1;
    int endY = -1;
    boolean isDrawing = false;

    @Override
    public void misKliknut(int x, int y, DiagramView currDiagram, PackageView pkg) {
        startX = x;
        startY = y;
        isDrawing = true;
        currDiagram.setSelectionRect(null);
        selectItems(startX, startY, currDiagram, true);
    }

    @Override
    public void misOtpusten(int x, int y, DiagramView currDiagram, PackageView pkg) {
        endX = x;
        endY = y;
        isDrawing = false;
        currDiagram.setSelectionRect(null);
        pkg.getSelectedComponents().clear();
        selectItems(endX, endY, currDiagram, false);
        startX = -1;
        startY = -1;
        endX = -1;
        endY = -1;
    }

    @Override
    public void misPrevucen(int x, int y, DiagramView currDiagram, PackageView pkg) {
        if (isDrawing) {
            endX = x;
            endY = y;
            int rectStartX = Math.min(startX, endX);
            int rectStartY = Math.min(startY, endY);
            int rectWidth = Math.abs(endX - startX);
            int rectHeight = Math.abs(endY - startY);

            currDiagram.setSelectionRect(new Rectangle(rectStartX, rectStartY, rectWidth, rectHeight));
            selectItems(endX, endY, currDiagram, false);
        }
    }

    void selectItems(int x, int y, DiagramView currDiagram, boolean click) {
        var leftX = Math.min(startX, x);
        var rightX = Math.max(startX, x);
        var topY = Math.min(startY, y);
        var botY = Math.max(startY, y);

        var anySelected = false;

        var children = new ArrayList<>(currDiagram.getDiagram().getChildren());
        Collections.reverse(children);

        for (var child : children) {
            if (child instanceof InterClass) {
                var interClass = (InterClass) child;

                var classLeftX = (int) interClass.getX();
                var classRightX = (int) interClass.getX() + (int) interClass.getWidth();
                var classTopY = (int) interClass.getY();
                var classBotY = (int) interClass.getY() + (int) interClass.getHeight();

                PackageView pkg = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getPackageView();
                if (click && !anySelected && x >= classLeftX && x <= classRightX && y >= classTopY && y <= classBotY) {
                    interClass.setSelected(true);
                    pkg.getSelectedComponents().add(interClass);
                    anySelected = true;

                } else if (!click && checkCollision(leftX, topY, rightX, botY, classLeftX, classTopY, classRightX, classBotY)) {
                    interClass.setSelected(true);
                    pkg.getSelectedComponents().add(interClass);
                } else {
                    interClass.setSelected(false);
                }
            }
        }
    }

    static boolean checkCollision(int leftX1, int topY1, int rightX1, int bottomY1,
                                  int leftX2, int topY2, int rightX2, int bottomY2) {
        return !(leftX1 > rightX2 || leftX2 > rightX1 || topY1 > bottomY2 || topY2 > bottomY1);
    }

    public void zavrsenaSelekcija(DiagramElement noviElement, PackageView pkg) {}

    @Override
    public void neispravnoCrtanje() {}

    @Override
    public void duplikacija(DiagramElement de, int x, int y, int w, int h, PackageView pkg) {}
}
