package raf.dsw.workspace.controller;

import raf.dsw.classyrepository.implementation.Diagram;
import raf.dsw.view.MainFrame;
import raf.dsw.workspace.WorkSpaceImplementation;
import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;

import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

public class MouseGraphicsEvent implements MouseListener, MouseMotionListener, MouseInputListener {

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        PackageView packageView = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getPackageView();
        Diagram currDiagram = ((DiagramView) packageView.getTabbedPane().getSelectedComponent()).getDiagram();
        Point worldP = getWorldCoordinates(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        PackageView packageView = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getPackageView();
        Diagram currDiagram = ((DiagramView) packageView.getTabbedPane().getSelectedComponent()).getDiagram();
        Point worldP = getWorldCoordinates(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        PackageView packageView = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getPackageView();
        Diagram currDiagram = ((DiagramView) packageView.getTabbedPane().getSelectedComponent()).getDiagram();
        Point worldP = getWorldCoordinates(e);

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    private Point getWorldCoordinates(MouseEvent me){
        PackageView packageView = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getPackageView();
        DiagramView currDiagramView = (DiagramView) packageView.getTabbedPane().getSelectedComponent();

        AffineTransform atInvert = null;

        try{
            atInvert = currDiagramView.getAffineTransform().createInverse();
        } catch (NoninvertibleTransformException | NullPointerException e){
            System.err.print("Non invertible transformation");
        }

        Point2D pDest = atInvert.transform(new Point2D.Double(me.getX(), me.getY()), null);
        Point pDest2 = new Point();
        pDest2.x = (int) pDest.getX();
        pDest2.y = (int) pDest.getY();
        return pDest2;
    }
}
