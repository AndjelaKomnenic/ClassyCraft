package raf.dsw.paint;

import raf.dsw.components.DiagramElement;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class BoxSelectPainter extends ElementPainter {
    private double x1, y1;
    private double x2, y2;

    public BoxSelectPainter(double x1, double y1) {
        super(null);
        this.x1 = x1;
        this.y1 = y1;
        setShape(new Rectangle2D.Double(this.x1, this.y1, 1, 1));
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2D = (Graphics2D)g;

        g2D.setStroke(new BasicStroke(2));
        g2D.setColor(Color.MAGENTA);
        g2D.draw(getShape());
    }

    @Override
    public boolean elementAt(int x, int y) {
        return getShape().contains(x, y);
    }

    public void updateCoordinates(double x1, double y1, double x2, double y2){
        this.x1 = Math.min(x1, x2);
        this.y1 = Math.min(y1, y2);
        this.x2 = Math.max(x1, x2);
        this.y2 = Math.max(y1, y2);
        setShape(new Rectangle2D.Double(this.x1, this.y1, this.x2-this.x1, this.y2-this.y1));
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof BoxSelectPainter)) return false;
        BoxSelectPainter lp = (BoxSelectPainter) obj;
        return lp.x1 == x1 && lp.x2 == x2 && lp.y1 == y1 && lp.y2 == y2;
    }
}
