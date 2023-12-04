package raf.dsw.novo;

import raf.dsw.classyrepository.implementation.Diagram;

public abstract class AbstractAbstractFactory {
    abstract public InterClass newInterClass(String type, Diagram parent);
    abstract public Connection newConnection(String type, Diagram parent);
}
