package raf.dsw.factory;

import raf.dsw.message.Message;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileLogger implements Logger{
    public FileLogger(){

    }
    @Override
    public void update(Object obj){
        if(obj instanceof Message) {
            Message message = (Message) obj;
            FileWriter fileWriter = null;
            PrintWriter printWriter = null;
            try {
                fileWriter = new FileWriter("log.txt");
                printWriter = new PrintWriter(fileWriter);
                printWriter.println(message);
            } catch (IOException e) {
                System.out.println("Fajl log.txt nije pronadjen");
            } finally {
                try {
                    printWriter.close();
                    fileWriter.close();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
