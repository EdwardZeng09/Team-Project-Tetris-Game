package model;

public class TetrisContext {
    private State state;


    public TetrisContext(){
        this.state = new StateOne(this);

    }
    public State getState(){
        return state;
    }

    public void changeDifficulty(State state){
        this.state = state;
    }
    
}
