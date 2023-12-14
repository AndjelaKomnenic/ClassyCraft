package raf.dsw.state;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.components.DiagramElement;
import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;

import java.awt.geom.AffineTransform;

@Getter
@Setter
public class ZoomInState implements State{
    /*private AffineTransform affineTransform = new AffineTransform();
    private double scaling = 1.0;
    private double translateX = 0.0;
    private double translateY = 0.0;*/

    private AffineTransform affineTransform = new AffineTransform();
    private double scaling = 1.0;
    private double translateX = 0.0;
    private double translateY = 0.0;

    @Override
    public void misKliknut(int x, int y, DiagramView currDiagramView, PackageView pkg) {

        double zoomFactor = 1.2;
        double prevScaling = currDiagramView.getScaling();
        double newScaling = currDiagramView.getScaling() * zoomFactor;
        if (newScaling >= 5) {
            newScaling = 5;
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

    /*private void zoomIn(PackageView pkg) {
        double newScaling = scaling * 1.2;
        if (newScaling >= 5) {
            newScaling = 5;
        }

        scaling = newScaling;
        setupTransformation(pkg, newScaling);
    }

    public void setupTransformation(double scaling){
        affineTransform.setToIdentity();
        affineTransform.translate(translateX, translateY);
        affineTransform.scale(scaling, scaling);
        repaint();
    }
    }*/
}
