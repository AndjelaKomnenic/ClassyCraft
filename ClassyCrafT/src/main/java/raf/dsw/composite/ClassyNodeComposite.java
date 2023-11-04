package raf.dsw.composite;

import java.util.ArrayList;
import java.util.List;

public abstract class ClassyNodeComposite extends ClassyNode{
    protected List<ClassyNode> children;

    public ClassyNodeComposite(String name, ClassyNode parent) {
        super(name, parent);
        this.children = new ArrayList<>();
    }

    public abstract void addChild(ClassyNode child);
    public void removeChild(ClassyNode child){
        if(child != null && !children.isEmpty())
            children.remove(child);
        child.notifySubscriber("REMOVE");
    }

}