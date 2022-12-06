package model;

public abstract class State {
    TetrisContext context;

    State(TetrisContext context){
        this.context = context;
    }
    public abstract String atone();
    public abstract String atTwo();
    public abstract String atThree();
}
