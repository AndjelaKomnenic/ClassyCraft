package raf.dsw.components;

import raf.dsw.classyrepository.composite.ClassyNode;

public class Agregacija extends Connection{
    public Agregacija(String name, ClassyNode parent, InterClass from) {
        super(name, parent, from);
    }
    @Override
    public String getClassName(){return "Agregacija";}
}
