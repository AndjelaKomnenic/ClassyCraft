package raf.dsw.classyrepository.implementation;

//import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.composite.ClassyNodeComposite;

import java.util.List;

public class ProjectExplorer extends ClassyNodeComposite {

    public ProjectExplorer(String name){
        super(name, null);
    }
    @JsonCreator
    public ProjectExplorer(@JsonProperty("name")String name
            , @JsonProperty("children") List<ClassyNode> children
            , @JsonProperty("counter") int counter){
        super(name, null);
        for(ClassyNode child: children)
            addChild(child);
    }
    /*@JsonCreator
    public ProjectExplorer(@JsonProperty("name")String name) {

        super(name, null);
    }*/

    @Override
    public void addChild(ClassyNode child) {
        if (child != null && child instanceof Project){
            Project newProject = (Project) child;
            if (!children.contains(newProject))
                children.add(child);
        }
        this.notifySubscriber("NEW");
        this.setCounter();
    }
}