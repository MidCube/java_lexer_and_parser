package main.java.cig23.cam.ac.uk.SummerCompiler;

public class Action {
    public final ShiftReduce action;
    public final int stateOrRule;
    public Action(ShiftReduce action, int stateOrRule) {
        this.action = action;
        this.stateOrRule = stateOrRule;
    }
}
