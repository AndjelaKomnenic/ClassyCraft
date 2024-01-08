package raf.dsw.classyrepository.composite;

import raf.dsw.classyrepository.composite.ClassyNode;

import java.util.ArrayList;
import java.util.List;

public abstract class ClassyNodeComposite extends ClassyNode {
    protected List<ClassyNode> children;
    private int counter;


    public ClassyNodeComposite() {

    }
    public ClassyNodeComposite(String name, ClassyNode parent) {
        super(name, parent);
        this.children = new ArrayList<>();
        counter = 0;
    }

    public abstract void addChild(ClassyNode child);
    public void removeChild(ClassyNode child){
        if(child != null && !children.isEmpty()) {
            children.remove(child);
            updateChanged();
            child.notifySubscriber("REMOVE");
        }
    }

    @Override
    public void resetChanged() {
        if (children != null && !children.isEmpty())
        {
            for (ClassyNode child: children)
            {
                child.resetChanged();
            }
        }
        super.resetChanged();
    }

    public boolean cotainsSameNameComponent(String name){
        for(ClassyNode child : children) {
            if (child.getName().trim().equals(name.trim()))
                return true;
        }
        return false;
    }

    public void setCounter() {
        this.counter += 1;
        updateChanged();
    }
    public int getCounter() {
        return counter;
    }
    public List<ClassyNode> getChildren(){
        if(this.children == null)
        {
            this.children = new ArrayList<>();
        }
        return this.children;
    }

    public void setChildren(List<ClassyNode> children) {
        if (this.children == children)
        {
            return;
        }
        this.children = children;
        updateChanged();
    }
}