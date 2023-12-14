package raf.dsw.state;

import lombok.Getter;


public class StateManager {
    private DodavanjeState newDodavanjeState;
    private DodavanjeSadrzajaState newDodavanjeSadrzajaState;
    private DodavanjeVezaState newDodavanjeVezeState;
    private BrisanjeState newBrisanjeState;
    private SelekcijaState newSelekcijaState;
    private MoveState newMoveState;
    private DupliranjeState newDupliranjeState;
    private State currState;


    public StateManager() {
        newDodavanjeState = new DodavanjeState();
        newDodavanjeSadrzajaState = new DodavanjeSadrzajaState();
        newDodavanjeVezeState = new DodavanjeVezaState();
        newBrisanjeState = new BrisanjeState();
        newSelekcijaState = new SelekcijaState();
        newMoveState = new MoveState();
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
    public void setNewSelekcijaState(){currState = newSelekcijaState;}
    public void setNewMoveState(){ currState = newMoveState;}
}