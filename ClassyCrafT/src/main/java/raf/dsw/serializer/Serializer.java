package raf.dsw.serializer;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classyrepository.implementation.Diagram;
import raf.dsw.classyrepository.implementation.Project;

import java.io.File;


public interface Serializer {
    Project loadProject (File file);
    void saveProject (Project node);
    void saveTemplate(Diagram diagram, String templateName);
    void loadTemplate(File file);
}
