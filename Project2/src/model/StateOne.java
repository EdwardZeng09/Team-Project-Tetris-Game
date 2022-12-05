package model;

public class StateOne extends State{
    public StateOne(TetrisContext context){
        super(context);
    }

    @Override
    public String atone() {
        context.changeDifficulty(new StateOne(context));
        return "Difficulty one";
    }

    @Override
    public String attwo() {
        context.changeDifficulty(new StateTwo(context));
        return "sealed";
    }

    @Override
    public String atthree() {
        context.changeDifficulty(new StateThree(context));
        return "sealed";
    }
}
