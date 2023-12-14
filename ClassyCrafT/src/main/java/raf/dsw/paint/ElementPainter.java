package raf.dsw.paint;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.components.DiagramElement;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

@Getter
@Setter
public abstract class ElementPainter {

    private DiagramElement dgElement;
    private Shape shape;

    public ElementPainter(DiagramElement dgElement) {
        this.dgElement = dgElement;
    }

    public abstract void draw(Graphics g);
    public abstract boolean elementAt(int x, int y);
    public int getRequiredWidth(){return 0;}
    public int getRequiredHeight(){return 0;}
    public int getXCoord(){return 0;}
    public int getYCoord(){return 0;}
    public abstract List<Point2D.Double> getRectangleCoordinates();
}
