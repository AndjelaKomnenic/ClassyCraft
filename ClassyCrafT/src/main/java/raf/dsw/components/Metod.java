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
    @JsonCreator
    public Metod(@JsonProperty("naziv")String naziv
            , @JsonProperty("tip") String tip
            , @JsonProperty("vidljivost")String vidljivost
            , @JsonProperty("parent") InterClass parent) {
        super(vidljivost, tip, naziv);
    }

}
