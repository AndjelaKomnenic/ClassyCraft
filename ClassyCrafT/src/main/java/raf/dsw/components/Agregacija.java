package raf.dsw.components;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import raf.dsw.classyrepository.composite.ClassyNode;

import java.util.List;

public class Agregacija extends Connection{
    public Agregacija(String name, ClassyNode parent, InterClass from, InterClass to) {
        super(name, parent, from, to);
    }


    public Agregacija(){
        super(null,null,null,null);
    }

}
