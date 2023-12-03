package raf.dsw.paint;

import raf.dsw.novo.DiagramElement;
import raf.dsw.novo.InterClass;
import raf.dsw.novo.Klasa;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class InterClassPainter extends ElementPainter{
    public InterClassPainter(DiagramElement dgElement) {
        super(dgElement);
    }

    @Override
    public void draw(Graphics g) {

        Klasa newKlasa = (Klasa) super.getDgElement();

        Graphics2D g2D = (Graphics2D)g;
        FontMetrics fm = g2D.getFontMetrics();

        int widthRectangle = fm.stringWidth(newKlasa.getName()) + 20;
        int heightRectangle = 30;

        newKlasa.setWidthAndHeight(widthRectangle, heightRectangle);
        setShape(new Rectangle2D.Double(newKlasa.getX(), newKlasa.getY(), widthRectangle, heightRectangle));

        g2D.setStroke(new BasicStroke(newKlasa.getStrokeWidth()));
        g2D.setColor(new Color(newKlasa.getColourInside()));
        g2D.fill(getShape());
        g2D.setColor(new Color(newKlasa.getColourOutline()));
        g2D.draw(getShape());

        double xString = newKlasa.getX() + ((double) (widthRectangle - fm.stringWidth(newKlasa.getName())) / 2);
        double yString = newKlasa.getY() + ((double) (heightRectangle - fm.getHeight() / 2) + fm.getAscent());
        g2D.drawString(newKlasa.getName(), (float) xString, (float) yString);
    }

    @Override
    public boolean elementAt(int x, int y) {
        return getShape().contains(x, y);
    }
}