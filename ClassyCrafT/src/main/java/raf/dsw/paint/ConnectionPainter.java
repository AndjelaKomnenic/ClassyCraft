package raf.dsw.paint;

import raf.dsw.components.*;

import java.awt.*;

public class ConnectionPainter extends ElementPainter{
    private Connection dgElement;
    public ConnectionPainter(Connection dgElement) {
        super(dgElement);
        this.dgElement = dgElement;
    }

    @Override
    public void draw(Graphics gr) {
        BasicStroke basicStroke = new BasicStroke(2);
        Graphics2D g = (Graphics2D) gr;
        g.setStroke(basicStroke);
        g.setColor(Color.BLACK);
        int startX = dgElement.getFromX();
        int startY = dgElement.getFromY();
        int finishX = dgElement.getToX();
        int finishY = dgElement.getToY();
        if(dgElement instanceof Agregacija) {
            g.setColor(Color.BLACK);
        }
        else if(dgElement instanceof Kompozicija){
            g.setColor(Color.cyan);
        }
        else if(dgElement instanceof Generalizacija){
            double length = Math.sqrt((finishX - startX)*(finishX - startX) + (finishY - startY)*(finishY - startY));
            double unitVectorX = (finishX - startX) / length;
            double unitVectorY = (finishY - startY) / length;
            double distance = -10;
            double newX = finishX + unitVectorX * distance;
            double newY = finishY + unitVectorY * distance;
            Point newPoint = new Point((int) newX, (int) newY);
            double perpendicularX1 = unitVectorY;
            double perpendicularY1 = -unitVectorX;
            double perpendicularX2 = -unitVectorY;
            double perpendicularY2 = unitVectorX;
            double lineLength = 9;
            Point perpendicularPoint1 = new Point((int) (newX + perpendicularX1 * lineLength),
                    (int) (newY + perpendicularY1 * lineLength));
            Point perpendicularPoint2 = new Point((int) (newX + perpendicularX2 * lineLength),
                    (int) (newY + perpendicularY2 * lineLength));
            g.drawLine(perpendicularPoint1.x, perpendicularPoint1.y, perpendicularPoint2.x, perpendicularPoint2.y);
            g.drawLine(perpendicularPoint1.x, perpendicularPoint1.y, finishX, finishY);
            g.drawLine(perpendicularPoint2.x, perpendicularPoint2.y, finishX, finishY);
            finishX = (int)newX;
            finishY = (int)newY;
        }
        else{
            Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                    0, new float[]{9}, 0);
            g.setStroke(dashed);
        }
        g.drawLine(startX, startY, finishX, finishY);
    }

    @Override
    public boolean elementAt(int x, int y) {
        return false;
    }
}
