package raf.dsw.factory;

import raf.dsw.message.Message;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileLogger implements Logger{
    //URL path = getClass().getResource("log.txt");

    public FileLogger(){

    }
    @Override
    public void update(Object obj){
        if(obj instanceof Message) {
            Message message = (Message) obj;
            FileWriter fileWriter = null;
            PrintWriter printWriter = null;
            File file = new File("log.txt");

            try {
                fileWriter = new FileWriter(file, true);
                fileWriter.append(String.valueOf(message));
                printWriter = new PrintWriter(fileWriter, true);
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
