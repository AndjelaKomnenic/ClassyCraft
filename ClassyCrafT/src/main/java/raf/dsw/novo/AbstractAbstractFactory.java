package raf.dsw.novo;

import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.composite.ClassyNodeComposite;
import raf.dsw.classyrepository.implementation.Diagram;

public abstract class AbstractAbstractFactory {
    abstract public InterClass newInterClass(String type, ClassyNode parent);
    abstract public Connection newConnection(String type, Diagram parent);
}
