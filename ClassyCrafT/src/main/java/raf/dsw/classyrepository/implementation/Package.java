package raf.dsw.classyrepository.implementation;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.composite.ClassyNodeComposite;

@Getter
@Setter
public class Package extends ClassyNodeComposite {
    public Package(String name, ClassyNode parent) {
        super(name, parent);
    }

    @Override
    public void addChild(ClassyNode child) {
        this.setCounter();
        children.add(child);
        this.notifySubscriber("NEW");
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

}