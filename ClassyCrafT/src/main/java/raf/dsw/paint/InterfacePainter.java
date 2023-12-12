package raf.dsw.paint;

import raf.dsw.components.*;
import raf.dsw.popUps.PopUpChooseIC;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class InterfacePainter extends ElementPainter{
    private PopUpChooseIC popUpChooseICInstance;
    private InterClass selectedElement;
    public InterfacePainter(DiagramElement dgElement, PopUpChooseIC popUpChooseICInstance) {
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

            int maxWidth = fm.stringWidth("(I)" + selectedElement.getName()) + 20;

            int height = fm.getHeight();
            int yOffset = (int) selectedElement.getY() + fm.getHeight();

            int requiredWidth = maxWidth;
            int requiredHeight = ((ccc.size() + 1) * (height + 5)) + height * 3;

            int selectedElementNameWidth = fm.stringWidth("(I)" + selectedElement.getName());


            int maxMethodWidth = 0;
            for (ClassContent element : ccc) {
                if (element instanceof Metod) {
                    int elementWidth = fm.stringWidth(promeniVidljivostUOznaku(element.getVidljivost()) + " " + element.getTip() + " " + element.getNaziv());
                    maxMethodWidth = Math.max(maxMethodWidth, elementWidth);
                }
            }

            requiredWidth = Math.max(selectedElementNameWidth, maxMethodWidth) + 20;

            selectedElement.setWidthAndHeight(requiredWidth, requiredHeight);
            setShape(new Rectangle2D.Double(selectedElement.getX(), selectedElement.getY(), requiredWidth, requiredHeight));


            g2D.setColor(new Color(selectedElement.getColourInside()));
            g2D.fillRect((int) selectedElement.getX(), (int) selectedElement.getY(), requiredWidth, requiredHeight);


            g2D.setColor(Color.BLACK);

            int xOffset = (int) selectedElement.getX() + 10;

            g2D.drawString("(I)" + selectedElement.getName(), xOffset, yOffset);
            yOffset += height + 5;


            g2D.drawLine((int) selectedElement.getX(), yOffset, (int) (selectedElement.getX() + requiredWidth), yOffset);
            yOffset += height + 5;

            for (ClassContent element : ccc) {
                if (element instanceof Metod) {
                    String methodString = promeniVidljivostUOznaku(element.getVidljivost()) + " " + element.getTip() + " " + element.getNaziv();
                    g2D.drawString(methodString, xOffset, yOffset);
                    yOffset += height + 5;
                }
            }


            g2D.setColor(new Color(selectedElement.getColourOutline()));
            g2D.drawRect((int) selectedElement.getX(), (int) selectedElement.getY(), requiredWidth, requiredHeight);
        }

    }

    public String promeniVidljivostUOznaku(String str){
        if (str.equals("private"))
            return "-";
        if (str.equals("public"))
            return "+";
        if (str.equals("protected"))
            return "#";
        return "";

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


}
