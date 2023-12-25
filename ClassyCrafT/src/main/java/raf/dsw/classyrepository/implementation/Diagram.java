package raf.dsw.classyrepository.implementation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.composite.ClassyNodeComposite;

@Getter
@Setter

public class Diagram extends ClassyNodeComposite {

    private boolean template;
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
