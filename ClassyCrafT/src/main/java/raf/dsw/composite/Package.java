package raf.dsw.composite;

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

    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }
}