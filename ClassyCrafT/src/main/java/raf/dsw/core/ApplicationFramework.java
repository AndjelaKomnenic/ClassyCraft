package raf.dsw.core;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classyrepository.composite.ClassyRepository;
import raf.dsw.classyrepository.ClassyRepositoryImplementation;
import raf.dsw.factory.Logger;
import raf.dsw.factory.LoggerFactory;
import raf.dsw.message.MessageGenerator;
import raf.dsw.message.MessageGeneratorImplementation;
import raf.dsw.serializer.JacksonSerializer;
import raf.dsw.serializer.Serializer;
import raf.dsw.view.MainFrame;

@Getter
@Setter
public class ApplicationFramework {

    protected Gui gui;
    protected ClassyRepository classyRepository;
    protected MessageGeneratorImplementation messageGenerator;
    protected Serializer serializer;

    public void run(){
        this.gui.start();
    }

    public void initialise(Gui gui, ClassyRepository classyRepository, Serializer serializer)
    {
        this.gui = gui;
        this.classyRepository = classyRepository;
        this.messageGenerator = new MessageGeneratorImplementation();
        LoggerFactory loggerFactory = new LoggerFactory();
        Logger log1 = loggerFactory.createLogger("consolelogger");
        Logger log2 = loggerFactory.createLogger("fileLogger");
        messageGenerator.addSubscriber(log1);
        messageGenerator.addSubscriber(log2);
        this.serializer = serializer;
    }

    private static ApplicationFramework instance;

    private ApplicationFramework() {

    }

    public void initialize(){
        //ClassyRepository classyRepo =  new ClassyRepositoryImplementation();
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
