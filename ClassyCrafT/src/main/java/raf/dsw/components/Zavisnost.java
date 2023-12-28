package raf.dsw.components;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import raf.dsw.classyrepository.composite.ClassyNode;

public class Zavisnost extends Connection{
    public Zavisnost(String name, ClassyNode parent, InterClass from, InterClass to) {
        super(name, parent, from, to);
    }

    public Zavisnost(){
        super(null,null,null,null);
    }

}
