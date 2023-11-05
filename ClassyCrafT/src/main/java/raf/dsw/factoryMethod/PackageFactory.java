package raf.dsw.factoryMethod;

import raf.dsw.composite.ClassyNode;
import raf.dsw.composite.ClassyNodeComposite;
import raf.dsw.composite.Diagram;
import raf.dsw.composite.Package;
import raf.dsw.tree.model.ClassyTreeItem;

public class PackageFactory extends NodeFactory {
    @Override
    public ClassyTreeItem createNode(ClassyTreeItem parent) {
        int nmb = ((ClassyNodeComposite)parent.getClassyNode()).getCounter();
        String name = "Package" + nmb;
        ClassyNode child = new Package(name, parent.getClassyNode());
        ClassyTreeItem childTreeItem = new ClassyTreeItem(child);
        return childTreeItem;
    }
}
