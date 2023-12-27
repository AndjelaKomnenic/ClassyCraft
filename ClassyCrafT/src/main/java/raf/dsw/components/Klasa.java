package raf.dsw.components;

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
}
