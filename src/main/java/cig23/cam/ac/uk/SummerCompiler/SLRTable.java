package main.java.cig23.cam.ac.uk.SummerCompiler;

import java.util.*;

public class SLRTable {
    public HashMap<Integer,HashMap<Terminals,Action>> actions;
    public HashMap<Integer, HashMap<Terminals,Integer>> gotos;

    public SLRTable() {
        FirstFollow firstFollow = new FirstFollow();
        Set<Terminals> actualTerminals = new HashSet<>();
        actualTerminals.add(Terminals.LBracket);
        actualTerminals.add(Terminals.Rbracket);
        actualTerminals.add(Terminals.Number);
        actualTerminals.add(Terminals.Plus);
        actualTerminals.add(Terminals.Minus);
        actualTerminals.add(Terminals.Mult);
        actualTerminals.add(Terminals.Cos);
        actualTerminals.add(Terminals.Bang);
        actualTerminals.add(Terminals.Finish);

        ArrayList<ArrayList<Terminals>> Rules = ProductionRules.generateRules();
        this.actions = new HashMap<>();
        this.gotos = new HashMap<>();
        //step 1
        ArrayList<ArrayList<Item>> C = CanonicalCollection.allStates();
        for (int i=0;i<C.size();i++) {


            //step 2

            ArrayList<Item> currentState = C.get(i);
            for(Item item:currentState){


                //b
                if (Rules.get(item.ProductionRule).size() == item.DotPlace+1) {
                    Terminals head = Rules.get(item.ProductionRule).get(0);
                    if (head!=Terminals.S) {

                        HashSet<Terminals> followingThis = firstFollow.follows.get(head);

                        for(Terminals next:followingThis) {
                            int j = item.ProductionRule;
                            Action myAction = new Action(ShiftReduce.Reduce,j);
                            HashMap<Terminals,Action> action;
                            if (this.actions.get(i)==null) {
                                action = new HashMap<>();
                            } else {
                                action = this.actions.get(i);
                            }

                            action.put(next,myAction);
                            this.actions.put(i,action);

                        }

                    } else {

                        //c
                        Action myAction = new Action(ShiftReduce.Accept,0);
                        HashMap<Terminals,Action> action;
                        if (this.actions.get(i)==null) {
                            action = new HashMap<>();
                        } else {
                            action = this.actions.get(i);
                        }
                        action.put(Terminals.Finish,myAction);
                        this.actions.put(i,action);
                    }
                } else if (actualTerminals.contains(Rules.get(item.ProductionRule).get(item.DotPlace+1))) {
                    //a

                    Terminals nextInput = Rules.get(item.ProductionRule).get(item.DotPlace+1);
                    ArrayList<Item> nextState = GoTo.GoTo(currentState,nextInput);
                    Integer j = C.indexOf(nextState);
                    Action myAction = new Action(ShiftReduce.Shift,j);
                    HashMap<Terminals,Action> action;
                    if (this.actions.get(i)==null) {
                        action = new HashMap<>();
                    } else {
                        action = this.actions.get(i);
                    }
                    action.put(nextInput,myAction);
                    this.actions.put(i,action);
                }
            }
            //step 3

            for(Terminals terminal:Terminals.values()) {
                if (!actualTerminals.contains(terminal)) {

                    ArrayList<Item> nextState = GoTo.GoTo(currentState,terminal);

                    Integer j = C.indexOf(nextState);

                    HashMap<Terminals,Integer> move;
                    if (this.gotos.get(i)==null) {
                        move = new HashMap<>();
                    } else {
                        move = this.gotos.get(i);
                    }
                    move.put(terminal,j);

                    this.gotos.put(i,move);
                }
            }




        }



    }

}
