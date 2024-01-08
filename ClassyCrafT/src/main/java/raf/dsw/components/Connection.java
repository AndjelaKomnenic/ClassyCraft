package raf.dsw.components;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;
import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.state.Tacka;

@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public abstract class Connection extends DiagramElement{
    @JsonIgnore
    private InterClass from;
    @JsonIgnore
    private InterClass to;
    //ova dva
    private String vidljivost;
    private String kardinalnost;

    private String fromNaziv;
    private String fromType;

    private String toNaziv;
    private String toType;


    private int fromX, fromY;
    private int toX, toY;

    public Connection(String name, ClassyNode parent, InterClass from, InterClass to) {
        super(name, parent);
        setFrom(from);
        setTo(to);
    }

    public void setVidljivost(String vidljivost) {
        if (this.vidljivost == null && vidljivost == null)
            return;
        if(this.vidljivost != null && this.vidljivost.equals(vidljivost))
            return;
        this.vidljivost = vidljivost;
        updateChanged();
    }

    public void setKardinalnost(String kardinalnost) {
        if (this.kardinalnost == null && kardinalnost == null)
            return;
        if(this.kardinalnost != null && this.kardinalnost.equals(kardinalnost))
            return;
        this.kardinalnost = kardinalnost;
        updateChanged();
    }

    public void setFromNaziv(String fromNaziv) {
        if (this.fromNaziv == null && fromNaziv == null)
            return;
        if(this.fromNaziv != null && this.fromNaziv.equals(fromNaziv))
            return;
        this.fromNaziv = fromNaziv;
        updateChanged();
    }

    public void setFromType(String fromType) {
        if (this.fromType == null && fromType == null)
            return;
        if(this.fromType != null && this.fromType.equals(fromType))
            return;
        this.fromType = fromType;
        updateChanged();
    }

    public void setToNaziv(String toNaziv) {
        if (this.toNaziv == null && toNaziv == null)
            return;
        if(this.toNaziv != null && this.toNaziv.equals(toNaziv))
            return;
        this.toNaziv = toNaziv;
        updateChanged();
    }

    public void setToType(String toType) {
        if (this.toType == null && toType == null)
            return;
        if(this.toType != null && this.toType.equals(toType))
            return;
        this.toType = toType;
        updateChanged();
    }

    public void setFromX(int fromX) {
        if (this.fromX == fromX)
            return;
        this.fromX = fromX;
        updateChanged();
    }

    public void setFromY(int fromY) {
        if(this.fromY == fromY)
            return;
        this.fromY = fromY;
        updateChanged();
    }

    public void setToX(int toX) {
        if(this.toX == toX)
            return;
        this.toX = toX;
        updateChanged();
    }

    public void setToY(int toY) {
        if(this.toY == toY)
            return;
        this.toY = toY;
        updateChanged();
    }

    public void setFrom(InterClass fromInter){
        if (this.from == fromInter)
            return;

        if(fromInter != null) {
            this.from = fromInter;
            this.fromNaziv = fromInter.getName();
            this.fromType = fromInter.tipKlase();
            recalculateCoordinates();
            updateChanged();
        }
    }

    public void setTo(InterClass toInter){
        if(this.to == toInter)
            return;

        if(toInter != null)
        {
            this.to = toInter;
            this.toNaziv = toInter.getName();
            this.toType = toInter.tipKlase();
            recalculateCoordinates();
            updateChanged();
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
                    setFromX((int)point1.getX());
                    setFromY((int)point1.getY());
                    setToX((int)point2.getX());
                    setToY((int)point2.getY());
                    //updateChanged();
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
}