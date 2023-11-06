package raf.dsw.factoryMethod;

import raf.dsw.composite.ClassyNode;
import raf.dsw.composite.ClassyNodeComposite;
import raf.dsw.composite.Diagram;
import raf.dsw.composite.Project;
import raf.dsw.tree.model.ClassyTreeItem;

public class ProjectFactory extends NodeFactory{
    @Override
    public ClassyTreeItem createNode(ClassyTreeItem parent) {
        String author = "";
        int nmb = ((ClassyNodeComposite)parent.getClassyNode()).getCounter();
        String tryName = "";
        boolean ok = true;
        boolean flag = false;
        while(!flag) {
            ok = true;
            tryName = "Projekat"+((ClassyNodeComposite)parent.getClassyNode()).getCounter();
            for (ClassyNode kid : ((ClassyNodeComposite) parent.getClassyNode()).getChildren()) {
                if (kid.getName().equalsIgnoreCase(tryName)) {
                    ok = false;
                }
            }
            if(ok)
                flag = true;
            else
                ((ClassyNodeComposite)parent.getClassyNode()).setCounter();
        }
        ClassyNode child = new Project(tryName, parent.getClassyNode(), author);
        ClassyTreeItem childTreeItem = new ClassyTreeItem(child);
        return childTreeItem;
    }
}
