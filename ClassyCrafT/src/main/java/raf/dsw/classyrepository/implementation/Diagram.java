package raf.dsw.classyrepository.implementation;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.composite.ClassyNodeComposite;

@Getter
@Setter

public class Diagram extends ClassyNodeComposite {
    public Diagram(String name, ClassyNode parent) {
        super(name, parent);
    }

    @Override
    public void addChild(ClassyNode child) {
        if(!children.contains(child))
        {
            children.add(child);
            this.notifySubscriber("NEW");
        }
        this.notifySubscriber("REPAINT");
    }
}
