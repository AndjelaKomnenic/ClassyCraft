package raf.dsw.codegenerator;

import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.composite.ClassyNodeComposite;
import raf.dsw.classyrepository.implementation.Diagram;
import raf.dsw.classyrepository.implementation.Package;
import raf.dsw.classyrepository.implementation.Project;
import raf.dsw.classyrepository.implementation.ProjectExplorer;
import raf.dsw.components.*;
import raf.dsw.tree.ClassyTreeImplementation;
import raf.dsw.tree.model.ClassyTreeItem;
import raf.dsw.tree.view.ClassyTreeView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Generator {
    private int agregacijaCounter = 0;
    private int kompozicijaCounter = 0;
    private int generalizacijaCounter = 0;
    public Generator() {}

    public void createStructure(ClassyTreeItem root, File baseDirectory) {
        if (root != null && root.getClassyNode() instanceof ProjectExplorer) {
            System.out.println("usao");
            ProjectExplorer explorer = (ProjectExplorer) root.getClassyNode();
            File explorerDirectory = new File(baseDirectory, "ProjectExplorer");

            if (!explorerDirectory.exists()) {
                if (explorerDirectory.mkdirs()) {
                    System.out.println("Directory created: " + explorerDirectory.getAbsolutePath());
                } else {
                    System.out.println("Failed to create directory for explorer: " + "ProjectExplorer");
                }
            }

            createStructureForChildren(root, explorerDirectory);
        }
    }

    private void createStructureForChildren(ClassyTreeItem currentItem, File parentDirectory) {
        if (currentItem != null) {
            // Check if the current item is a Project
            if (currentItem.getClassyNode() instanceof Project) {
                Project project = (Project) currentItem.getClassyNode();
                String projectName = project.getName();
                File projectDirectory = new File(parentDirectory, projectName);

                if (!projectDirectory.exists()) {
                    if (projectDirectory.mkdirs()) {
                        System.out.println("Directory created: " + projectDirectory.getAbsolutePath());
                    } else {
                        System.out.println("Failed to create directory for project: " + projectName);
                    }
                }

                // Recursively create structure for children
                for (ClassyTreeItem child : currentItem.getChildren()) {
                    createStructureForChildren(child, projectDirectory);
                }
            }

            // Check if the current item is a Package
            if (currentItem.getClassyNode() instanceof Package) {
                Package pkg = (Package) currentItem.getClassyNode();
                String packageName = pkg.getName();

                // Check if the parent directory is a project directory
                if (parentDirectory.getParentFile() != null && parentDirectory.getParentFile().getName().equals("ProjectExplorer")) {
                    File packageDirectory = new File(parentDirectory, packageName);

                    if (!packageDirectory.exists()) {
                        if (packageDirectory.mkdirs()) {
                            System.out.println("Directory created: " + packageDirectory.getAbsolutePath());
                        } else {
                            System.out.println("Failed to create directory for package: " + packageName);
                        }
                    }

                    // Recursively create structure for children inside the package directory
                    for (ClassyTreeItem child : currentItem.getChildren()) {
                        createStructureForChildren(child, packageDirectory);
                    }
                }
            }

            // Check if the current item is a Diagram
            if (currentItem.getClassyNode() instanceof Diagram) {
                Diagram diagram = (Diagram) currentItem.getClassyNode();
                String diagramName = diagram.getName();

                File diagramFile = new File(parentDirectory, diagramName + ".txt");

                try {
                    if (diagramFile.createNewFile()) {
                        System.out.println("File created: " + diagramFile.getAbsolutePath());
                        writeDiagramElementsToFile(diagram, diagramFile); // Write diagram elements to the file
                    } else {
                        System.out.println("File already exists.");
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred while creating the file: " + e.getMessage());
                }
            }

            // Traverse through the children recursively for non-package items
            if (!(currentItem.getClassyNode() instanceof Package)) {
                for (ClassyTreeItem child : currentItem.getChildren()) {
                    createStructureForChildren(child, parentDirectory);
                }
            }
        }
    }

    /*private void writeDiagramElements(Diagram diagram, File diagramFile) throws IOException {
        try (FileWriter writer = new FileWriter(diagramFile)) {

            for (ClassyNode element : diagram.getChildren()) {
                if (element instanceof InterClass) {
                    if (element instanceof Klasa){
                        writer.write("Klasa " + element.getName());
                        writer.write(System.lineSeparator());

                        if (((Klasa) element).isApstraktna()){
                            writer.write("(Apstrakta klasa)");
                            writer.write(System.lineSeparator());
                        }

                        for (ClassContent ccc : ((Klasa) element).getCl()){
                                if (ccc instanceof Atribut) {
                                    writer.write(ccc.getVidljivost() + " " + ccc.getTip() +" " + ccc.getNaziv());
                                    writer.write(System.lineSeparator());
                                }

                                if (ccc instanceof Metod) {
                                    writer.write(ccc.getVidljivost() + " " + ccc.getTip() +" " + ccc.getNaziv());
                                    writer.write(System.lineSeparator());
                                }
                        }
                    }
                }
            }
        }
    }*/
    private void writeAgregacijaDetails(Klasa klasa, FileWriter writer) throws IOException {
        for (Connection connection : klasa.getListaVeza()) {
            if (connection instanceof Agregacija) {
                Agregacija agregacija = (Agregacija) connection;
                writer.write(  "\t" + agregacija.getVidljivost() + " " + agregacija.getToNaziv() + " " + agregacija.getName() + "; //Agregacija");
                writer.write(System.lineSeparator());
            }
        }
    }

    private void writeKompozicijaDetails(Klasa klasa, FileWriter writer) throws IOException {
        for (Connection connection : klasa.getListaVeza()) {
            if (connection instanceof Kompozicija) {
                Kompozicija kompozicija = (Kompozicija) connection;
                writer.write(  "\t" + kompozicija.getVidljivost() + " " + kompozicija.getToNaziv() + " " + kompozicija.getName() + "; //Kompozicija");
                writer.write(System.lineSeparator());
            }
        }
    }

    private void writeGeneralizacijaDetails(Klasa klasa, FileWriter writer) throws IOException {
        for (Connection connection : klasa.getListaVeza()) {
            if (connection instanceof Generalizacija) {
                Generalizacija generalizacija = (Generalizacija) connection;
                writer.write(" extends " + generalizacija.getToNaziv() + " {");
                writer.write(System.lineSeparator());
            }
        }
    }
    public void writeDiagramElementsToFile(Diagram diagram, File diagramFile) throws IOException {
        try (FileWriter writer = new FileWriter(diagramFile)) {
            for (ClassyNode element : diagram.getChildren()) {
                if (element instanceof Klasa) {
                    Klasa klasa = (Klasa) element;
                    writeKlasaElement(klasa, writer);

                }
            }
        }
    }


    /*private void writeAgregacijaElement(Agregacija agregacija, FileWriter writer) throws IOException {
        // Logic to write details of Agregacija to the file
        // Example:

        // fali jos kardinalnost

        //toNAziv je u stvr tip
        writer.write("// Agregacija: " + agregacija.getToNaziv() + " " + agregacija.getVidljivost() + " " + agregacija.getName());
        writer.write(System.lineSeparator());
    }*/
    private void writeKlasaElement(Klasa klasa, FileWriter writer) throws IOException {
        writer.write("public");

        if (klasa.isApstraktna()) {
            writer.write(" abstract");
        }

        writer.write(" class " + klasa.getName());

        if (hasGeneralizacija(klasa) && generalizacijaCounter % 2 == 0) {
            writeGeneralizacijaDetails(klasa, writer);
        } else {
            writer.write(" {"); // Add the opening brace for class definition if there's no inheritance
            writer.write(System.lineSeparator());
        }


        writeAttributes(klasa.getCl(), writer);

        /*if (!agregacijaWritten) {
            writeAgregacijaDetails(klasa, writer);
            agregacijaWritten = false; // Set the flag to true after writing details in one class
        }*/
        if (hasAgregacija(klasa) && agregacijaCounter % 2 == 0) {
            writeAgregacijaDetails(klasa, writer);
        }
        if (hasKompozicija(klasa) && kompozicijaCounter % 2 == 0) {
            writeKompozicijaDetails(klasa, writer); // ovde
        }
        /*if (hasGeneralizacija(klasa) && generalizacijaCounter % 2 == 0) {
            writeGeneralizacijaDetails(klasa, writer); // ovde
        }*/

        writer.write("\n");
        writeMethods(klasa.getCl(), writer);

        if (hasAgregacija(klasa)) {
            agregacijaCounter++;
        }

        if (hasKompozicija(klasa)) {
            kompozicijaCounter++;
        }

        if (hasGeneralizacija(klasa)) {
            generalizacijaCounter++;
        }

        writer.write("}" + System.lineSeparator());
    }

    private boolean hasAgregacija(Klasa klasa) {
        return klasa.getListaVeza().stream().anyMatch(connection -> connection instanceof Agregacija);
    }

    private boolean hasKompozicija(Klasa klasa) {
        return klasa.getListaVeza().stream().anyMatch(connection -> connection instanceof Kompozicija);
    }

    private boolean hasGeneralizacija(Klasa klasa) {
        return klasa.getListaVeza().stream().anyMatch(connection -> connection instanceof Generalizacija);
    }

    private void writeAttributes(List<ClassContent> classContents, FileWriter writer) throws IOException {
        for (ClassContent ccc : classContents) {
            if (ccc instanceof Atribut) {
                String attributeInfo = "\t" + ccc.getVidljivost() + " " + ccc.getTip() + " " + ccc.getNaziv() + ";";
                writer.write(attributeInfo);
                writer.write(System.lineSeparator());
            }
        }
    }

    private void writeMethods(List<ClassContent> classContents, FileWriter writer) throws IOException {
        for (ClassContent ccc : classContents) {
            if (ccc instanceof Metod) {
                String methodInfo = "\t" + ccc.getVidljivost() + " " + ccc.getTip() + " " + ccc.getNaziv() + "() {";
                writer.write(methodInfo);
                writer.write(System.lineSeparator());
                writer.write("\t\t// TODO: Method implementation");
                writer.write(System.lineSeparator());
                writer.write("\t}");
                writer.write(System.lineSeparator());
                writer.write(System.lineSeparator());
            }
        }
    }

}
