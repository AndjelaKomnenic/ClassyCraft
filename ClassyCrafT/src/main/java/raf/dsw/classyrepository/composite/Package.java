package raf.dsw.classyrepository.composite;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Package extends ClassyNodeComposite{
    /*TODO*/
    public Package(String name, ClassyNode parent) {
        super(name, parent);
    }

    @Override
    public void addChild(ClassyNode child) {
        //jos uvek nije implementirano kkao treba nego je meni ovo potrebno za JTree
        this.setCounter();
        children.add(child);
        this.notifySubscriber("NEW");
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }
}