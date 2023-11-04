package raf.dsw.factory;

public class ConsoleLogger implements Logger{
    public ConsoleLogger(){

    }
    @Override
    public void logMessage(){
        System.out.println("greska");
    }
}
