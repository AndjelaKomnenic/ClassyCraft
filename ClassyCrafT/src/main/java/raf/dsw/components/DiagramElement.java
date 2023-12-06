package raf.dsw.components;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classyrepository.composite.ClassyNode;

@Getter
@Setter
public abstract class DiagramElement extends ClassyNode{

    private int strokeWidth;
    private int colourInside;
    private int colourOutline;
    private boolean selected;

    public DiagramElement(String name, ClassyNode parent){
        super(name, parent);
        strokeWidth = 2;
        colourInside = 0xffffff;
        colourOutline = 0x000000;
    }

    public void setColourInside(String colour){
        this.colourInside = Integer.decode(colour);
        getParent().notifySubscriber("REPAINT");
    }

    public void setColourOutline(String colour){
        this.colourOutline =Integer.decode(colour);
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

        return this.getName().equals(that.getName()) && this.getParent().equals(that.getParent());
    }

}
