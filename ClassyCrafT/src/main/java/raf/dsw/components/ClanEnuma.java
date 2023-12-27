package raf.dsw.components;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonCreator
    public ClanEnuma(@JsonProperty("value")String value
            , @JsonProperty("en") Enum en) {
        this.value = value;
        this.en = en;
    }
}
