package raf.dsw.components;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classyrepository.composite.ClassyNode;

@Getter
@Setter
public abstract class Connection extends DiagramElement{

    private InterClass from;
    private InterClass to;

    private double fromX, fromY;
    private double toX, toY;

    public Connection(String name, ClassyNode parent, InterClass from) {
        super(name, parent);
        setFrom(from);
    }

    public void setFrom(InterClass fromInter){
        this.from = fromInter;
        fromX = from.getX() + (from.getWidth() / 2);
        fromY = from.getY() + (from.getHeight() / 2);
    }

    public void setTo(InterClass toInter){
        this.to = toInter;
        toX = to.getX() + (to.getWidth() / 2);
        toY = to.getY() + (to.getHeight() / 2);
    }

    public void recalculateCoordinates(){
        if (to == null)
            return;

        fromX = from.getX() + (from.getWidth() / 2);
        fromY = from.getY() + (from.getHeight() / 2);
        toX = to.getX() + (to.getWidth() / 2);
        toY = to.getY() + (to.getHeight() / 2);
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

}
