package raf.dsw.core;

import raf.dsw.composite.ClassyRepository;
import raf.dsw.composite.ClassyRepositoryImplementation;
import raf.dsw.message.MessageGenerator;
import raf.dsw.message.MessageGeneratorImplementation;
import raf.dsw.view.MainFrame;

public class ApplicationFramework {

    protected Gui gui;
    protected ClassyRepository classyRepository;

    public void run(){
        this.gui.start();
    }

    public void initialise(Gui gui, ClassyRepository classyRepository)
    {
        this.gui = gui;
        this.classyRepository = classyRepository;
    }

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
    public ClassyRepository getClassyRepository(){
        return this.classyRepository;
    }
}
