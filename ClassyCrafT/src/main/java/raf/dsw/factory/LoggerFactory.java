package raf.dsw.factory;

public class LoggerFactory {
    public LoggerFactory(){

    }
    public Logger createLogger(String typeOfLogger){
        if(typeOfLogger.toUpperCase().equals("CONSOLELOGGER"))
            return new ConsoleLogger();
        else if(typeOfLogger.toUpperCase().equals("FILELOGGER"))
            return new FileLogger();
        return null;
    }
}
