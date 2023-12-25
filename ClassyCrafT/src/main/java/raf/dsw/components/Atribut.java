package raf.dsw.components;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import raf.dsw.classyrepository.composite.ClassyNode;

import java.util.List;

public class Atribut extends ClassContent{

    public Atribut(String vidljivost, String tip, String naziv){
        super(vidljivost, tip, naziv);
    }
    @JsonCreator
    public Atribut(@JsonProperty("naziv")String naziv
            , @JsonProperty("tip") String tip
            , @JsonProperty("vidljivost")String vidljivost
            , @JsonProperty("parent") InterClass parent) {
        super(vidljivost, tip, naziv);
    }
}
