package raf.dsw.composite.factoryMethod;

import raf.dsw.composite.ClassyNode;
import raf.dsw.composite.ClassyNodeComposite;
import raf.dsw.composite.Package;
import raf.dsw.tree.model.ClassyTreeItem;

public class PackageFactory extends NodeFactory {
    @Override
    public ClassyTreeItem createNode(ClassyTreeItem parent) {
        int nmb = ((ClassyNodeComposite)parent.getClassyNode()).getCounter();
        String tryName = "";
        boolean ok = true;
        boolean flag = false;
        while(!flag) {
            ok = true;
            tryName = "Paket"+((ClassyNodeComposite)parent.getClassyNode()).getCounter();
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
        ClassyNode child = new Package(tryName, parent.getClassyNode());
        ClassyTreeItem childTreeItem = new ClassyTreeItem(child);
        return childTreeItem;
    }
}
