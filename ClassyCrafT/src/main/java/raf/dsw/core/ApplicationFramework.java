package raf.dsw.core;

import raf.dsw.composite.ClassyRepository;
import raf.dsw.composite.ClassyRepositoryImplementation;
import raf.dsw.message.MessageGenerator;
import raf.dsw.message.MessageGeneratorImplementation;
import raf.dsw.view.MainFrame;

public class ApplicationFramework {
    private static ApplicationFramework instance;

    private ApplicationFramework() {

    }

    public void initialize(){
        ClassyRepository classyRepo =  new ClassyRepositoryImplementation();
        MainFrame.getInstance().setVisible(true);
    }

    public static ApplicationFramework getInstance(){
        if (instance == null)
            instance = new ApplicationFramework();
        return instance;
    }
}
