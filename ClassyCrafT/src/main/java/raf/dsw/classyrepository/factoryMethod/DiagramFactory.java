package raf.dsw.classyrepository.factoryMethod;

import raf.dsw.classyrepository.composite.ClassyNodeComposite;
import raf.dsw.classyrepository.implementation.Diagram;
import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.tree.model.ClassyTreeItem;

public class DiagramFactory extends NodeFactory{

    @Override
    public ClassyTreeItem createNode(ClassyTreeItem parent) {
        int nmb = ((ClassyNodeComposite)parent.getClassyNode()).getCounter();
        String tryName = "";
        boolean ok = true;
        boolean flag = false;
        while(!flag) {
            ok = true;
            tryName = "Dijagram"+((ClassyNodeComposite)parent.getClassyNode()).getCounter();
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
        ClassyNode child = new Diagram(tryName, parent.getClassyNode());
        ClassyTreeItem childTreeItem = new ClassyTreeItem(child);
        return childTreeItem;
    }
}
