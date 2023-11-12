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
            File file = new File(getClass().getResource(".").getFile() + "/log.txt");

            try {

                if (file.createNewFile()) {
                    System.out.println("File is created!");
                } else {
                    System.out.println("File already exists.");
                }
                fileWriter = new FileWriter(file);
                fileWriter.write(String.valueOf(message));
                printWriter = new PrintWriter(fileWriter, true);
                printWriter.println(message);
                printWriter.flush();
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
