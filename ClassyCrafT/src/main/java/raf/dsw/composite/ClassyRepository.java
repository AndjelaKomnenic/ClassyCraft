package raf.dsw.composite;

public interface ClassyRepository {
    ProjectExplorer getProjectExplorer();
    void addChild(ClassyNodeComposite parent, ClassyNode chiild);
}
