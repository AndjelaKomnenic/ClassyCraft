package raf.dsw.components;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import raf.dsw.classyrepository.composite.ClassyNode;

public class Kompozicija extends Connection{
    public Kompozicija(String name, ClassyNode parent, InterClass from, InterClass to) {
        super(name, parent, from, to);
    }

    public Kompozicija(){
        super(null,null,null,null);
    }
}

