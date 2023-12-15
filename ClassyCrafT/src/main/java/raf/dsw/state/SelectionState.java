package raf.dsw.state;

//import lombok.var;
import lombok.var;
import raf.dsw.components.Connection;
import raf.dsw.components.DiagramElement;
import raf.dsw.components.InterClass;
import raf.dsw.state.State;
import raf.dsw.view.MainFrame;
import raf.dsw.workspace.WorkSpaceImplementation;
import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
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
        printSelectedElements();
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
                //System.out.println(pkg.getSelectedComponents().size());
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
                //System.out.println(pkg.getSelectedComponents().size());
            }
            else if (child instanceof Connection) {
                var connection = (Connection) child;
                PackageView pkg = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getPackageView();
                int connStartX = (int) connection.getFromX();
                int connEndX = (int) connection.getToX();
                int connStartY = (int) connection.getFromY();
                int connEndY = (int) connection.getToY();


                int clickThreshold = 5;

                if (isLineInsideRectangle(leftX, topY, rightX, botY, connStartX, connStartY, connEndX, connEndY)) {
                    connection.setSelected(true);
                    pkg.getSelectedComponents().add(connection);
                }

                else if (isClickNearLine(x, y, connStartX, connStartY, connEndX, connEndY, clickThreshold)) {
                    connection.setSelected(true);
                    pkg.getSelectedComponents().add(connection);
                } else {
                    connection.setSelected(false);
                }
            }
        }
    }

    boolean isLineInsideRectangle(int rectStartX, int rectStartY, int rectEndX, int rectEndY,
                                  int lineStartX, int lineStartY, int lineEndX, int lineEndY) {
        // Check if both endpoints of the line are inside the rectangle bounds
        return isPointInsideRectangle(rectStartX, rectStartY, rectEndX, rectEndY, lineStartX, lineStartY)
                && isPointInsideRectangle(rectStartX, rectStartY, rectEndX, rectEndY, lineEndX, lineEndY);
    }

    boolean isPointInsideRectangle(int rectStartX, int rectStartY, int rectEndX, int rectEndY,
                                   int pointX, int pointY) {
        return pointX >= Math.min(rectStartX, rectEndX)
                && pointX <= Math.max(rectStartX, rectEndX)
                && pointY >= Math.min(rectStartY, rectEndY)
                && pointY <= Math.max(rectStartY, rectEndY);
    }
    boolean isClickNearLine(int x, int y, int startX, int startY, int endX, int endY, int threshold) {
        // Calculate the distance from the click to the line segment formed by (startX, startY) and (endX, endY)
        double segmentLength = Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2));
        double dotProduct = ((x - startX) * (endX - startX)) + ((y - startY) * (endY - startY));

        // Calculate the closest point on the line
        double closestX = startX + (dotProduct / Math.pow(segmentLength, 2)) * (endX - startX);
        double closestY = startY + (dotProduct / Math.pow(segmentLength, 2)) * (endY - startY);


        double distance = Math.sqrt(Math.pow(closestX - x, 2) + Math.pow(closestY - y, 2));


        return distance <= threshold;
    }
    public void printSelectedElements() {
        PackageView pkg = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getPackageView();
        List<DiagramElement> selectedComponents = pkg.getSelectedComponents();

        System.out.println("Selected Elements:");
        for (DiagramElement element : selectedComponents) {
            if (element instanceof InterClass) {
                InterClass interClass = (InterClass) element;

                System.out.println("InterClass selected: " + interClass.getName());

            }if (element instanceof Connection){
                Connection connection = (Connection) element;
                System.out.println("Connection selected: " + connection.getName());
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
