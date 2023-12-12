package raf.dsw.components;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classyrepository.composite.ClassyNode;
@Getter
@Setter
public class ClanEnuma {

    private Enum en;
    private String value;
    public ClanEnuma(String value) {
        this.value = value;
    }
}
