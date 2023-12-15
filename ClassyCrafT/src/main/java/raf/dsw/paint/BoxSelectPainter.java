package raf.dsw.paint;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.components.DiagramElement;
import raf.dsw.paint.ElementPainter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

@Getter
@Setter
public class BoxSelectPainter extends ElementPainter {
    private double x1, y1;
    private double x2, y2;

    public BoxSelectPainter(double x1, double y1) {
        super(null);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x1; // Set initial x2 and y2 to the same as x1 and y1
        this.y2 = y1;
        setShape(new Rectangle2D.Double(this.x1, this.y1, 1, 1));
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        g2D.setStroke(new BasicStroke(2));
        g2D.setColor(Color.MAGENTA);
        g2D.draw(getShape());
    }

    @Override
    public boolean elementAt(int x, int y) {
        return getShape().contains(x, y);
    }

    @Override
    public List<Point2D.Double> getRectangleCoordinates() {
        return null;
    }

    public void updateCoordinates(double x1, double y1, double x2, double y2) {
        this.x1 = Math.min(x1, x2);
        this.y1 = Math.min(y1, y2);
        this.x2 = Math.max(x1, x2);
        this.y2 = Math.max(y1, y2);
        setShape(new Rectangle2D.Double(this.x1, this.y1, this.x2 - this.x1, this.y2 - this.y1));
    }

    public void updateStartPoint(double x, double y) {
        this.x1 = x;
        this.y1 = y;
    }

    public void updateEndPoint(double x, double y) {
        this.x2 = x;
        this.y2 = y;
        setShape(new Rectangle2D.Double(this.x1, this.y1, this.x2 - this.x1, this.y2 - this.y1));
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BoxSelectPainter)) return false;
        BoxSelectPainter lp = (BoxSelectPainter) obj;
        return lp.x1 == x1 && lp.x2 == x2 && lp.y1 == y1 && lp.y2 == y2;
    }
}
