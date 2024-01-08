package raf.dsw.components;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import raf.dsw.classyrepository.composite.ClassyNode;

import java.util.ArrayList;
import java.util.List;

public class Interfejs extends InterClass{

    private List<ClassContent> methods;
    public Interfejs(String name, ClassyNode parent, double x, double y) {
        super(name, parent, x, y);
    }

    @Override
    public String tipKlase() {
        return "Interfejs";
    }

    public Interfejs(){ //za Json, nema drugu svrhu
        methods = new ArrayList<>();

    }

    public void setMethods(List<ClassContent> methods) {
        if(this.methods == methods)
            return;
        this.methods = methods;
        updateChanged();
    }
}