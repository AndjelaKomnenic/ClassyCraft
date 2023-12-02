package raf.dsw.paint;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.novo.DiagramElement;

import java.awt.*;

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
}
