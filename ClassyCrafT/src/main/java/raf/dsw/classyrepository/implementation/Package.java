package raf.dsw.classyrepository.implementation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.composite.ClassyNodeComposite;

@Getter
@Setter
public class Package extends ClassyNodeComposite {
    protected String filePath;
    @JsonCreator
    public Package(@JsonProperty("name")String name, ClassyNode parent) {
        super(name, parent);
    }

    @Override
    public void addChild(ClassyNode child) {
        if(!children.contains(child))
        {
            this.setCounter();
            children.add(child);
            this.notifySubscriber("NEW");
        }
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }


}