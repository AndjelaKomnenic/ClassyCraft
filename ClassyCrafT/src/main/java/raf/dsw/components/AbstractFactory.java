package raf.dsw.components;

import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.implementation.Diagram;

public class AbstractFactory extends AbstractAbstractFactory{
    public InterClass newInterClass(String type, ClassyNode parent){
        //posle vrati da ovde ne prosledjujemo koordinate vec da se one pri kliku na diagView setuju
        if(type.equalsIgnoreCase("klasa"))
            return new Klasa("", parent, 0, 0);
        else if(type.equalsIgnoreCase("interfejs"))
            return new Interfejs("", parent, 0, 0);
        return new Enum("", parent, 0, 0);
    }
    public Connection newConnection(String type, Diagram parent){
        if(type.equalsIgnoreCase("zavisnost"))
            return new Zavisnost("", parent, null);
        else if(type.equalsIgnoreCase("kompozicija"))
            return new Kompozicija("", parent, null);
        else if(type.equalsIgnoreCase("generalizacija"))
            return new Generalizacija("", parent, null);
        return new Agregacija("", parent, null);
    }
}
