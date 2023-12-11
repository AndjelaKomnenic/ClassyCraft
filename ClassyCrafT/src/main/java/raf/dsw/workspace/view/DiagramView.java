package raf.dsw.workspace.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classyrepository.implementation.Diagram;
import raf.dsw.observer.ISubscriber;
import raf.dsw.paint.*;
import raf.dsw.view.MainFrame;
import raf.dsw.workspace.IWorkspace;
import raf.dsw.workspace.controller.MouseGraphicsEvent;
import raf.dsw.workspace.WorkSpaceImplementation;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class DiagramView extends JPanel implements ISubscriber {

    private Diagram diagram;
    private List<ElementPainter> painters = new ArrayList<>();
    private List<ElementPainter> selectedElements = new ArrayList<>();
    private AffineTransform affineTransform = new AffineTransform();
    private double scaling = 1.0;
    private double translateX = 0.0;
    private double translateY = 0.0;

    public DiagramView(Diagram diagram){
        this.diagram = diagram;
        this.addMouseListener(new MouseGraphicsEvent(this));
        this.addMouseMotionListener(new MouseGraphicsEvent(this));
    }

    @Override
    public void update(Object notification) {
        IWorkspace workspace = MainFrame.getInstance().getWorkspace();
        PackageView ourPackageView = ((WorkSpaceImplementation) workspace).getPackageView();
        int indexOfOurDiagram = ourPackageView.getTabbedPane().indexOfComponent(this);

        if (indexOfOurDiagram != -1) {
            if (notification.equals("REMOVE")) {
                ourPackageView.getTabs().remove(this);
                ourPackageView.getTabbedPane().remove(indexOfOurDiagram);
            } else if (notification.equals("RENAME")) {
                ourPackageView.getTabbedPane().setTitleAt(indexOfOurDiagram, diagram.getName());
            }
        }

        ourPackageView.revalidate();
        ourPackageView.repaint();
        SwingUtilities.updateComponentTreeUI(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DiagramView other = (DiagramView) obj;
        return Objects.equals(this.getDiagram(), other.getDiagram());
    }
    @Override
    public int hashCode() {
        return Objects.hash(diagram);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g.create();
        g2D.transform(affineTransform);
       /* for(Painter p : painters){                                                                              //1. crtamo asocijacije da bi bile ispod pojmova
            if(p instanceof AssociationPainter) p.draw(g2D);
        }*/
        for(ElementPainter p : painters){
            if(p instanceof ClassPainter)
                p.draw(g2D);
            else if(p instanceof ConnectionPainter)
                p.draw(g2D);
            else if(p instanceof InterfacePainter)
                p.draw(g2D);
        }
        g2D.dispose();
    }

    public void zoomIn(){
        double newScaling = scaling * 1.2;
        if (newScaling >= 5) {
            newScaling = 5;
        }

        this.scaling = newScaling;
        setupTransformation(newScaling);
    }

    public void zoomOut(){
        double newScaling = scaling * 0.8;
        if (newScaling <= 0.2) {
            newScaling = 0.2;
        }

        this.scaling = newScaling;
        setupTransformation(newScaling);
    }

    public void setupTransformation(double scaling){
        affineTransform.setToIdentity();
        affineTransform.translate(translateX, translateY);
        affineTransform.scale(scaling, scaling);
        repaint();
    }
}
