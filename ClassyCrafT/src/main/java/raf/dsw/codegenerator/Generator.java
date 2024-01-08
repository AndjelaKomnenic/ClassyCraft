package raf.dsw.codegenerator;

import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.composite.ClassyNodeComposite;
import raf.dsw.classyrepository.implementation.Diagram;
import raf.dsw.classyrepository.implementation.Package;
import raf.dsw.classyrepository.implementation.Project;
import raf.dsw.classyrepository.implementation.ProjectExplorer;
import raf.dsw.components.*;
import raf.dsw.components.Enum;
import raf.dsw.tree.ClassyTreeImplementation;
import raf.dsw.tree.model.ClassyTreeItem;
import raf.dsw.tree.view.ClassyTreeView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.StringJoiner;

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

                for (ClassyTreeItem child : currentItem.getChildren()) {
                    createStructureForChildren(child, projectDirectory);
                }
            }

            /*if (currentItem.getClassyNode() instanceof Package) {
                Package pkg = (Package) currentItem.getClassyNode();
                String packageName = pkg.getName();
                String[] subPackages = packageName.split("\\.");

                File currentDirectory = parentDirectory;
                for (String subPackage : subPackages) {
                    File packageDirectory = new File(currentDirectory, subPackage);

                    if (!packageDirectory.exists()) {
                        if (packageDirectory.mkdirs()) {
                            System.out.println("Directory created: " + packageDirectory.getAbsolutePath());
                        } else {
                            System.out.println("Failed to create directory for package: " + subPackage);
                        }
                    }

                    currentDirectory = packageDirectory;
                }

                for (ClassyTreeItem child : currentItem.getChildren()) {
                    createStructureForChildren(child, currentDirectory);
                }

            }*/

            if (currentItem.getClassyNode() instanceof Package) {
                Package pkg = (Package) currentItem.getClassyNode();
                String packageName = pkg.getName();

                File packageDirectory = new File(parentDirectory, packageName);
                if (!packageDirectory.exists()) {
                    if (packageDirectory.mkdirs()) {
                        System.out.println("Directory created: " + packageDirectory.getAbsolutePath());
                    } else {
                        System.out.println("Failed to create directory for package: " + packageName);
                    }
                }

                for (ClassyTreeItem child : currentItem.getChildren()) {
                    createStructureForChildren(child, packageDirectory);
                }
            }

            if (currentItem.getClassyNode() instanceof Diagram) {
                Diagram diagram = (Diagram) currentItem.getClassyNode();
                String diagramName = diagram.getName();

                File packageDirectory = parentDirectory;
                while (!(packageDirectory.getParentFile().getName().equals("ProjectExplorer"))) {
                    packageDirectory = packageDirectory.getParentFile();
                }

                File diagramFile = new File(parentDirectory, diagramName + ".java");

                try {
                    if (diagramFile.createNewFile()) {
                        System.out.println("File created: " + diagramFile.getAbsolutePath());
                        writeDiagramElementsToFile(diagram, diagramFile, diagram.getParent().getName());
                    } else {
                        System.out.println("File already exists.");
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred while creating the file: " + e.getMessage());
                }
            }

            for (ClassyTreeItem child : currentItem.getChildren()) {
                if (!(child.getClassyNode() instanceof Package) && !(child.getClassyNode() instanceof Diagram)) {
                    createStructureForChildren(child, parentDirectory);
                }
            }
        }
    }

    public void writeDiagramElementsToFile(Diagram diagram, File diagramFile, String packageName) throws IOException {
        try (FileWriter writer = new FileWriter(diagramFile)) {
            writer.write("import package " + packageName + ";" + System.lineSeparator() + System.lineSeparator());
            for (ClassyNode element : diagram.getChildren()) {
                if (element instanceof InterClass) {
                    InterClass interClass = (InterClass) element;
                    writeKlasaElement(interClass, writer);
                }

            }
        }
    }

    private void writeKlasaElement(InterClass inter, FileWriter writer) throws IOException {

        if (inter instanceof Klasa){

        } else if (inter instanceof Interfejs){

        } else if (inter instanceof Enum){

        }

        writer.write("public");

        if (inter instanceof Klasa){
            if (((Klasa)inter).isApstraktna()) {
                writer.write(" abstract");
            }
            writer.write(" class " + inter.getName());
        }

        if (inter instanceof Interfejs){
            writer.write(" interface " + inter.getName());
        }

        if (inter instanceof Enum){
            writer.write(" " + inter.getName());
        }

        if (hasGeneralizacija(inter) && generalizacijaCounter % 2 == 0) {
            writeGeneralizacijaDetails(inter, writer);
        } else {
            writer.write(" {");
            writer.write(System.lineSeparator());
        }

        if (inter instanceof Klasa)
            writeAttributes(inter.getCl(), writer);

        if (hasAgregacija(inter) && agregacijaCounter % 2 == 0) {
            writeAgregacijaDetails(inter, writer);
        }
        if (hasKompozicija(inter) && kompozicijaCounter % 2 == 0) {
            writeKompozicijaDetails(inter, writer); // ovde
        }

        writer.write("\n");
        if (!(inter instanceof Enum))
            writeMethods(inter, writer);

        if (inter instanceof Enum)
            writeEnumEL(inter.getNEnum(), writer);


        if (hasAgregacija(inter)) {
            agregacijaCounter++;
        }

        if (hasKompozicija(inter)) {
            kompozicijaCounter++;
        }

        if (hasGeneralizacija(inter)) {
            generalizacijaCounter++;
        }

        writer.write("}" + System.lineSeparator());
    }

    private boolean hasAgregacija(InterClass inter) {
        return inter.getListaVeza().stream().anyMatch(connection -> connection instanceof Agregacija);
    }

    private boolean hasKompozicija(InterClass inter) {
        return inter.getListaVeza().stream().anyMatch(connection -> connection instanceof Kompozicija);
    }

    private boolean hasGeneralizacija(InterClass inter) {
        return inter.getListaVeza().stream().anyMatch(connection -> connection instanceof Generalizacija);
    }

    private void writeAgregacijaDetails(InterClass inter, FileWriter writer) throws IOException {
        for (Connection connection : inter.getListaVeza()) {
            if (connection instanceof Agregacija) {
                Agregacija agregacija = (Agregacija) connection;
                String cardinality = agregacija.getKardinalnost();
                if (cardinality == "jedan") {
                    writer.write("\t" + agregacija.getVidljivost() + " " + agregacija.getToNaziv() + " " + agregacija.getName() + "; // Agregacija");
                    writer.write(System.lineSeparator());
                } else {
                    writer.write("\t" + agregacija.getVidljivost() + " List<" + agregacija.getToNaziv() + "> " + agregacija.getName() + "; // Agregacija - List");
                    writer.write(System.lineSeparator());
                }
            }
        }
    }

    private void writeKompozicijaDetails(InterClass inter, FileWriter writer) throws IOException {
        for (Connection connection : inter.getListaVeza()) {
            if (connection instanceof Kompozicija) {
                Kompozicija kompozicija = (Kompozicija) connection;
                String cardinality = kompozicija.getKardinalnost();
                if (cardinality == "jedan") {
                    writer.write("\t" + kompozicija.getVidljivost() + " " + kompozicija.getToNaziv() + " " + kompozicija.getName() + "; // Kompozicija");
                    writer.write(System.lineSeparator());
                } else {
                    writer.write("\t" + kompozicija.getVidljivost() + " List<" + kompozicija.getToNaziv() + "> " + kompozicija.getName() + "; // Kompozicija - List");
                    writer.write(System.lineSeparator());
                }
            }
        }
    }

    private void writeGeneralizacijaDetails(InterClass inter, FileWriter writer) throws IOException {
        for (Connection connection : inter.getListaVeza()) {
            if (connection instanceof Generalizacija) {
                Generalizacija generalizacija = (Generalizacija) connection;
                writer.write(" extends " + generalizacija.getToNaziv() + " {");
                writer.write(System.lineSeparator());
            }
        }
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

    private void writeMethods(InterClass interClass, FileWriter writer) throws IOException {
        boolean interClassIsAbstract = interClass instanceof Klasa && ((Klasa) interClass).isApstraktna();

        if (interClassIsAbstract) {
            writer.write("// Transfer abstract methods from abstract class");
            writer.write(System.lineSeparator());

            // Write a comment indicating that all methods in an abstract class are implicitly abstract
            writer.write("// All methods in the connected abstract class are implicitly abstract due to the class being abstract");
            writer.write(System.lineSeparator());

            // Handle logic for abstract class
            writeAbstractClassMethods(interClass, writer);
        } else {
            // Handle logic for non-abstract class
            writeConcreteClassMethods(interClass, writer);
            // Check if connected to an abstract class and import its abstract methods
            writeImportedAbstractMethods(interClass, writer);
        }
    }

    private void writeImportedAbstractMethods(InterClass inter, FileWriter writer) throws IOException {
        for (Connection connection : inter.getListaVeza()) {
            if (connection instanceof Generalizacija) {
                ClassyNode connectedNode = connection.getTo();
                if (!(connectedNode instanceof Klasa) || !((Klasa) connectedNode).isApstraktna()) {
                    connectedNode = connection.getFrom();
                }
                if (connectedNode instanceof Klasa && ((Klasa) connectedNode).isApstraktna()) {
                    Klasa abstractClass = (Klasa) connectedNode;
                    for (ClassContent abstractMethod : abstractClass.getCl()) {
                        if (abstractMethod instanceof Metod) {
                            String methodName = "\t@Override\n" +
                                    "\t" + abstractMethod.getVidljivost() + " " + abstractMethod.getTip() + " " + abstractMethod.getNaziv() + "() {\n" + "\t\t// TODO: Method implementation\n" + "\t}\n\n";
                            writer.write(methodName);
                        }
                    }
                }
            }
        }
    }

    private void writeAbstractClassMethods(InterClass interClass, FileWriter writer) throws IOException {
        // Write abstract methods of the abstract class
        for (ClassContent abstractMethod : interClass.getCl()) {
            if (abstractMethod instanceof Metod) {
                String methodName = abstractMethod.getNaziv();

                String methodInfo = "\t" + abstractMethod.getVidljivost() + " abstract " + abstractMethod.getTip() + " " + methodName + "();\n\n";
                writer.write(methodInfo);
            }
        }
    }
    private void writeConcreteClassMethods(InterClass interClass, FileWriter writer) throws IOException {
        writer.write("// Concrete class methods");
        writer.write(System.lineSeparator());

        // Write methods of the concrete class
        for (ClassContent concreteMethod : interClass.getCl()) {
            if (concreteMethod instanceof Metod) {
                String methodInfo = "\t" + concreteMethod.getVidljivost() + " " + concreteMethod.getTip() + " " + concreteMethod.getNaziv() + "() {\n" +
                        "\t\t// TODO: Method implementation\n" +
                        "\t}\n\n";
                writer.write(methodInfo);
            }
        }
    }

    private void writeEnumEL(List<ClanEnuma> clEn, FileWriter writer) throws IOException {
        int size = clEn.size();
        for (int i = 0; i < size; i++) {
            ClanEnuma ccc = clEn.get(i);
            writer.write("\t" + ccc.getValue());
            if (i < size - 1) {
                writer.write("," + System.lineSeparator());
            } else {
                writer.write(System.lineSeparator());
            }
        }
    }
}
