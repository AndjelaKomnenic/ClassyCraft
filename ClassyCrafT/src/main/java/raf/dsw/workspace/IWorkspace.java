package raf.dsw.workspace;

import raf.dsw.workspace.view.PackageView;

public interface IWorkspace {
    PackageView generateWorkspace();
    PackageView getPackageView();
}
