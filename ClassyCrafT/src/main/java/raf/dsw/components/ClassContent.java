package raf.dsw.components;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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
