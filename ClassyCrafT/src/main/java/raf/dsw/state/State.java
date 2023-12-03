package raf.dsw.state;

import raf.dsw.classyrepository.implementation.Diagram;
import raf.dsw.workspace.view.PackageView;

import java.awt.*;

public interface  State {

     void misKliknut(int x, int y, Diagram currDiagram);
     void misOtpusten(Point e, PackageView packageView, Diagram currDiagram);
     void misPrivucen(Point e, PackageView packageView, Diagram currDiagram);
}
