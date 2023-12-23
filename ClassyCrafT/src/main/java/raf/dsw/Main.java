package raf.dsw;

import raf.dsw.classyrepository.ClassyRepositoryImplementation;
import raf.dsw.core.ApplicationFramework;
import raf.dsw.classyrepository.composite.ClassyRepository;
import raf.dsw.core.Gui;
import raf.dsw.serializer.JacksonSerializer;
import raf.dsw.serializer.Serializer;
import raf.dsw.view.SwingGui;

public class Main {
    public static void main(String[] args) {
        ApplicationFramework appCore = ApplicationFramework.getInstance();
        Gui gui = new SwingGui();
        ClassyRepository classyRepository = new ClassyRepositoryImplementation();
        Serializer serializer = new JacksonSerializer();
        appCore.initialise(gui, classyRepository, serializer);
        appCore.run();
    }
}