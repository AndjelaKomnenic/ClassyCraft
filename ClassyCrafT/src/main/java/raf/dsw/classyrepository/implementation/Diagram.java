package raf.dsw.classyrepository.implementation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.jshell.Diag;
import lombok.Getter;
import lombok.Setter;
import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.composite.ClassyNodeComposite;

import java.util.List;

@Getter
@Setter

public class Diagram extends ClassyNodeComposite {
    public Diagram(String name, ClassyNode parent){
        super(name, parent);
    }
    @JsonCreator
    public Diagram(@JsonProperty("name")String name
            , @JsonProperty("parent")ClassyNode parent
            , @JsonProperty("children") List<ClassyNode> children) {
        super(name, parent);
        for(ClassyNode child: children)
            addChild(child);
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
}
