package raf.dsw.components;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Metod extends ClassContent{
    public Metod()
    {
        super(null, null, null);
    }
    public Metod(String vidljivost, String tip, String naziv) {
        super(vidljivost, tip, naziv);
    }

}
