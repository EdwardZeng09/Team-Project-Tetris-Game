package model;

public class StateThree extends State{
    public StateThree(TetrisContext context){
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
        return "sealed";
    }

    @Override
    public String atThree() {
        context.changeDifficulty(new StateThree(context));
        return "Difficulty three";
    }
}
