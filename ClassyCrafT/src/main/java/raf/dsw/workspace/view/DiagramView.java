package raf.dsw.workspace.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classyrepository.implementation.Diagram;
import raf.dsw.commands.CommandManager;
import raf.dsw.components.*;
import raf.dsw.components.Enum;
import raf.dsw.observer.ISubscriber;
import raf.dsw.paint.*;
import raf.dsw.tree.model.ClassyTreeItem;
import raf.dsw.view.MainFrame;
import raf.dsw.workspace.IWorkspace;
import raf.dsw.workspace.controller.MouseGraphicsEvent;
import raf.dsw.workspace.WorkSpaceImplementation;
import raf.dsw.classyrepository.composite.ClassyNode;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class DiagramView extends JPanel implements ISubscriber {

    private Diagram diagram;
    private CommandManager commandManager;
    private List<ElementPainter> painters = new ArrayList<>();
    private List<ElementPainter> selectedElements = new ArrayList<>();
    private AffineTransform affineTransform = new AffineTransform();
    private double scaling = 1.0;
    private double translateX = 0.0;
    private double translateY = 0.0;

    private Rectangle selectionRect; // ovo dodato

    public DiagramView(Diagram diagram){
        this.diagram = diagram;
        for (ClassyNode child: diagram.getChildren())
        {
            if(child instanceof InterClass)
            {
                addClass((InterClass) child);
            }
            else if(child instanceof Connection)
            {
                addConnection((Connection)child);
            }
        }
        commandManager = new CommandManager();
        this.addMouseListener(new MouseGraphicsEvent(this));
        this.addMouseMotionListener(new MouseGraphicsEvent(this));
    }

    public void addConnection(Connection connection)
    {
        if( connection.getTo() !=  connection.getFrom()) {
            ElementPainter conPain = new ConnectionPainter(connection);

            PackageView packageView = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getPackageView();
//            ClassyTreeItem root = MainFrame.getInstance().getClassyTree().getRoot();
//
//            ClassyTreeItem myParent = root.findClassyTreeItem(this.getDiagram());
//            if(myParent != null) {
//                MainFrame.getInstance().getClassyTree().addChildToDiag(myParent, connection);
//            }
            packageView.addPainter(this.getDiagram(), conPain);
        }
    }

    public void addClass(InterClass node)
    {
        ElementPainter elementPainter = null;
        //this.getDiagram().addChild(popUp.getSelectedElement());

        if (node instanceof Klasa) {
            elementPainter = new ClassPainter(node);
        } else if (node instanceof Interfejs) {
            elementPainter = new InterfacePainter(node);
        } else if (node instanceof Enum) {
            elementPainter = new EnumPainter(node);
        }

        PackageView packageView = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getPackageView();
//        ClassyTreeItem root = MainFrame.getInstance().getClassyTree().getRoot();
//        ClassyTreeItem myParent = root.findClassyTreeItem(this.getDiagram());
//        if(myParent != null) {
//            MainFrame.getInstance().getClassyTree().addChildToDiag(myParent, node);
//        }
        packageView.addPainter(this.getDiagram(), elementPainter);
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
            } else if (notification.equals("REPAINT")) {
                for (ClassyNode dete: diagram.getChildren())
                {
                    if (dete instanceof Connection)
                    {
                        Connection connection = (Connection)dete;
                        connection.recalculateCoordinates();
                    }
                }
                //this.setPainters(painters.get(this.getDiagram()));
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

    // da trigeruje repaintovanje
    public void setSelectionRect(Rectangle rect) {
        this.selectionRect = rect;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g.create();
        g2D.transform(affineTransform);

        for(ElementPainter p : painters){
            if(p instanceof ConnectionPainter)
                p.draw(g2D);
            else if(p instanceof ClassPainter)
                p.draw(g2D);
            else if(p instanceof InterfacePainter)
                p.draw(g2D);
            else if(p instanceof EnumPainter)
                p.draw(g2D);
            else if(p instanceof InterClassPainter)
                p.draw(g2D);
        }

        // crtaj pravougaonik ako postoji
        if (selectionRect != null) {
            g2D.setColor(Color.CYAN);
            g2D.setStroke(new BasicStroke(1.5f));

            g2D.drawRect(selectionRect.x, selectionRect.y, selectionRect.width, selectionRect.height);
        }

        g2D.dispose();
    }

    public BufferedImage createImage(int desiredWidth, int desiredHeight) {

        BufferedImage bufferedImage = new BufferedImage(desiredWidth, desiredHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufferedImage.createGraphics();
        this.paint(g);
        return bufferedImage;
    }

    /*public void zoomIn(int x, int y){
        double zoomFactor = 1.2;
        double prevScaling = scaling;
        double newScaling = scaling * zoomFactor;
        if (newScaling >= 5) {
            newScaling = 5;
        }

        scaling = newScaling;


        translateX = x - (x - translateX) * (newScaling / prevScaling);
        translateY = y - (y - translateY) * (newScaling / prevScaling);

        setupTransformation(scaling);
    }*/

    /*public void scroll(double translateX, double translateY){
        this.translateX += translateX;
        this.translateY += translateY;
        setupTransformation(scaling);
    }*/

    /*public void zoomOut(){
        double newScaling = scaling * 0.8;
        if (newScaling <= 0.2) {
            newScaling = 0.2;
        }

        this.scaling = newScaling;
        setupTransformation(newScaling);
    }
*/
    /*public void setupTransformation(double scaling){
        affineTransform.setToIdentity();
        affineTransform.translate(translateX, translateY);
        affineTransform.scale(scaling, scaling);
        repaint();
    }*/
}