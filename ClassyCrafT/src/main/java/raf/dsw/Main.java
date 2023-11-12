package raf.dsw;

import raf.dsw.classyrepository.composite.ClassyRepositoryImplementation;
import raf.dsw.core.ApplicationFramework;
import raf.dsw.classyrepository.composite.ClassyRepository;
import raf.dsw.core.Gui;
import raf.dsw.view.SwingGui;

public class Main {
    public static void main(String[] args) {
        ApplicationFramework appCore = ApplicationFramework.getInstance();
        Gui gui = new SwingGui();
        ClassyRepository classyRepository = new ClassyRepositoryImplementation();
        appCore.initialise(gui, classyRepository);
        appCore.run();
        //appCore.initialize();
    }
}