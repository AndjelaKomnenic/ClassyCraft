package raf.dsw.components;

import raf.dsw.classyrepository.composite.ClassyNode;

public class Agregacija extends Connection{
    public Agregacija(String name, ClassyNode parent, InterClass from, InterClass to) {
        super(name, parent, from, to);
    }

    public Agregacija(){
        super(null,null,null,null);
    }

}