package raf.dsw.classyrepository.implementation;

import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.composite.ClassyNodeComposite;

import java.util.List;

public class ProjectExplorer extends ClassyNodeComposite {

    public ProjectExplorer(String name){
        super(name, null);
        for (ClassyNode child : children)
            addChild(child);
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