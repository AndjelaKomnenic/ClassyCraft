package raf.dsw.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.controller.ActionManager;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class MainFrame extends JFrame {
    private static MainFrame instance;
    private ActionManager actionManager;

    private MainFrame(){

    }

    private void initialise(){
        actionManager = new ActionManager();
        initializeGUI();
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
    }

    public static MainFrame getInstance(){
        if (instance == null){
            instance = new MainFrame();
            instance.initialise();
        }
        return instance;
    }

}