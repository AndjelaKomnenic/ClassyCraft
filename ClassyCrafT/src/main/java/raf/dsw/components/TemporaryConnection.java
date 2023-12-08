package raf.dsw.components;

import raf.dsw.classyrepository.composite.ClassyNode;

public class TemporaryConnection extends Connection{
    public TemporaryConnection(String name, ClassyNode parent, InterClass from) {
        super(name, parent, from);
    }
}
