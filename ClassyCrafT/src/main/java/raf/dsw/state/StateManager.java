package raf.dsw.state;

import lombok.Getter;


public class StateManager {
    private DodavanjeState newDodavanjeState;
    private DodavanjeSadrzajaState newDodavanjeSadrzajaState;
    private DodavanjeVezaState newDodavanjeVezeState;
    private BrisanjeState newBrisanjeState;
    private SelekcijaState newSelekcijaState;
    private MoveState newMoveState;
    private ZoomInState newZoomInState;
    private ZoomOutState newZoomOutState;
    private State currState;


    public StateManager() {
        newDodavanjeState = new DodavanjeState();
        newDodavanjeSadrzajaState = new DodavanjeSadrzajaState();
        newDodavanjeVezeState = new DodavanjeVezaState();
        newBrisanjeState = new BrisanjeState();
        newSelekcijaState = new SelekcijaState();
        newMoveState = new MoveState();
        newZoomInState = new ZoomInState();
        newZoomOutState = new ZoomOutState();
        currState = newDodavanjeState;
    }

    public State getCurrState(){return currState;}
    public void setNewDodavanjeState(){
        currState = newDodavanjeState;
    }
    public void setNewDodavanjeSadrzajaState() {currState = newDodavanjeSadrzajaState;}
    public void setNewDodavanjeVezeState(){currState = newDodavanjeVezeState;}
    public void setNewBrisanjeState(){ currState = newBrisanjeState;}
    public void setNewSelekcijaState(){currState = newSelekcijaState;}
    public void setNewMoveState(){ currState = newMoveState;}
    public void setNewZoomInState(){ currState = newZoomInState;}
    public void setNewZoomOutState(){ currState = newZoomOutState; }
}