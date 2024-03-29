package raf.dsw.classyrepository.factoryMethod;

import raf.dsw.classyrepository.implementation.Package;
import raf.dsw.classyrepository.implementation.Project;
import raf.dsw.classyrepository.implementation.ProjectExplorer;
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
