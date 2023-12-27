package raf.dsw.components;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import raf.dsw.classyrepository.composite.ClassyNode;

import java.util.List;

public class Agregacija extends Connection{
    public Agregacija(String name, ClassyNode parent, InterClass from, InterClass to) {
        super(name, parent, from, to);
    }
    @JsonCreator
    public Agregacija(@JsonProperty("name")String name
            , @JsonProperty("parent")ClassyNode parent
            , @JsonProperty("from")InterClass from
            , @JsonProperty("to")InterClass to
            , @JsonProperty("fromX")int fromX
            , @JsonProperty("fromY")int fromY
            , @JsonProperty("toX")int toX
            , @JsonProperty("toY")int toY
            , @JsonProperty("filePath") String filePath) {
        super(name, parent, from, to);
        this.setFromX(fromX);
        this.setFromY(fromY);
        this.setToX(toX);
        this.setToY(toY);
    }
    @Override
    public String getClassName(){return "Agregacija";}
}
