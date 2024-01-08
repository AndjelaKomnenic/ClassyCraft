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

    private boolean template;

    public Diagram(){

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
            updateChanged();
            this.notifySubscriber("NEW");
        }
        this.notifySubscriber("REPAINT");
    }

    public void setTemplate(boolean template) {
        if(this.template == template)
            return;

        this.template = template;
        updateChanged();
    }

    @Override
    public void setName(String name){
        super.setName(name);
    }
}