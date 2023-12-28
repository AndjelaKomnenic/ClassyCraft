package raf.dsw.classyrepository.implementation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.jshell.Diag;
import lombok.Getter;
import lombok.Setter;
import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.composite.ClassyNodeComposite;
import raf.dsw.observer.ISubscriber;

import java.util.ArrayList;
import java.util.List;

import java.util.List;

@Getter
@Setter

public class Diagram extends ClassyNodeComposite {
    @JsonCreator
    public Diagram(@JsonProperty("name")String name
            , @JsonProperty("parent")ClassyNode parent
            , @JsonProperty("children") List<ClassyNode> children) {
        super(name, parent);
        for (ClassyNode child : children)
            addChild(child);
        this.template = false;
    }

    private boolean template;
    @JsonIgnore
    private transient List<ISubscriber> subs;
    public Diagram(){
        subs = new ArrayList<>();
    }
    public Diagram(String name, ClassyNode parent) {
        super(name, parent);
        this.template = false;
    }

    @Override
    public void addChild(ClassyNode child) {
        if(!children.contains(child))
        {
            children.add(child);
            this.notifySubscriber("NEW");
        }
        this.notifySubscriber("REPAINT");
    }

    @Override
    public void setName(String name){
        super.setName(name);
    }
}
