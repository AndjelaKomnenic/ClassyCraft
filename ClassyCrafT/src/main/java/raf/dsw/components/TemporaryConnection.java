package raf.dsw.components;

import raf.dsw.classyrepository.composite.ClassyNode;

public class TemporaryConnection extends Connection{
    public TemporaryConnection(String name, ClassyNode parent) {
        super(name, parent, null, null);
    }
}
