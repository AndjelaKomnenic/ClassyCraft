package raf.dsw.factoryMethod;

import raf.dsw.composite.ClassyNode;
import raf.dsw.composite.ClassyNodeComposite;
import raf.dsw.composite.Diagram;
import raf.dsw.composite.Project;
import raf.dsw.tree.model.ClassyTreeItem;

public class ProjectFactory extends NodeFactory{
    @Override
    public ClassyTreeItem createNode(ClassyTreeItem parent) {
        String author = "autor";
        int nmb = ((ClassyNodeComposite)parent.getClassyNode()).getCounter();
        String name = "Project" + nmb;
        ClassyNode child = new Project(name, parent.getClassyNode(), author);
        ClassyTreeItem childTreeItem = new ClassyTreeItem(child);
        return childTreeItem;
    }
}
