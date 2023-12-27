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

    public ClassyNode(){}
    public ClassyNode(String name, ClassyNode parent) {
        this.name = name;
        this.parent = parent;
    }

    public void setName(String name){
        this.name = name;
        this.notifySubscriber("RENAME");
    }

    public void setParent(ClassyNode parent) {
        this.parent = parent;
    }

}