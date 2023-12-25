package raf.dsw.components;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import raf.dsw.classyrepository.composite.ClassyNode;

import java.util.ArrayList;
import java.util.List;
@Getter
public class Enum extends InterClass{

    //List<ClanEnuma> nEnum = new ArrayList<>();
    public Enum(String name, ClassyNode parent, double x, double y) {
        super(name, parent, x, y);
    }
    @JsonCreator
    public Enum(@JsonProperty("naziv")String naziv
            , @JsonProperty("parent")ClassyNode parent
            , @JsonProperty("vidljivost")String vidljivost
            , @JsonProperty("x")double x
            , @JsonProperty("y")double y
            , @JsonProperty("width")double width
            , @JsonProperty("height")double height
            , @JsonProperty("ce")List<ClanEnuma> ce
            , @JsonProperty("lc")List<Connection> lc) {
        super(naziv, parent, x, y);
        setVidljivost(vidljivost);
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        for(ClanEnuma clCon: ce)
            addToListE(clCon);
        for(Connection con: lc)
            addToListVeza(con);
    }
}
