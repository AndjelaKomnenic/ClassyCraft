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
        this.author = (author != null && !author.isEmpty()) ? author : "Default Author";
    }


    @Override
    public void addChild(ClassyNode child) {
        if(child != null && child instanceof Package) {
            Package newPackage = (Package) child;
            if(!children.contains(newPackage))
            {
                children.add(child);
                updateChanged();
            }
        }
        this.notifySubscriber("NEW");
        this.setCounter();
    }

    public void setAuthor(String author){
        if(this.author == null && author == null)
            return;
        if(this.author != null && this.author.equals(author))
            return;
        this.author = author;
        this.notifySubscriber("ADD_AUTHOR");
        updateChanged();
    }

    public void setFilePath(String filePath) {
        if(this.filePath == null && filePath == null)
            return;
        if(this.filePath != null && this.filePath.equals(filePath))
            return;
        this.filePath = filePath;
        updateChanged();
    }


    @Override
    public void setName(String name) {
        super.setName(name);
    }

}