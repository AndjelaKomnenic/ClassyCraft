package raf.dsw.components;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import raf.dsw.classyrepository.composite.ClassyNode;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Klasa extends InterClass{

    //private List<ClassContent> listaClassCont = new ArrayList<>();
    //private InterClass itemFrom, itemTo;
    private boolean apstraktna;

    public Klasa(){         //za Json, nema drugu svrhu

    }
    public Klasa(String name, ClassyNode parent, double x, double y) {
        super(name, parent, x, y);
    }
    @JsonCreator
    public Klasa(@JsonProperty("naziv")String naziv
            , @JsonProperty("parent")ClassyNode parent
            , @JsonProperty("vidljivost")String vidljivost
            , @JsonProperty("x")double x
            , @JsonProperty("y")double y
            , @JsonProperty("width")double width
            , @JsonProperty("height")double height
            , @JsonProperty("cc")List<ClassContent> cc
            , @JsonProperty("lc")List<Connection> lc
            , @JsonProperty("apstraktna")boolean apstraktna) {
        super(naziv, parent, x, y);
        setVidljivost(vidljivost);
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        for(ClassContent clCon: cc)
            addToList(clCon);
        for(Connection con: lc)
            addToListVeza(con);
        setApstraktna(apstraktna);

    }
}
