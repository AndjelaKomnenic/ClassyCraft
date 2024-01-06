package raf.dsw.classyrepository.implementation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.composite.ClassyNodeComposite;
import raf.dsw.observer.ISubscriber;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Project extends ClassyNodeComposite {
    protected String filePath;
    private String author;

    public Project(){

    }

    public Project(String name, ClassyNode parent, String author) {
        super(name, parent);
        this.author = author;
    }

    /*@JsonCreator
    public Project(@JsonProperty("name")String name
            , @JsonProperty("author")String author
            , @JsonProperty("children") List<ClassyNode> children
            , @JsonProperty("counter") int counter
            , @JsonProperty("filePath") String filePath) {
        super(name, null);
        this.author = author;
        this.setName(name);
        this.filePath = filePath;
        for(ClassyNode child: children)
            this.addChild(child);
        //this.setCounterVal(counter);
    }*/


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