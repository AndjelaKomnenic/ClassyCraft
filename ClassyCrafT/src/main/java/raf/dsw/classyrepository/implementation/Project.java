package raf.dsw.classyrepository.implementation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.composite.ClassyNodeComposite;

@Getter
@Setter
public class Project extends ClassyNodeComposite {
    protected String filePath;
    private String author;
    @JsonCreator
    public Project(@JsonProperty("name")String name, ClassyNode parent, String author) {
        super(name, parent);
        this.author = author;
    }

    @Override
    public void addChild(ClassyNode child) {
        if(child != null && child instanceof Package) {
            Package newPackage = (Package) child;
            if(!children.contains(newPackage))
                children.add(child);
        }
        this.notifySubscriber("NEW");
        this.setCounter();
    }

    public void setAuthor(String author){
        this.author = author;
        this.notifySubscriber("ADD_AUTHOR");
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


    @Override
    public void setName(String name) {
        super.setName(name);
    }
}