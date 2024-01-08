package raf.dsw.components;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import raf.dsw.classyrepository.composite.ClassyNode;

import java.util.List;

public class Atribut extends ClassContent{

    public Atribut()
    {
        super(null, null, null);
    }
    public Atribut(String vidljivost, String tip, String naziv){
        super(vidljivost, tip, naziv);
    }

}
