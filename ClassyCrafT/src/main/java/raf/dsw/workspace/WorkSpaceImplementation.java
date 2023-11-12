package raf.dsw.workspace;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter
@Setter
public class WorkSpaceImplementation implements IWorkspace{
    private PackageView packageView;

    @Override
    public PackageView generateWorkspace() {
        packageView = new PackageView();
        return packageView;
    }
}
