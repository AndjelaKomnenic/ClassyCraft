package raf.dsw.factoryMethod;

import raf.dsw.composite.ClassyNode;
import raf.dsw.composite.ClassyNodeComposite;
import raf.dsw.tree.model.ClassyTreeItem;

public abstract class NodeFactory {
    public abstract ClassyTreeItem createNode(ClassyTreeItem parent);
}
