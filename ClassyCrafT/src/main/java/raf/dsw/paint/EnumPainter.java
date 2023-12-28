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
    private InterClass klasa;
    private int requiredWidth, requiredHeight;
    public EnumPainter(InterClass klasa) {
        super(klasa);
        this.klasa = klasa;
    }

    @Override
    public void draw(Graphics g) {

        if (klasa != null) {
            List<ClanEnuma> ccc = klasa.getNEnum();

            /*System.out.println("Size of ccc: " + ccc.size());
            for (ClanEnuma c : ccc) {
                System.out.println(c.getValue());
            }*/
            //System.out.println("Size of ccc: " + ccc.size());
            Graphics2D g2D = (Graphics2D) g;
            FontMetrics fm = g2D.getFontMetrics();
            BasicStroke basicStroke = new BasicStroke(1);
            g2D.setStroke(basicStroke);

            int maxWidth = fm.stringWidth("(E)" + klasa.getName()) + 20;

            int height = fm.getHeight();
            int yOffset = (int) klasa.getY() + fm.getHeight();

            requiredWidth = maxWidth;
            requiredHeight = ((ccc.size() + 1) * (height + 5)) + height * 3;

            int klasaNameWidth = fm.stringWidth("(E)" + klasa.getName());


            int maxMethodWidth = 0;
            for (ClanEnuma element : ccc) {
                //if (element instanceof ClanEnuma) {
                int elementWidth = fm.stringWidth(element.getValue());
                maxMethodWidth = Math.max(maxMethodWidth, elementWidth);
                //}
                //System.out.println(element.getValue()+"\n");
            }


            requiredWidth = Math.max(klasaNameWidth,  maxMethodWidth) + 20;

            if (this.getDgElement().isSelected())
            {
                klasa.setColourOutline("0x00FFFF");
            }
            else
            {
                klasa.setColourOutline("0x000000");
            }

            g2D.setColor(Color.BLACK); // boja za text

            klasa.setWidthAndHeight(requiredWidth, requiredHeight);
            setShape(new Rectangle2D.Double(klasa.getX(), klasa.getY(), requiredWidth, requiredHeight));


            g2D.setColor(new Color(klasa.getColourInside()));
            g2D.fillRect((int) klasa.getX(), (int) klasa.getY(), requiredWidth, requiredHeight);



            g2D.setColor(Color.BLACK);

            int xOffset = (int) klasa.getX() + 10;

            g2D.drawString("(E)" + klasa.getName(), xOffset, yOffset);
            yOffset += height + 5;

            g2D.drawLine((int) klasa.getX(), yOffset, (int) (klasa.getX() + requiredWidth), yOffset);
            yOffset += height + 5;



            for (ClanEnuma element : ccc) {
                //if (element instanceof Metod) {
                String enumVal = element.getValue();
                g2D.drawString(enumVal, xOffset, yOffset);
                yOffset += height + 5;
                //System.out.println("Usao u en");
                //}

            }


            g2D.setColor(new Color(klasa.getColourOutline()));
            g2D.drawRect((int) klasa.getX(), (int) klasa.getY(), requiredWidth, requiredHeight);
        }

    }

    public List<Point2D.Double> getRectangleCoordinates() {
        List<Point2D.Double> coordinates = new ArrayList<>();

        double topLeftX = klasa.getX();
        double topLeftY = klasa.getY();

        double topRightX = topLeftX + klasa.getWidth();
        double topRightY = topLeftY;

        double bottomLeftX = topLeftX;
        double bottomLeftY = topLeftY + klasa.getHeight();

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
    public int getXCoord(){return (int)klasa.getX();}
    public int getYCoord(){return (int)klasa.getY();}
}