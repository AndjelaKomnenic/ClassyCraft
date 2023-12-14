package raf.dsw.components;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.state.Tacka;

@Getter
@Setter
public abstract class Connection extends DiagramElement{

    private InterClass from;
    private InterClass to;

    private int fromX, fromY;
    private int toX, toY;

    public Connection(String name, ClassyNode parent, InterClass from, InterClass to) {
        super(name, parent);
        setFrom(from);
        setTo(to);
    }

    public void setFrom(InterClass fromInter){
        if(fromInter != null) {
            this.from = fromInter;
            recalculateCoordinates();
        }
    }

    public void setTo(InterClass toInter){
        if(toInter != null)
        {
            this.to = toInter;
            recalculateCoordinates();
        }
    }

    public void recalculateCoordinates(){
        if (to == null || from == null)
            return;

        double curr = 0, max = Integer.MAX_VALUE;
        for(Tacka point1 : from.getRectangleCoordinates()){
            for(Tacka point2: to.getRectangleCoordinates()){
                curr = Math.sqrt((point1.getX() - point2.getX())*(point1.getX() - point2.getX())
                        + (point1.getY() - point2.getY())*(point1.getY() - point2.getY()));
                if(curr < max) {
                    max = curr;
                    fromX = point1.getX();
                    fromY = point1.getY();
                    toX = point2.getX();
                    toY = point2.getY();
                }
            }
        }
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Connection that = (Connection) o;

        if(this.to == null)
            return this.from.equals(that.from);
        return this.from.equals(that.from) && this.to.equals(that.to) ||
                this.from.equals(that.to) && this.to.equals(that.from);
    }
    public void tempSetFrom(int x, int y){
        fromX = x;
        fromY = y;
    }
    public void tempSetTo(int x, int y){
        toX = x;
        toY = y;
    }
    public String getClassName(){return "";}
}
