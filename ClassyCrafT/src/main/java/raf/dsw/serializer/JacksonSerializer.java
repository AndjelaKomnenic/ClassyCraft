package raf.dsw.serializer;


//import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import lombok.Getter;
import lombok.Setter;
import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.classyrepository.composite.ClassyNodeComposite;
import raf.dsw.classyrepository.implementation.Diagram;
import raf.dsw.classyrepository.implementation.Package;
import raf.dsw.classyrepository.implementation.Project;
import raf.dsw.components.*;
import raf.dsw.components.Enum;
import raf.dsw.paint.*;
import raf.dsw.tree.ClassyTreeImplementation;
import raf.dsw.tree.model.ClassyTreeItem;
import raf.dsw.tree.view.ClassyTreeView;
import raf.dsw.view.MainFrame;
import raf.dsw.workspace.view.PackageView;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class JacksonSerializer implements Serializer {

    private final ObjectMapper objectMapper;

    public JacksonSerializer(){
        List<ClassyNode> a = new ArrayList<>();
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()              //prihvatamo ove tipove podataka (bunio se dzekson)
                .allowIfSubType(Project.class)
                .allowIfSubType(Package.class)
                .allowIfSubType(Diagram.class)
                .allowIfSubType(Klasa.class)
                .allowIfSubType(Enum.class)
                .allowIfSubType(Interfejs.class)
                .allowIfSubType("java.util.List")
                .allowIfSubType("java.util.ArrayList")
                .allowIfSubType("java.awt.Point")
                .allowIfSubType("java.awt.Dimension")
                .build();
        objectMapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);
    }
    @Override
    public Project loadProject(File file) {


        try {
            Project project = objectMapper.readValue(file, Project.class);

            for (ClassyNode pkg : project.getChildren()) {
                pkg.setParent(project); // Set parent reference of Package to Project

                if (pkg instanceof ClassyNodeComposite) {
                    ClassyNodeComposite packageComposite = (ClassyNodeComposite) pkg;

                    for (ClassyNode diagram : packageComposite.getChildren()) {
                        diagram.setParent(pkg); // Set parent reference of Diagram to Package
                        // odavde ga sibni za tmp
                        if (diagram instanceof ClassyNodeComposite) {
                            ClassyNodeComposite diagramComposite = (ClassyNodeComposite) diagram;

                            // If Diagram contains elements (children), set their parent references
                            List<ClassyNode> elementi = diagramComposite.getChildren();
                            for (ClassyNode element : elementi) {
                                element.setParent(diagram); // Set parent reference of Element to Diagram

                                if(element instanceof Connection)
                                {
                                    Connection konekcija = (Connection)element;
                                    InterClass from = nadjiKlasu(elementi, konekcija.getFromNaziv(), konekcija.getFromType());
                                    InterClass to = nadjiKlasu(elementi, konekcija.getToNaziv(), konekcija.getToType());
                                    konekcija.setFrom(from);
                                    konekcija.setTo(to);
                                    from.addToListVeza(konekcija);
                                    to.addToListVeza(konekcija);
                                }
                            }
                        }
                    }
                }
            }

            ClassyTreeView treeView = ((ClassyTreeImplementation) MainFrame.getInstance().getClassyTree()).getTreeView();
            ClassyTreeItem root = (ClassyTreeItem) treeView.getModel().getRoot();
            TreePath path = new TreePath(root.getPath());
            treeView.setSelectionPath(path);
            treeView.expandPath(treeView.getSelectionPath());

            ((ClassyNodeComposite) root.getClassyNode()).addChild(project);
            SwingUtilities.updateComponentTreeUI(treeView);

            /*for (ClassyNode pkg : project.getChildren()) {
                project.getChildren().add(pkg);
            }*/


            PackageView pkgVIew = MainFrame.getInstance().getWorkspace().getPackageView();


            for (ClassyNode pkg : project.getChildren()) {
                if (pkg instanceof Package) {
                    Package currentPackage = (Package) pkg;

                    // Get Diagrams within the current package
                    // Process elements within the diagrams as needed

                    // Update the workspace of PackageView with the current package
                    pkgVIew.updateWorkspace(currentPackage);
                }
            }


            /*for (ClassyNode diagram : project.getChildren()){
                if (diagram instanceof Diagram)
                    repaintTheDiagram(pkgVIew, (Diagram) diagram);
            }*/

            return project;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    private InterClass nadjiKlasu(List<ClassyNode> klase, String naziv, String tip)
    {
        for (ClassyNode element : klase) {
            if (element instanceof InterClass) {
                InterClass klasa = (InterClass) element;
                if(klasa.getName().equals(naziv) && klasa.tipKlase().equals(tip))
                {
                    return klasa;
                }
            }
        }
        return null;
    }

    @Override
    public void saveProject(Project project) {
        try {
            objectMapper.writeValue(new File(project.getFilePath()), project);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadTemplate(File file) {
        PackageView pkgView = MainFrame.getInstance().getWorkspace().getPackageView();
        ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectedNode();

        try {
            Diagram diagram = objectMapper.readValue(file, Diagram.class);

            Package selectedPackage = (Package) selected.getClassyNode();

            diagram.setParent(selectedPackage);
            selectedPackage.addChild(diagram);

            List<ClassyNode> elementi = diagram.getChildren();
            for (ClassyNode element : elementi) {
                element.setParent(diagram);

                if(element instanceof Connection)
                {
                    Connection konekcija = (Connection)element;
                    InterClass from = nadjiKlasu(elementi, konekcija.getFromNaziv(), konekcija.getFromType());
                    InterClass to = nadjiKlasu(elementi, konekcija.getToNaziv(), konekcija.getToType());
                    konekcija.setFrom(from);
                    konekcija.setTo(to);
                    from.addToListVeza(konekcija);
                    to.addToListVeza(konekcija);
                }
            }
            pkgView.updateWorkspace(selectedPackage);

            ClassyTreeImplementation mti = (ClassyTreeImplementation)MainFrame.getInstance().getClassyTree();
            mti.addChildToDiag(selected, diagram);


            for(ClassyNode element : diagram.getChildren())
                element.setParent(diagram);

            String fileName = file.getName();
            String diagramName = fileName.replaceFirst("[.][^.]+$", ""); // Removes the file extension
            
            diagram.setName(diagramName);

            //repaintTheDiagram(pkgView, diagram);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void saveTemplate(Diagram diagram, String templateName) {
        diagram.setTemplate(true);
        diagram.setName(templateName);
        try {
            //String json = objectMapper.writeValueAsString(diagram); // Serialize the Diagram object to JSON

            // Write JSON to file
            File directory = new File("src/main/resources/templates/");
            if (!directory.exists()) {
                if (directory.mkdirs()) {
                    System.out.println("Directory created successfully.");
                } else {
                    System.out.println("Failed to create directory.");
                }
            }
            //File file = new File(getClass().getClassLoader().getResource("templates/" + templateName + ".json").getFile());
            File file = new File("src/main/resources/templates/" + templateName + ".json");
            //Path absolutePath = Paths.get(relativePath).toAbsolutePath();
            /*Path absolutePath = Paths.get("src/main/resources/templates/" + templateName + ".json").toAbsolutePath();
            System.out.println(absolutePath);*/
            objectMapper.writeValue(file, diagram);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*private void repaintTheDiagram(PackageView pkgView, Diagram diagram){
        List<DiagramElement> elements = new ArrayList<>();

        for (ClassyNode el : diagram.getChildren()){
            ElementPainter elP = null;
            if (el instanceof InterClass){
                if (el instanceof Klasa)
                    elP = new InterClassPainter((Klasa)el, (int)((Klasa) el).getWidth(), (int)((Klasa) el).getHeight(), (int)((Klasa) el).getX(), (int)((Klasa) el).getY());
                else if (el instanceof Interfejs)
                    elP = new InterClassPainter((Interfejs)el, (int)((Interfejs) el).getWidth(), (int)((Interfejs) el).getHeight(), (int)((Interfejs) el).getX(), (int)((Interfejs) el).getY());
                else if (el instanceof Enum)
                    elP = new InterClassPainter((Enum)el, (int)((Enum) el).getWidth(), (int)((Enum) el).getHeight(), (int)((Enum) el).getX(), (int)((Enum) el).getY());
            }
            pkgView.getPaintersForDiagram().get(diagram).add(elP);
        }

        pkgView.repaintAll();
    }*/
}