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

    private DiagramView currDiagramView;

    public MouseGraphicsEvent(DiagramView currDiagramView) {
        this.currDiagramView = currDiagramView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        /*PackageView packageView = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getPackageView();
        Diagram currDiagram = ((DiagramView) packageView.getTabbedPane().getSelectedComponent()).getDiagram();
        Point worldP = getWorldCoordinates(e);*/
        //startX = (int) ((x - currDiagram.getTranslateX()) / currDiagram.getScaling());
        PackageView packageView = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getPackageView();
        DiagramView currDiagramView = ((DiagramView) packageView.getTabbedPane().getSelectedComponent());
        packageView.getStateManager().getCurrState().misKliknut(scaleX(e, currDiagramView), scaleY(e, currDiagramView), currDiagramView, packageView);
        //packageView.getStateManager().getCurrState().misKliknut(e.getX(), e.getY(), currDiagramView, packageView);

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        PackageView packageView = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getPackageView();
        DiagramView currDiagramView = ((DiagramView) packageView.getTabbedPane().getSelectedComponent());
        packageView.getStateManager().getCurrState().misOtpusten(scaleX(e, currDiagramView), scaleY(e, currDiagramView), currDiagramView, packageView);

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
        DiagramView currDiagramView = ((DiagramView) packageView.getTabbedPane().getSelectedComponent());
        packageView.getStateManager().getCurrState().misPrevucen(scaleX(e, currDiagramView), scaleY(e, currDiagramView), currDiagramView, packageView);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    private int scaleX(MouseEvent e, DiagramView currDiagramView){
        return (int)((e.getX() - currDiagramView.getTranslateX()) / currDiagramView.getScaling());
    }

    private int scaleY(MouseEvent e, DiagramView currDiagramView){
        return (int)((e.getY() - currDiagramView.getTranslateY()) / currDiagramView.getScaling());
    }

}
