package model;

public class TetrisContext {
    private State state;


    public TetrisContext(){
        this.state = new StateOne(this);

    }

    public void changeDifficulty(State state){
        this.state = state;
    }
    
}
