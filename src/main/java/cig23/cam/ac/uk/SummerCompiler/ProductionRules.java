package main.java.cig23.cam.ac.uk.SummerCompiler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ProductionRules {
    public static ArrayList<ArrayList<Terminals>> generateRules(){
        //This generates all the production rules in a list
        //We can index the list and the place the dot is at to represent
        //An item
        ArrayList<ArrayList<Terminals>> rules = new ArrayList<>();
        //Rule for S
        //rule 0
        ArrayList<Terminals> rule = new ArrayList<>(Arrays.asList(Terminals.S, Terminals.E));
        rules.add(rule);
        //Rule for E
        //rule 1
        rule = new ArrayList<>(Arrays.asList(Terminals.E, Terminals.E,Terminals.Plus, Terminals.A));
        rules.add(rule);
        //rule 2
        rule = new ArrayList<>(Arrays.asList(Terminals.E,Terminals.A));
        rules.add(rule);


        //Rules for A
        //rule 3
        rule = new ArrayList<>(Arrays.asList(Terminals.A,Terminals.A,Terminals.Minus, Terminals.B));
        rules.add(rule);
        //rule 4
        rule = new ArrayList<>(Arrays.asList(Terminals.A,Terminals.B));
        rules.add(rule);

        //Rules for B
        //rule 5
        rule = new ArrayList<>(Arrays.asList(Terminals.B,Terminals.C,Terminals.Mult,Terminals.B));
        rules.add(rule);
        //rule 6
        rule = new ArrayList<>(Arrays.asList(Terminals.B,Terminals.C));
        rules.add(rule);

        //Rules for C
        //rule 7
        rule = new ArrayList<>(Arrays.asList(Terminals.C,Terminals.Cos,Terminals.C));
        rules.add(rule);
        //rule 8
        rule = new ArrayList<>(Arrays.asList(Terminals.C,Terminals.D));
        rules.add(rule);

        //Rules for D
        //rule 9
        rule = new ArrayList<>(Arrays.asList(Terminals.D,Terminals.D,Terminals.Bang));
        rules.add(rule);
        //rule 10
        rule = new ArrayList<>(Arrays.asList(Terminals.D,Terminals.F));
        rules.add(rule);

        //Rules for F
        //rule 11
        rule = new ArrayList<>(Arrays.asList(Terminals.F,Terminals.LBracket,Terminals.E,Terminals.Rbracket));
        rules.add(rule);
        //rule 12
        rule = new ArrayList<>(Arrays.asList(Terminals.F,Terminals.Number));
        rules.add(rule);

        return rules;
    }
}
