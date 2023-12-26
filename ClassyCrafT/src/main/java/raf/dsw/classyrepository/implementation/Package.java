package raf.dsw.classyrepository.implementation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.composite.ClassyNodeComposite;

import java.util.List;

@Getter
@Setter
public class Package extends ClassyNodeComposite {
    protected String filePath;

    /*public Package(String name, ClassyNode parent){
        super(name, parent);
    }*/
    @JsonCreator
    public Package(@JsonProperty("name")String name
            , @JsonProperty("parent")ClassyNode parent
            , @JsonProperty("filePath") String filePath
            , @JsonProperty("children") List<ClassyNode> children) {

        super(name, parent);
        for(ClassyNode child: children)
            addChild(child);
        this.filePath = filePath;
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