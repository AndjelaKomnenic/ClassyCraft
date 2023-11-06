package raf.dsw.composite;

public class ProjectExplorer extends ClassyNodeComposite{
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