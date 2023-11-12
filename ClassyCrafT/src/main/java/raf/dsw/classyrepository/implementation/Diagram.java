package raf.dsw.classyrepository.implementation;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classyrepository.composite.ClassyNode;

@Getter
@Setter

public class Diagram extends ClassyNode {
    public Diagram(String name, ClassyNode parent) {
        super(name, parent);
    }
    /*TODO*/
}
