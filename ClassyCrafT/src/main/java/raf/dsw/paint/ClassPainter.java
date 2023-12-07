package raf.dsw.paint;

import raf.dsw.components.ClassContent;
import raf.dsw.components.DiagramElement;
import raf.dsw.components.InterClass;
import raf.dsw.popUps.PopUpChooseIC;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class ClassPainter extends ElementPainter{
    private PopUpChooseIC popUpChooseICInstance;
    private InterClass selectedElement;
    public ClassPainter(DiagramElement dgElement, PopUpChooseIC popUpChooseICInstance) {
        super(dgElement);
        this.popUpChooseICInstance = popUpChooseICInstance;
    }

    @Override
    public void draw(Graphics g) {

        /*Klasa newKlasa = (Klasa) super.getDgElement();
        InterClass newKlasa = pp.getNoviElement();

        List<ClassContent> cl = newKlasa.getCl();*/

        //System.out.println(newKlasa.getNaziv() + " " );

        //InterClass newKlasa = pp.getNoviElement();

        selectedElement = popUpChooseICInstance.getSelectedElement();


        Graphics2D g2D = (Graphics2D)g;
        FontMetrics fm = g2D.getFontMetrics();

        int widthRectangle = fm.stringWidth(selectedElement.getName()) + 20;
        int heightRectangle = 30;

        selectedElement.setWidthAndHeight(widthRectangle, heightRectangle);
        setShape(new Rectangle2D.Double(selectedElement.getX(), selectedElement.getY(), widthRectangle, heightRectangle));

        g2D.setStroke(new BasicStroke(selectedElement.getStrokeWidth()));
        g2D.setColor(new Color(selectedElement.getColourInside()));
        g2D.fill(getShape());
        g2D.setColor(new Color(selectedElement.getColourOutline()));
        g2D.draw(getShape());

        double xString = selectedElement.getX() + ((double) (widthRectangle - fm.stringWidth(selectedElement.getName())) / 2);
        double yString = selectedElement.getY() + ((double) (heightRectangle + fm.getAscent()) / 2);
        g2D.drawString(selectedElement.getName(), (float) xString, (float) yString);
    }

    @Override
    public boolean elementAt(int x, int y) {
        return getShape().contains(x, y);
    }

    /*public void najduzaRec(InterClass inter){
        for (InterClass:
             ) {

        }
    }*/
}
