package raf.dsw.components;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public abstract class ClassContent {

    private String vidljivost;
    private String tip;
    private String naziv;


    public ClassContent(String vidljivost, String tip, String naziv){
        this.vidljivost = vidljivost;
        this.naziv = naziv;
        this.tip = tip;
    }



}
