package core;

import java.io.PrintWriter;

public class FileLogger implements Logger{
    public FileLogger(){

    }
    @Override
    public void logMessage(){
        try(PrintWriter pw = new PrintWriter("log.txt")){
            pw.println("poruka");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
