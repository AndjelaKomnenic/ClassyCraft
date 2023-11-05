package raf.dsw.factoryMethod;

import raf.dsw.composite.ClassyNode;
import raf.dsw.composite.ClassyNodeComposite;
import raf.dsw.composite.Diagram;
import raf.dsw.tree.model.ClassyTreeItem;

public class DiagramFactory extends NodeFactory{

    @Override
    public ClassyTreeItem createNode(ClassyTreeItem parent) {
        int nmb = ((ClassyNodeComposite)parent.getClassyNode()).getCounter();
        String name = "Diagram" + nmb;
        ClassyNode child = new Diagram(name, parent.getClassyNode());
        ClassyTreeItem childTreeItem = new ClassyTreeItem(child);
        return childTreeItem;
    }
}
