package raf.dsw.classyrepository.composite;

import raf.dsw.classyrepository.implementation.ProjectExplorer;

public interface ClassyRepository {
    ProjectExplorer getProjectExplorer();
    void addChild(ClassyNodeComposite parent, ClassyNode chiild);
}