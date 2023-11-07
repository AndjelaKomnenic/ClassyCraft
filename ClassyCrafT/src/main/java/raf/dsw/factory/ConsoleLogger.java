package raf.dsw.factory;

import raf.dsw.message.Message;

public class ConsoleLogger implements Logger{
    public ConsoleLogger(){

    }
    @Override
    public void update(Object object){
        if(object instanceof Message) {
            Message message = (Message) object;
            System.out.println(message);
        }
    }
}
