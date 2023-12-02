package raf.dsw.state;

import lombok.Getter;

@Getter
public class StateManager {
    private DodavanjeState newDodavanjeState;
    private DodavanjeSadrzajaState newDodavanjeSadrzajaState;
    private DodavanjeVezaState newDodavanjeVezeState;
    private State currState;


    public StateManager() {
        newDodavanjeState = new DodavanjeState();
        newDodavanjeSadrzajaState = new DodavanjeSadrzajaState();
        newDodavanjeVezeState = new DodavanjeVezaState();
        currState = newDodavanjeState;
    }

    public void setNewDodavanjeState(){
        currState = newDodavanjeState;
    }
}
