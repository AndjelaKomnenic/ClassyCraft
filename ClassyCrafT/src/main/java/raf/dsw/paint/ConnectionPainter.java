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
            double length = Math.sqrt((finishX - startX)*(finishX - startX) + (finishY - startY)*(finishY - startY));
            double unitVectorX = (startX - finishX) / length;
            double unitVectorY = (startY - finishY) / length;
            double firstDistance = -10;
            double secondDistance = -20;
            double newfirstX = startX + unitVectorX * firstDistance;
            double newfirstY = startY + unitVectorY * firstDistance;
            double newsecondX = startX + unitVectorX * secondDistance;
            double newsecondY = startY + unitVectorY * secondDistance;
            Point newPoint = new Point((int) newfirstX, (int) newfirstY);
            double perpendicularX1 = unitVectorY;
            double perpendicularY1 = -unitVectorX;
            double perpendicularX2 = -unitVectorY;
            double perpendicularY2 = unitVectorX;
            double lineLength = 9;
            Point perpendicularPoint1 = new Point((int) (newfirstX + perpendicularX1 * lineLength),
                    (int) (newfirstY + perpendicularY1 * lineLength));
            Point perpendicularPoint2 = new Point((int) (newfirstX + perpendicularX2 * lineLength),
                    (int) (newfirstY + perpendicularY2 * lineLength));
            g.drawLine(perpendicularPoint1.x, perpendicularPoint1.y, startX, startY);
            g.drawLine(perpendicularPoint2.x, perpendicularPoint2.y, startX, startY);
            g.drawLine((int)newsecondX, (int)newsecondY, perpendicularPoint1.x, perpendicularPoint1.y);
            g.drawLine((int)newsecondX, (int)newsecondY, perpendicularPoint2.x, perpendicularPoint2.y);
            startX = (int)newsecondX;
            startY = (int)newsecondY;
        }
        else if(dgElement instanceof Kompozicija){
            double length = Math.sqrt((finishX - startX)*(finishX - startX) + (finishY - startY)*(finishY - startY));
            double unitVectorX = (startX - finishX) / length;
            double unitVectorY = (startY - finishY) / length;
            double firstDistance = -10;
            double secondDistance = -20;
            double newfirstX = startX + unitVectorX * firstDistance;
            double newfirstY = startY + unitVectorY * firstDistance;
            double newsecondX = startX + unitVectorX * secondDistance;
            double newsecondY = startY + unitVectorY * secondDistance;
            Point newPoint = new Point((int) newfirstX, (int) newfirstY);
            double perpendicularX1 = unitVectorY;
            double perpendicularY1 = -unitVectorX;
            double perpendicularX2 = -unitVectorY;
            double perpendicularY2 = unitVectorX;
            double lineLength = 9;
            Point perpendicularPoint1 = new Point((int) (newfirstX + perpendicularX1 * lineLength),
                    (int) (newfirstY + perpendicularY1 * lineLength));
            Point perpendicularPoint2 = new Point((int) (newfirstX + perpendicularX2 * lineLength),
                    (int) (newfirstY + perpendicularY2 * lineLength));
            int []xpoints = {startX, (int)perpendicularPoint1.x, (int)newsecondX, (int)perpendicularPoint2.x};
            int []ypoints = {startY, (int)perpendicularPoint1.y, (int)newsecondY, (int)perpendicularPoint2.y};
            Shape s = new Polygon(xpoints, ypoints, 4);
            g.fill(s);
            g.drawLine(perpendicularPoint1.x, perpendicularPoint1.y, startX, startY);
            g.drawLine(perpendicularPoint2.x, perpendicularPoint2.y, startX, startY);
            g.drawLine((int)newsecondX, (int)newsecondY, perpendicularPoint1.x, perpendicularPoint1.y);
            g.drawLine((int)newsecondX, (int)newsecondY, perpendicularPoint2.x, perpendicularPoint2.y);
            startX = (int)newsecondX;
            startY = (int)newsecondY;
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
        else if(dgElement instanceof Zavisnost){
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
