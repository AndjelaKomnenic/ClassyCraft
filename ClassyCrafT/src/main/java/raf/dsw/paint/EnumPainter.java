package raf.dsw.paint;

import raf.dsw.components.ClassContent;
import raf.dsw.components.DiagramElement;
import raf.dsw.components.InterClass;
import raf.dsw.components.Metod;
import raf.dsw.popUps.PopUpChooseIC;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class EnumPainter extends ElementPainter{
    private PopUpChooseIC popUpChooseICInstance;
    private InterClass selectedElement;
    public EnumPainter(DiagramElement dgElement, PopUpChooseIC popUpChooseICInstance) {
        super(dgElement);
        this.popUpChooseICInstance = popUpChooseICInstance;
    }

    @Override
    public void draw(Graphics g) {

        selectedElement = popUpChooseICInstance.getSelectedElement();
        if (selectedElement != null) {
            List<ClassContent> ccc = selectedElement.getCl();

            Graphics2D g2D = (Graphics2D) g;
            FontMetrics fm = g2D.getFontMetrics();

            int maxWidth = fm.stringWidth(selectedElement.getName()) + 20;

            int height = fm.getHeight();
            int yOffset = (int) selectedElement.getY() + fm.getHeight();

            int requiredWidth = maxWidth;
            int requiredHeight = ((ccc.size() + 1) * (height + 5)) + height * 3;

            int selectedElementNameWidth = fm.stringWidth(selectedElement.getVidljivost() + " " + selectedElement.getName());


            int maxMethodWidth = 0;
            for (ClassContent element : ccc) {
                if (element instanceof Metod) {
                    int elementWidth = fm.stringWidth(element.getVidljivost() + " " + element.getTip() + " " + element.getNaziv());
                    maxMethodWidth = Math.max(maxMethodWidth, elementWidth);
                }
            }


            requiredWidth = Math.max(selectedElementNameWidth,  maxMethodWidth) + 20;



            selectedElement.setWidthAndHeight(requiredWidth, requiredHeight);
            setShape(new Rectangle2D.Double(selectedElement.getX(), selectedElement.getY(), requiredWidth, requiredHeight));


            g2D.setColor(new Color(selectedElement.getColourInside()));
            g2D.fillRect((int) selectedElement.getX(), (int) selectedElement.getY(), requiredWidth, requiredHeight);


            g2D.setColor(Color.BLACK);

            int xOffset = (int) selectedElement.getX() + 10;

            g2D.drawLine((int) selectedElement.getX(), yOffset, (int) (selectedElement.getX() + requiredWidth), yOffset);
            yOffset += height + 5;


            g2D.drawLine((int) selectedElement.getX(), yOffset, (int) (selectedElement.getX() + requiredWidth), yOffset);
            yOffset += height + 5;

            for (ClassContent element : ccc) {
                if (element instanceof Metod) {
                    String methodString = element.getVidljivost() + " " + element.getTip() + " " + element.getNaziv();
                    g2D.drawString(methodString, xOffset, yOffset);
                    yOffset += height + 5;
                }
            }


            g2D.setColor(new Color(selectedElement.getColourOutline()));
            g2D.drawRect((int) selectedElement.getX(), (int) selectedElement.getY(), requiredWidth, requiredHeight);
        }

    }

    @Override
    public boolean elementAt(int x, int y) {
        return getShape().contains(x, y);
    }
}
