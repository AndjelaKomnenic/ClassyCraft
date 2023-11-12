package raf.dsw.classyrepository.composite;

public interface ClassyRepository {
    ProjectExplorer getProjectExplorer();
    void addChild(ClassyNodeComposite parent, ClassyNode chiild);
}