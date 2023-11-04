package raf.dsw.composite;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassyRepositoryImplementation implements ClassyRepository{
    private ProjectExplorer projectExplorer;

    public ClassyRepositoryImplementation() {
        projectExplorer = new ProjectExplorer("My Project Explorer");
    }

    @Override
    public void addChild(ClassyNodeComposite parent, ClassyNode chiild) {

    }
}
