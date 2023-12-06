package raf.dsw.components;

import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.implementation.Diagram;

public abstract class AbstractAbstractFactory {
    abstract public InterClass newInterClass(String type, ClassyNode parent);
    abstract public Connection newConnection(String type, Diagram parent);
}
