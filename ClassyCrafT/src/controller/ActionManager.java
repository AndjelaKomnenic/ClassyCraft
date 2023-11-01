package controller;

/*@Getter
@Setter*/ // e sad sto se tice njih treba biblioteku da dodam "Lombok", al za sada cu bez njih raditi
public class ActionManager {
    private ExitAction exitAction;
    private AboutUs aboutUsAction;

    public ActionManager() {
        initialiseActions();
    }

    private void initialiseActions(){
        exitAction = new ExitAction();
        aboutUsAction = new AboutUs();
    }

    public ExitAction getExitAction() {
        return exitAction;
    }

    public void setExitAction(ExitAction exitAction) {
        this.exitAction = exitAction;
    }

    public AboutUs getAboutUsAction() {
        return aboutUsAction;
    }

    public void setAboutUsAction(AboutUs aboutUsAction) {
        this.aboutUsAction = aboutUsAction;
    }
}
