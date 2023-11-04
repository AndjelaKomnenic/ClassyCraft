package raf.dsw;

import raf.dsw.core.ApplicationFramework;
import raf.dsw.message.MessageGenerator;
import raf.dsw.message.MessageGeneratorImplementation;

public class Main {
    public static void main(String[] args) {
        ApplicationFramework appCore = ApplicationFramework.getInstance();
        appCore.initialize();
    }
}