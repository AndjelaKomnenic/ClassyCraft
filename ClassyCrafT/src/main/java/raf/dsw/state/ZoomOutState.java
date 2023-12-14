package raf.dsw.state;

import raf.dsw.components.DiagramElement;
import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;

import java.awt.geom.AffineTransform;

public class ZoomOutState implements State{
    @Override
    public void misKliknut(int x, int y, DiagramView currDiagramView, PackageView pkg) {

        double zoomFactor = 0.8;
        double prevScaling = currDiagramView.getScaling();
        double newScaling = currDiagramView.getScaling() * zoomFactor;

        if (newScaling <= 0.2) {
            newScaling = 0.2;
        }

        currDiagramView.setScaling(newScaling);

        double newTranslateX = x - (x - currDiagramView.getTranslateX()) * (newScaling / prevScaling);
        double newTranslateY = y - (y - currDiagramView.getTranslateY()) * (newScaling / prevScaling);

        currDiagramView.setTranslateX(newTranslateX);
        currDiagramView.setTranslateY(newTranslateY);

        currDiagramView.getAffineTransform().setToIdentity();
        currDiagramView.getAffineTransform().translate(currDiagramView.getTranslateX(), currDiagramView.getTranslateY());
        currDiagramView.getAffineTransform().scale(currDiagramView.getScaling(), currDiagramView.getScaling());
        currDiagramView.repaint();
    }

    @Override
    public void misOtpusten(int x, int y, DiagramView currDiagram, PackageView pkg) {

    }

    @Override
    public void misPrevucen(int x, int y, DiagramView currDiagram, PackageView pkg) {

    }

    @Override
    public void zavrsenaSelekcija(DiagramElement inter, PackageView pkg) {

    }

    @Override
    public void neispravnoCrtanje() {

    }

    @Override
    public void duplikacija(DiagramElement de, int x, int y, int w, int h, PackageView pkg) {

    }
}
