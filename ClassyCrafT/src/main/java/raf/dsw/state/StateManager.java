package raf.dsw.state;


public class StateManager {
    private DodavanjeState newDodavanjeState;
    private DodavanjeSadrzajaState newDodavanjeSadrzajaState;
    private DodavanjeVezaState newDodavanjeVezeState;
    private BrisanjeState newBrisanjeState;
    private SelectionState newSelectionState;
    private MoveState newMoveState;
    private ZoomInState newZoomInState;
    private ZoomOutState newZoomOutState;
    private DupliranjeState newDupliranjeState;
    private State currState;


    public StateManager() {
        newDodavanjeState = new DodavanjeState();
        newDodavanjeSadrzajaState = new DodavanjeSadrzajaState();
        newDodavanjeVezeState = new DodavanjeVezaState();
        newBrisanjeState = new BrisanjeState();
        newSelectionState = new SelectionState();
        newMoveState = new MoveState();
        newZoomInState = new ZoomInState();
        newZoomOutState = new ZoomOutState();
        newDupliranjeState = new DupliranjeState();
        currState = newDodavanjeState;
    }

    public State getCurrState(){return currState;}
    public void setNewDodavanjeState(){
        currState = newDodavanjeState;
    }
    public void setNewDodavanjeSadrzajaState() {currState = newDodavanjeSadrzajaState;}
    public void setNewDodavanjeVezeState(){currState = newDodavanjeVezeState;}
    public void setNewBrisanjeState(){ currState = newBrisanjeState;}
    public void setDupliranjeState(){currState = newDupliranjeState;}
    public void setNewSelekcijaState(){currState = newSelectionState;}
    public void setNewMoveState(){ currState = newMoveState;}
    public void setNewZoomInState(){ currState = newZoomInState;}
    public void setNewZoomOutState(){ currState = newZoomOutState; }
}