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
/*<<<<<<< HEAD
    private List<ClassContent> classContentList = new ArrayList<>();
=======*/
    private List<ClassContent> cl = new ArrayList<>();

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
}
