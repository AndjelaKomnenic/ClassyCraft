package raf.dsw.state;

import raf.dsw.classyrepository.implementation.Diagram;
import raf.dsw.core.ApplicationFramework;
import raf.dsw.novo.Klasa;
import raf.dsw.workspace.view.DiagramView;
import raf.dsw.workspace.view.PackageView;

import javax.swing.*;
import java.awt.*;

//klase, interfejsa, enuma
public class DodavanjeState implements State{
    @Override
    public void misKliknut(int x, int y, Diagram currDiagram) {
        /*DiagramView currDiagramView = (DiagramView) currDiagram.getParent().getTabbedPane().getSelectedComponent();

        String topicString = JOptionPane.showInputDialog(new JFrame(), "Unesite pojam", "Pojam", JOptionPane.PLAIN_MESSAGE);*/
        /*if(topicString == null || topicString.trim().equals("")){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(IllegalEvent.TOPIC_CANNOT_BE_EMPTY);
            return;
        }*/
        /*Klasa newKlasa = new Klasa(topicString, currDiagramView, newKlasa.x, newKlasa.y);
        currDiagramView.addChild(newTopic);

        //new painter
        Painter painter = new TopicPainter(newTopic);
        thisTopicPainter = painter;
        projectView.addPainterForCurrent(painter);*/
        //Klasa newKlasa = new Klasa();
    }

    @Override
    public void misOtpusten(Point e, PackageView packageView, Diagram currDiagram) {

    }

    @Override
    public void misPrivucen(Point e, PackageView packageView, Diagram currDiagram) {

    }
}
