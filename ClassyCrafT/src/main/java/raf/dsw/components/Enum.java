package raf.dsw.components;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    public void setClanE(List<ClanEnuma> clanE) {
        if(this.clanE == clanE)
            return;
        this.clanE = clanE;
        updateChanged();
    }

    @Override
    public String tipKlase() {
        return "Enum";
    }
}