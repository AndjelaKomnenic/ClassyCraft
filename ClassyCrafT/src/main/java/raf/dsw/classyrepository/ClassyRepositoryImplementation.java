package raf.dsw.classyrepository;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.composite.ClassyNodeComposite;
import raf.dsw.classyrepository.composite.ClassyRepository;
import raf.dsw.classyrepository.implementation.ProjectExplorer;

@Getter
@Setter
public class ClassyRepositoryImplementation implements ClassyRepository {
    private ProjectExplorer projectExplorer;

    public ClassyRepositoryImplementation() {
        projectExplorer = new ProjectExplorer("My Project Explorer");
    }

    @Override
    public void addChild(ClassyNodeComposite parent, ClassyNode chiild) {

    }
    @Override
    public ProjectExplorer getProjectExplorer(){
        return this.projectExplorer;
    }
}