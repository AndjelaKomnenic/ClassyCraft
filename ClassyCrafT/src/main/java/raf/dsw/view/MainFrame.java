package raf.dsw.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classyrepository.implementation.ProjectExplorer;
import raf.dsw.controller.ActionManager;
import raf.dsw.core.ApplicationFramework;
import raf.dsw.message.Message;
import raf.dsw.observer.ISubscriber;
import raf.dsw.tree.ClassyTree;
import raf.dsw.tree.ClassyTreeImplementation;
import raf.dsw.tree.controller.MouseControl;
import raf.dsw.tree.view.ClassyTreeView;
import raf.dsw.workspace.IWorkspace;
import raf.dsw.workspace.WorkSpaceImplementation;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class MainFrame extends JFrame implements ISubscriber {
    private static MainFrame instance;
    private ActionManager actionManager;
    private ClassyTree classyTree;
    private IWorkspace workspace;

    private MySideBar sideBar;

    private void initialise(){
        actionManager = new ActionManager();
        classyTree = new ClassyTreeImplementation();
        workspace = new WorkSpaceImplementation();
        initializeGUI();
        ApplicationFramework.getInstance().getMessageGenerator().addSubscriber(this);
    }

    private void initializeGUI(){

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ClassyCrafT");

        MyMenuBar menu = new MyMenuBar();
        setJMenuBar(menu);

        MyToolBar toolBar = new MyToolBar();
        add(toolBar, BorderLayout.NORTH);

        JPanel rightPanel = workspace.generateWorkspace();
        //JScrollPane scrollPaneRight = new JScrollPane(rightPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


        ProjectExplorer pe = ApplicationFramework.getInstance().getClassyRepository().getProjectExplorer();
        ClassyTreeView jTreeProjectExplorer = classyTree.generateTree(pe);
        pe.addSubscriber(jTreeProjectExplorer);
        jTreeProjectExplorer.addMouseListener(new MouseControl(jTreeProjectExplorer));

        JScrollPane scroll=new JScrollPane(jTreeProjectExplorer);
        scroll.setMinimumSize(new Dimension(200,150));
        JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, rightPanel); //scrollPaneRight
        getContentPane().add(split,BorderLayout.CENTER);
        split.setDividerLocation(250);
        split.setOneTouchExpandable(true);

        sideBar = new MySideBar();
        getContentPane().add(sideBar, BorderLayout.EAST);

    }

    public static MainFrame getInstance(){
        if (instance == null){
            instance = new MainFrame();
            instance.initialise();
        }
        return instance;
    }
    public void update(Object object){
        if(object instanceof Message) {
            Message message = (Message) object;
            String type = message.getType();
            if (type.equalsIgnoreCase("warning")) {
                JOptionPane.showMessageDialog(null, message.getText(), "Upozorenje", JOptionPane.WARNING_MESSAGE);
            } else if (type.equalsIgnoreCase("error")) {
                JOptionPane.showMessageDialog(null, message.getText(), "Greska", JOptionPane.ERROR_MESSAGE);
            } else if (type.equalsIgnoreCase("information")) {
                JOptionPane.showMessageDialog(null, message.getText(), "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

}