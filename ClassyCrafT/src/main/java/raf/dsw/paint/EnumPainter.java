package raf.dsw.paint;

import raf.dsw.components.*;
import raf.dsw.popUps.PopUpChooseIC;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class EnumPainter extends ElementPainter{
    private PopUpChooseIC popUpChooseICInstance;
    private InterClass selectedElement;
    private int requiredWidth, requiredHeight;
    public EnumPainter(DiagramElement dgElement, PopUpChooseIC popUpChooseICInstance) {
        super(dgElement);
        this.popUpChooseICInstance = popUpChooseICInstance;
    }

    @Override
    public void draw(Graphics g) {

        selectedElement = popUpChooseICInstance.getSelectedElement();
        if (selectedElement != null) {
            List<ClanEnuma> ccc = selectedElement.getNEnum();

            /*System.out.println("Size of ccc: " + ccc.size());
            for (ClanEnuma c : ccc) {
                System.out.println(c.getValue());
            }*/
            //System.out.println("Size of ccc: " + ccc.size());
            Graphics2D g2D = (Graphics2D) g;
            FontMetrics fm = g2D.getFontMetrics();

            int maxWidth = fm.stringWidth("(E)" + selectedElement.getName()) + 20;

            int height = fm.getHeight();
            int yOffset = (int) selectedElement.getY() + fm.getHeight();

            requiredWidth = maxWidth;
            requiredHeight = ((ccc.size() + 1) * (height + 5)) + height * 3;

            int selectedElementNameWidth = fm.stringWidth("(E)" + selectedElement.getName());


            int maxMethodWidth = 0;
            for (ClanEnuma element : ccc) {
                //if (element instanceof ClanEnuma) {
                    int elementWidth = fm.stringWidth(element.getValue());
                    maxMethodWidth = Math.max(maxMethodWidth, elementWidth);
                //}
                //System.out.println(element.getValue()+"\n");
            }


            requiredWidth = Math.max(selectedElementNameWidth,  maxMethodWidth) + 20;

            if (this.getDgElement().isSelected())
            {
                selectedElement.setColourOutline("0x00FFFF");
            }
            else
            {
                selectedElement.setColourOutline("0x000000");
            }

            g2D.setColor(Color.BLACK); // boja za text

            selectedElement.setWidthAndHeight(requiredWidth, requiredHeight);
            setShape(new Rectangle2D.Double(selectedElement.getX(), selectedElement.getY(), requiredWidth, requiredHeight));


            g2D.setColor(new Color(selectedElement.getColourInside()));
            g2D.fillRect((int) selectedElement.getX(), (int) selectedElement.getY(), requiredWidth, requiredHeight);



            g2D.setColor(Color.BLACK);

            int xOffset = (int) selectedElement.getX() + 10;

            g2D.drawString("(E)" + selectedElement.getName(), xOffset, yOffset);
            yOffset += height + 5;

            g2D.drawLine((int) selectedElement.getX(), yOffset, (int) (selectedElement.getX() + requiredWidth), yOffset);
            yOffset += height + 5;



            for (ClanEnuma element : ccc) {
                //if (element instanceof Metod) {
                    String enumVal = element.getValue();
                    g2D.drawString(enumVal, xOffset, yOffset);
                    yOffset += height + 5;
                //System.out.println("Usao u en");
                //}

            }


            g2D.setColor(new Color(selectedElement.getColourOutline()));
            g2D.drawRect((int) selectedElement.getX(), (int) selectedElement.getY(), requiredWidth, requiredHeight);
        }

    }

    public List<Point2D.Double> getRectangleCoordinates() {
        List<Point2D.Double> coordinates = new ArrayList<>();

        double topLeftX = selectedElement.getX();
        double topLeftY = selectedElement.getY();

        double topRightX = topLeftX + selectedElement.getWidth();
        double topRightY = topLeftY;

        double bottomLeftX = topLeftX;
        double bottomLeftY = topLeftY + selectedElement.getHeight();

        double bottomRightX = topRightX;
        double bottomRightY = bottomLeftY;

        coordinates.add(new Point2D.Double(topLeftX, topLeftY));
        coordinates.add(new Point2D.Double(topRightX, topRightY));
        coordinates.add(new Point2D.Double(bottomLeftX, bottomLeftY));
        coordinates.add(new Point2D.Double(bottomRightX, bottomRightY));

        // midpoints
        double topMidX = (topLeftX + topRightX) / 2;
        double topMidY = topLeftY;

        double rightMidX = topRightX;
        double rightMidY = (topRightY + bottomRightY) / 2;

        double bottomMidX = (bottomLeftX + bottomRightX) / 2;
        double bottomMidY = bottomLeftY;

        double leftMidX = topLeftX;
        double leftMidY = (topLeftY + bottomLeftY) / 2;

        coordinates.add(new Point2D.Double(topMidX, topMidY));
        coordinates.add(new Point2D.Double(rightMidX, rightMidY));
        coordinates.add(new Point2D.Double(bottomMidX, bottomMidY));
        coordinates.add(new Point2D.Double(leftMidX, leftMidY));

        return coordinates;
    }
    @Override
    public boolean elementAt(int x, int y) {
        return getShape().contains(x, y);
    }
    public int getRequiredWidth(){return requiredWidth;}
    public int getRequiredHeight(){return  requiredHeight;}
    public int getXCoord(){return (int)selectedElement.getX();}
    public int getYCoord(){return (int)selectedElement.getY();}
}
