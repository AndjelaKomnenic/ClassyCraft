package raf.dsw;

import raf.dsw.core.ApplicationFramework;

public class Main {
    public static void main(String[] args) {
        ApplicationFramework appCore = ApplicationFramework.getInstance();
        appCore.initialize();
    }
}