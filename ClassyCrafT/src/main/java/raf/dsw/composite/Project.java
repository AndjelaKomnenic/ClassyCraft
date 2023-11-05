package raf.dsw.composite;

public class Project extends ClassyNodeComposite{
    protected String filePath;
    private String author;

    public Project(String name, ClassyNode parent, String author) {
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
        this.notifySubscriber("ADD AUTHOR");
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


    @Override
    public void setName(String name) {
        super.setName(name);
    }
}