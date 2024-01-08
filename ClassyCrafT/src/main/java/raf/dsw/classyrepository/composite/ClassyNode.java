package raf.dsw.classyrepository.composite;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;
import raf.dsw.observer.CPublisher;

@Getter
@Setter

// @JsonIgnoreProperties({ "parent" })
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public abstract class ClassyNode extends CPublisher {

    private String name;
    @JsonIgnore
    private transient ClassyNode parent;

    @JsonIgnore
    private transient boolean changed = false;


    public ClassyNode(){}
    public ClassyNode(String name, ClassyNode parent) {
        this.name = name;
        this.parent = parent;
    }

    public void resetChanged()
    {
        changed = false;
    }
    public void updateChanged()
    {
        changed = true;
        if(parent != null)
        {
            parent.updateChanged();
        }
    }

    public void setName(String name){
        if(this.name == null && name == null)
            return;
        if(this.name != null && this.name.equals(name))
            return;
        this.name = name;
        updateChanged();
        this.notifySubscriber("RENAME");
    }

    public void setParent(ClassyNode parent) {
        if(this.parent == parent)
            return;
        this.parent = parent;
        updateChanged();
    }

}