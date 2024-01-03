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

    public Generator() {

    }

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
            }

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

                File diagramFile = new File(parentDirectory, diagramName + ".txt");

                try {
                    if (diagramFile.createNewFile()) {
                        System.out.println("File created: " + diagramFile.getAbsolutePath());
                        writeDiagramElements(diagram, diagramFile); // ovde pise
                    } else {
                        System.out.println("File already exists.");
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred while creating the file: " + e.getMessage());
                }
            }

            if (!(currentItem.getClassyNode() instanceof Package)) {
                for (ClassyTreeItem child : currentItem.getChildren()) {
                    createStructureForChildren(child, parentDirectory);
                }
            }
        }
    }

    private void writeDiagramElements(Diagram diagram, File diagramFile) throws IOException {
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
    }
}
