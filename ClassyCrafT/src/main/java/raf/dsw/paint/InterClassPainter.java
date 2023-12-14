package raf.dsw.paint;

import raf.dsw.components.*;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class InterClassPainter extends ElementPainter{
    private InterClass selectedElement;
    private int requiredWidth, requiredHeight;
    public InterClassPainter(InterClass selectedElement, int w, int h, int x, int y){
        super(selectedElement);
        this.selectedElement = selectedElement;
        requiredHeight = h;
        requiredWidth = w;
        selectedElement.setX(x);
        selectedElement.setY(y);
    }
    public InterClassPainter(DiagramElement dgElement){super(dgElement);}

    @Override
    public void draw(Graphics g) {
        if(selectedElement == null)
            return;
        if(selectedElement instanceof Klasa)
            drawKlasa(g);
        else if(selectedElement instanceof Interfejs)
            drawInterfejs(g);
        else
            drawEnum(g);

    }

    private void drawEnum(Graphics g) {
        List<ClanEnuma> ccc = selectedElement.getNEnum();
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

    private void drawInterfejs(Graphics g) {
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

            if (this.getDgElement().isSelected())
            {
                selectedElement.setColourOutline("0x00FFFF");
            }
            else
            {
                selectedElement.setColourOutline("0x000000");
            }

            g2D.setColor(Color.BLACK); // boja za text

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

    private void drawKlasa(Graphics g) {
        List<ClassContent> ccc = selectedElement.getCl();
        Graphics2D g2D = (Graphics2D) g;
        BasicStroke basicStroke = new BasicStroke(1);
        g2D.setStroke(basicStroke);
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
        FontMetrics fm = g2D.getFontMetrics();
        int xOffset = (int) selectedElement.getX() + 10;
        int height = fm.getHeight();
        int yOffset = (int) selectedElement.getY() + fm.getHeight();
        g2D.setColor(Color.BLACK); // boja za text
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
    public String promeniVidljivostUOznaku(String str){
        if (str.equals("private"))
            return "-";
        if (str.equals("public"))
            return "+";
        if (str.equals("protected"))
            return "#";
        return "";

    }

    @Override
    public boolean elementAt(int x, int y) {
        return getShape().contains(x, y);
    }
    public boolean doTheyOverlap(int x1, int y1, int x2, int y2, int w1, int w2, int h1, int h2){
        if((x2 >= x1 && x2<=(x1+w1) && (y2+h2) >= y1 && (y2+h2) <= (y1+h1)))
            return true;
        if(x2 >= x1 && x2 <= x1 + w1 && y2 >= y1 && y2 <= (y1+h1))
            return true;
        return false;
    }
    public int getRequiredWidth(){return requiredWidth;}
    public int getRequiredHeight(){return  requiredHeight;}
    public int getXCoord(){return (int)selectedElement.getX();}
    public int getYCoord(){return (int)selectedElement.getY();}

    //neki stari draw
    /*@Override
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
        double yString = newKlasa.getY() + ((double) (heightRectangle + fm.getAscent()) / 2);
        g2D.drawString(newKlasa.getName(), (float) xString, (float) yString);
    }*/
}
