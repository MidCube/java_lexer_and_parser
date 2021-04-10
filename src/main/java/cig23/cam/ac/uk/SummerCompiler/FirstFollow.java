package main.java.cig23.cam.ac.uk.SummerCompiler;

import java.security.cert.TrustAnchor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class FirstFollow {
    public HashMap<Terminals, HashSet<Terminals>> first;
    public HashMap<Terminals, HashSet<Terminals>> follows;

    public FirstFollow() {
        ArrayList<ArrayList<Terminals>> rules = ProductionRules.generateRules();
        this.first = new HashMap<>();
        this.follows = new HashMap<>();
        //S
        HashSet<Terminals> firstSet = new HashSet<>();
        firstSet.add(Terminals.Cos);
        firstSet.add(Terminals.Number);
        firstSet.add(Terminals.LBracket);
        HashSet<Terminals> followSet = new HashSet<>();
        followSet.add(Terminals.Finish);
        this.first.put(Terminals.S,firstSet);
        this.follows.put(Terminals.S,followSet);
        //E
        firstSet = new HashSet<>();
        firstSet.add(Terminals.Cos);
        firstSet.add(Terminals.Number);
        firstSet.add(Terminals.LBracket);
        followSet = new HashSet<>();
        followSet.add(Terminals.Rbracket);
        followSet.add(Terminals.Plus);
        followSet.add(Terminals.Finish);
        this.first.put(Terminals.E,firstSet);

        this.follows.put(Terminals.E,followSet);

        //A
        firstSet = new HashSet<>();
        firstSet.add(Terminals.Cos);
        firstSet.add(Terminals.Number);
        firstSet.add(Terminals.LBracket);
        followSet = new HashSet<>();
        followSet.add(Terminals.Plus);
        followSet.add(Terminals.Finish);
        followSet.add(Terminals.Rbracket);
        followSet.add(Terminals.Minus);
        this.first.put(Terminals.A,firstSet);
        this.follows.put(Terminals.A,followSet);

        //B
        firstSet = new HashSet<>();
        firstSet.add(Terminals.Cos);
        firstSet.add(Terminals.Number);
        firstSet.add(Terminals.LBracket);
        followSet = new HashSet<>();
        followSet.add(Terminals.Plus);
        followSet.add(Terminals.Minus);
        followSet.add(Terminals.Finish);
        followSet.add(Terminals.Rbracket);
        this.first.put(Terminals.B,firstSet);
        this.follows.put(Terminals.B,followSet);
        //C
        firstSet = new HashSet<>();
        firstSet.add(Terminals.Cos);
        firstSet.add(Terminals.Number);
        firstSet.add(Terminals.LBracket);
        followSet = new HashSet<>();
        followSet.add(Terminals.Plus);
        followSet.add(Terminals.Minus);
        followSet.add(Terminals.Mult);
        followSet.add(Terminals.Finish);
        followSet.add(Terminals.Rbracket);
        this.first.put(Terminals.C,firstSet);
        this.follows.put(Terminals.C,followSet);
        //D
        firstSet = new HashSet<>();
        firstSet.add(Terminals.Number);
        firstSet.add(Terminals.LBracket);
        followSet = new HashSet<>();
        followSet.add(Terminals.Plus);
        followSet.add(Terminals.Minus);
        followSet.add(Terminals.Mult);
        followSet.add(Terminals.Bang);
        followSet.add(Terminals.Finish);
        followSet.add(Terminals.Rbracket);
        this.first.put(Terminals.D,firstSet);
        this.follows.put(Terminals.D,followSet);
        //F
        firstSet = new HashSet<>();
        firstSet.add(Terminals.Number);
        firstSet.add(Terminals.LBracket);
        followSet = new HashSet<>();
        followSet.add(Terminals.Plus);
        followSet.add(Terminals.Minus);
        followSet.add(Terminals.Mult);
        followSet.add(Terminals.Bang);
        followSet.add(Terminals.Finish);
        followSet.add(Terminals.Rbracket);
        this.first.put(Terminals.F,firstSet);
        this.follows.put(Terminals.F,followSet);


    }


}
