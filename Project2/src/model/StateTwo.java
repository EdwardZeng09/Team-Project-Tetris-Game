package model;

public class StateTwo extends State{
    public StateTwo(TetrisContext context){
        super(context);
    }
    @Override
    public String atone() {
        context.changeDifficulty(new StateOne(context));
        return "sealed";
    }

    @Override
    public String atTwo() {
        context.changeDifficulty(new StateTwo(context));
        return "Difficulty two";
    }

    @Override
    public String atThree() {
        context.changeDifficulty(new StateThree(context));
        return "sealed";
    }
}
