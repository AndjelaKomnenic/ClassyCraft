package raf.dsw.classyrepository.implementation;

//import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator;
import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.composite.ClassyNodeComposite;

public class ProjectExplorer extends ClassyNodeComposite {
    @JsonCreator
    public ProjectExplorer(String name) {
        super(name, null);
    }

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