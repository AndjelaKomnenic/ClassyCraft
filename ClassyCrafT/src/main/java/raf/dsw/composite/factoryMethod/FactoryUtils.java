package raf.dsw.composite.factoryMethod;

import raf.dsw.composite.Package;
import raf.dsw.composite.Project;
import raf.dsw.composite.ProjectExplorer;
import raf.dsw.tree.model.ClassyTreeItem;

public class FactoryUtils {
    public FactoryUtils(){

    }
    public NodeFactory nodeFactory(ClassyTreeItem parentNode){
        NodeFactory factory = null;
        if(parentNode.getClassyNode() instanceof ProjectExplorer)
            factory = new ProjectFactory();
        if(parentNode.getClassyNode() instanceof Project)
            factory = new PackageFactory();
        if(parentNode.getClassyNode() instanceof Package)
            factory = new DiagramFactory();
        return factory;
    }
}
