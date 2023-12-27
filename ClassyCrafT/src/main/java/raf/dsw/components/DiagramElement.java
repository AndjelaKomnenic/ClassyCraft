package raf.dsw.components;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.paint.ElementPainter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class DiagramElement extends ClassyNode{

    private int strokeWidth;
    private int colourInside;
    private boolean selected;
    private int colourOutline;


    public DiagramElement(){
    }
    public DiagramElement(String name, ClassyNode parent){
        super(name, parent);
        strokeWidth = 2;
        colourInside = 0xffffff;
        colourOutline = 0x000000;
    }

    public void setColourInside(String colour){
        this.colourInside = Integer.decode(colour);
        if (getParent() != null)
            getParent().notifySubscriber("REPAINT");
    }

    public void setColourOutline(String colour){
        this.colourOutline =Integer.decode(colour);
        if (getParent() != null)
            getParent().notifySubscriber("REPAINT");
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiagramElement that = (DiagramElement) o;

        if (this.getName() != null ? !this.getName().equals(that.getName()) : that.getName() != null)
            return false;

        // Check for null parents before comparing
        if (this.getParent() == null || that.getParent() == null)
            return false;

        return this.getParent().equals(that.getParent());
    }

}
