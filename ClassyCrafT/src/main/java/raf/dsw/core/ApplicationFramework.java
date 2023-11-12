package raf.dsw.core;

import raf.dsw.classyrepository.composite.ClassyRepository;
import raf.dsw.classyrepository.composite.ClassyRepositoryImplementation;
import raf.dsw.factory.Logger;
import raf.dsw.factory.LoggerFactory;
import raf.dsw.message.MessageGenerator;
import raf.dsw.message.MessageGeneratorImplementation;
import raf.dsw.view.MainFrame;

public class ApplicationFramework {

    protected Gui gui;
    protected ClassyRepository classyRepository;
    protected MessageGeneratorImplementation messageGenerator;

    public void run(){
        this.gui.start();
    }

    public void initialise(Gui gui, ClassyRepository classyRepository)
    {
        this.gui = gui;
        this.classyRepository = classyRepository;
        this.messageGenerator = new MessageGeneratorImplementation();
        LoggerFactory loggerFactory = new LoggerFactory();
        Logger log1 = loggerFactory.createLogger("consolelogger");
        Logger log2 = loggerFactory.createLogger("fileLogger");
        messageGenerator.addSubscriber(log1);
        messageGenerator.addSubscriber(log2);
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
    public MessageGenerator getMessageGenerator(){return this.messageGenerator;}
}
