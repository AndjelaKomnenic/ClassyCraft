package raf.dsw.components;

import lombok.Getter;
import raf.dsw.classyrepository.composite.ClassyNode;

import java.util.ArrayList;
import java.util.List;
@Getter
public class Enum extends InterClass{

    List<ClanEnuma> clanE;

    public Enum() {
        clanE = new ArrayList<>();
    }

    public Enum(String name, ClassyNode parent, double x, double y) {
        super(name, parent, x, y);
    }

    @Override
    public String tipKlase() {
        return "Enum";
    }
}