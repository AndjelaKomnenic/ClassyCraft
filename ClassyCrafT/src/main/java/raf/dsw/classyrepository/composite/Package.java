package raf.dsw.classyrepository.composite;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Package extends ClassyNodeComposite{
    /*TODO*/
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