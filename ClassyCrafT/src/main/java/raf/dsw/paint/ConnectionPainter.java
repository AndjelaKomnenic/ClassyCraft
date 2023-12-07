package raf.dsw.paint;

import raf.dsw.components.Connection;
import raf.dsw.components.DiagramElement;

import java.awt.*;

public class ConnectionPainter extends ElementPainter{
    private Connection dgElement;
    public ConnectionPainter(Connection dgElement) {
        super(dgElement);
        this.dgElement = dgElement;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(dgElement.getFromX(), dgElement.getFromY(), dgElement.getToX(), dgElement.getToY());
    }

    @Override
    public boolean elementAt(int x, int y) {
        return false;
    }
}
