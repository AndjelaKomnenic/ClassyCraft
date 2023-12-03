package raf.dsw.state;

import lombok.Getter;

@Getter
public class StateManager {
    private DodavanjeState newDodavanjeState;
    private DodavanjeSadrzajaState newDodavanjeSadrzajaState;
    private DodavanjeVezaState newDodavanjeVezeState;
    private BrisanjeState newBrisanjeState;
    private SelekcijaState newSelekcijaState;
    private State currState;


    public StateManager() {
        newDodavanjeState = new DodavanjeState();
        newDodavanjeSadrzajaState = new DodavanjeSadrzajaState();
        newDodavanjeVezeState = new DodavanjeVezaState();
        newBrisanjeState = new BrisanjeState();
        newSelekcijaState = new SelekcijaState();
        currState = newDodavanjeState;
    }

    public void setNewDodavanjeState(){
        currState = newDodavanjeState;
    }
    public void setNewDodavanjeSadrzajaState() {currState = newDodavanjeSadrzajaState;}
    public void setNewDodavanjeVezeState(){currState = newDodavanjeVezeState;}
    public void setNewBrisanjeState(){ currState = newBrisanjeState;}
    public void setNewSelekcijaState(){currState = newSelekcijaState;}
}
