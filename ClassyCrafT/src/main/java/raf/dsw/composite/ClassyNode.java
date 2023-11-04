package raf.dsw.composite;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.observer.CSubject;

@Getter
@Setter
public abstract class ClassyNode extends CSubject {
    private String name;
    private ClassyNode parent;

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