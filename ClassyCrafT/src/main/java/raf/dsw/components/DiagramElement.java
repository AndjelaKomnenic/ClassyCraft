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

    public void setStrokeWidth(int strokeWidth) {
        if(this.strokeWidth == strokeWidth)
            return;
        this.strokeWidth = strokeWidth;
        updateChanged();
    }

    public void setColourInside(int colourInside) {
        if(this.colourInside == colourInside)
            return;
        this.colourInside = colourInside;
        updateChanged();
        if (getParent() != null) {
            getParent().notifySubscriber("REPAINT");
        }
    }

    public void setSelected(boolean selected) {
        if(this.selected == selected)
            return;
        this.selected = selected;
        updateChanged();
    }

    public void setColourOutline(int colourOutline) {
        if(this.colourOutline == colourOutline)
            return;
        this.colourOutline = colourOutline;
        updateChanged();
        if (getParent() != null) {
            getParent().notifySubscriber("REPAINT");
        }
    }

    public void setColourInside(String colour){
        setColourInside(Integer.decode(colour));
    }

    public void setColourOutline(String colour){
        setColourOutline(Integer.decode(colour));

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