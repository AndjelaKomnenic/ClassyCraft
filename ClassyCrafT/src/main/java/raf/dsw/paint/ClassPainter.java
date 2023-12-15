package raf.dsw.paint;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.components.*;
import raf.dsw.popUps.PopUpChooseIC;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import static java.awt.Color.cyan;

@Getter
@Setter
public class ClassPainter extends ElementPainter{
    private PopUpChooseIC popUpChooseICInstance;
    private InterClass selectedElement;
    private int requiredWidth, requiredHeight;
    public ClassPainter(DiagramElement dgElement, PopUpChooseIC popUpChooseICInstance) {
        super(dgElement);
        this.popUpChooseICInstance = popUpChooseICInstance;
    }
    public ClassPainter(InterClass selectedElement, int w, int h, int x, int y){
        super(selectedElement);
        requiredHeight = h;
        requiredWidth = w;
    }
    @Override
    public void draw(Graphics g) {
        selectedElement = popUpChooseICInstance.getSelectedElement();
        int startX = (int)selectedElement.getX();
        int startY = (int)selectedElement.getY();
        if (selectedElement != null) {
            List<ClassContent> ccc = selectedElement.getCl();
            //System.out.println("Size of ccc: " + ccc.size());
            Graphics2D g2D = (Graphics2D) g;
            FontMetrics fm = g2D.getFontMetrics();
            BasicStroke basicStroke = new BasicStroke(1);
            g2D.setStroke(basicStroke);

            int maxWidth = fm.stringWidth("(C) " + promeniVidljivostUOznaku(selectedElement.getVidljivost()) + " " + selectedElement.getName()) + 20;
            int height = fm.getHeight();
            int yOffset = (int) selectedElement.getY() + fm.getHeight();

            requiredWidth = maxWidth;
            requiredHeight = ((ccc.size() + 1) * (height + 5)) + height * 3;

            int selectedElementNameWidth = fm.stringWidth("(C) " + promeniVidljivostUOznaku(selectedElement.getVidljivost()) + " " + selectedElement.getName());


            int maxAttributeWidth = 0;
            for (ClassContent element : ccc) {
                if (element instanceof Atribut) {
                    int elementWidth = fm.stringWidth(promeniVidljivostUOznaku(element.getVidljivost()) + " " + element.getTip() + " " + element.getNaziv());
                    maxAttributeWidth = Math.max(maxAttributeWidth, elementWidth);
                }
            }


            int maxMethodWidth = 0;
            for (ClassContent element : ccc) {
                if (element instanceof Metod) {
                    int elementWidth = fm.stringWidth(promeniVidljivostUOznaku(element.getVidljivost()) + " " + element.getTip() + " " + element.getNaziv());
                    maxMethodWidth = Math.max(maxMethodWidth, elementWidth);
                }
            }


            requiredWidth = Math.max(selectedElementNameWidth, Math.max(maxAttributeWidth, maxMethodWidth)) + 20;


            selectedElement.setWidthAndHeight(requiredWidth, requiredHeight);
            setShape(new Rectangle2D.Double(selectedElement.getX(), selectedElement.getY(), requiredWidth, requiredHeight));


            g2D.setColor(new Color(selectedElement.getColourInside()));
            g2D.fillRect((int) selectedElement.getX(), (int) selectedElement.getY(), requiredWidth, requiredHeight);


            if (this.getDgElement().isSelected())
            {
                selectedElement.setColourOutline("0x00FFFF");
            }
            else
            {
                selectedElement.setColourOutline("0x000000");
            }
            int xOffset = (int) selectedElement.getX() + 10;

            g2D.setColor(Color.BLACK); // boja za text``````
            g2D.drawString("(C)" + promeniVidljivostUOznaku(selectedElement.getVidljivost()) + " " + selectedElement.getName(), xOffset, yOffset);
            yOffset += height + 5;

            g2D.drawLine((int) selectedElement.getX(), yOffset, (int) (selectedElement.getX() + requiredWidth), yOffset);
            yOffset += height + 5;


            for (ClassContent element : ccc) {
                if (element instanceof Atribut) {
                    String attributeString = promeniVidljivostUOznaku(element.getVidljivost()) + " " + element.getTip() + " " + element.getNaziv();
                    g2D.drawString(attributeString, xOffset, yOffset);
                    yOffset += height + 5;
                }
            }


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

        //System.out.println(getRectangleCoordinates());
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

    public String promeniVidljivostUOznaku(String str){
        if (str.equals("private"))
            return "-";
        if (str.equals("public"))
            return "+";
        if (str.equals("protected"))
            return "#";
        if(str.equals("package"))
            return "~";
        return "";

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