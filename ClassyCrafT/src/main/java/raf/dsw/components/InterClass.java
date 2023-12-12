package raf.dsw.components;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classyrepository.composite.ClassyNode;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class InterClass extends DiagramElement{

    private String naziv;
    private String vidljivost;
    private double x, y;
    private double width, height;

    private List<ClassContent> cl = new ArrayList<>();
    private List<ClanEnuma> nEnum = new ArrayList<>();

    public InterClass(String name, ClassyNode parent, double x, double y) {
        super(name, parent);
        this.x = x;
        this.y = y;
    }

    public void setWidthAndHeight(double width, double height){
        this.width = width;
        this.height = height;
    }

    public void setX(double x){
        this.x = x;
        getParent().notifySubscriber("REPAINT");
    }

    public void setY(double y){
        this.y = y;
        getParent().notifySubscriber("REPAINT");
    }
    public void addToList(ClassContent cc){
        cl.add(cc);
    }

    public void addToListE(ClanEnuma ce){
        nEnum.add(ce);

    public List<ClassContent> getList(){return cl;}

    public void setVidljivost(String vidljivost) {
        this.vidljivost = vidljivost;

    }
}
