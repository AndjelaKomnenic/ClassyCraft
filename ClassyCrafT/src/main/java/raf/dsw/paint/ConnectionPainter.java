package raf.dsw.paint;

import raf.dsw.components.*;
import raf.dsw.workspace.view.PackageView;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

public class ConnectionPainter extends ElementPainter{
    private Connection dgElement;
    private int startX, startY, finishX, finishY;
    public ConnectionPainter(Connection dgElement) {
        super(dgElement);
        this.dgElement = dgElement;
    }

    @Override
    public void draw(Graphics gr) {
        BasicStroke basicStroke = new BasicStroke(2);
        Graphics2D g = (Graphics2D) gr;
        g.setStroke(basicStroke);
        if (this.getDgElement().isSelected())
        {
            dgElement.setColourOutline("0x00FFFF");
        }
        else
        {
            dgElement.setColourOutline("0x000000");
        }
        startX = dgElement.getFromX();
        startY = dgElement.getFromY();
        finishX = dgElement.getToX();
        finishY = dgElement.getToY();
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
            double ppX1 = unitVectorY;
            double ppY1 = -unitVectorX;
            double ppX2 = -unitVectorY;
            double ppY2 = unitVectorX;
            double lineLength = 9;
            Point p1 = new Point((int) (newfirstX + ppX1 * lineLength),
                    (int) (newfirstY + ppY1 * lineLength));
            Point p2 = new Point((int) (newfirstX + ppX2 * lineLength),
                    (int) (newfirstY + ppY2 * lineLength));
            g.drawLine(p1.x, p1.y, startX, startY);
            g.drawLine(p2.x, p2.y, startX, startY);
            g.drawLine((int)newsecondX, (int)newsecondY, p1.x, p1.y);
            g.drawLine((int)newsecondX, (int)newsecondY, p2.x, p2.y);
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
            double ppX1 = unitVectorY;
            double ppY1 = -unitVectorX;
            double ppX2 = -unitVectorY;
            double ppY2 = unitVectorX;
            double lineLength = 9;
            Point p1 = new Point((int) (newfirstX + ppX1 * lineLength),
                    (int) (newfirstY + ppY1 * lineLength));
            Point p2 = new Point((int) (newfirstX + ppX2 * lineLength),
                    (int) (newfirstY + ppY2 * lineLength));
            int []xpoints = {startX, (int)p1.x, (int)newsecondX, (int)p2.x};
            int []ypoints = {startY, (int)p1.y, (int)newsecondY, (int)p2.y};
            Shape s = new Polygon(xpoints, ypoints, 4);
            g.fill(s);
            g.drawLine(p1.x, p1.y, startX, startY);
            g.drawLine(p2.x, p2.y, startX, startY);
            g.drawLine((int)newsecondX, (int)newsecondY, p1.x, p1.y);
            g.drawLine((int)newsecondX, (int)newsecondY, p2.x, p2.y);
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
            double ppX1 = unitVectorY;
            double ppY1 = -unitVectorX;
            double ppX2 = -unitVectorY;
            double ppY2 = unitVectorX;
            double lineLength = 9;
            Point p1 = new Point((int) (newX + ppX1 * lineLength),
                    (int) (newY + ppY1 * lineLength));
            Point p2 = new Point((int) (newX + ppX2 * lineLength),
                    (int) (newY + ppY2 * lineLength));
            g.drawLine(p1.x, p1.y, p2.x, p2.y);
            g.drawLine(p1.x, p1.y, finishX, finishY);
            g.drawLine(p2.x, p2.y, finishX, finishY);
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
        double n = Math.abs((finishX - startX) * (startY - y) - (startX - x) * (finishY - startY));
        double d = Math.sqrt(Math.pow(finishX - startX, 2) + Math.pow(finishY - startY, 2));
        double pointToLineDistance = n/d;
        return pointToLineDistance <= 5;
    }

    @Override
    public List<Point2D.Double> getRectangleCoordinates() {
        return null;
    }
}
