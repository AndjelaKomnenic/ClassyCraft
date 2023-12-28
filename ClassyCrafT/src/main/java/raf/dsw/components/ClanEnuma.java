package raf.dsw.components;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;
import raf.dsw.classyrepository.composite.ClassyNode;
@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class ClanEnuma {

    private Enum en;
    private String value;
    public ClanEnuma(String value) {
        this.value = value;
    }

    public ClanEnuma()
    {

    }
}