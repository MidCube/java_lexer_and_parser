package main.java.cig23.cam.ac.uk.SummerCompiler;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Parser {

    private static ArrayList<ArrayList<Terminals>> rules = ProductionRules.generateRules();

    private static String inputToString(List<Token> w) {
        String output = "";
        for(Token input:w) {
            if (input.t==Terminals.Finish){
                output+=input.c;
            } else {
                output+=input.toString();
            }

        }
        return output;
    }

    private static String actionToString(Action action) {
        String output = "";
        output+=action.action;
        output+=" ";
        if(action.action==ShiftReduce.Reduce){
            output+=ruleToString(rules.get(action.stateOrRule));
        } else {
            output+=action.stateOrRule;
        }

        return output;
    }

    public static String printStack(Stack<Integer> stack) {
        String values = Arrays.toString(stack.toArray());
        return values;
    }

    public static String ruleToString(ArrayList<Terminals> rule){
        String output = rule.get(0).toString();
        output += "-->";
        for(int i=1;i<rule.size();i++) {
            output+=rule.get(i).toString();
        }
        return output;
    }

    public static Boolean parse(List<Token> w) throws Main.ParsingException {
        w.add(new Token(Terminals.Finish,"$"));

        Stack<ArrayList<Terminals>> rulesApplied = new Stack<>();
        SLRTable table = new SLRTable();
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        System.out.println("Stack        Input          Action");
        System.out.println(printStack(stack)+"          "+inputToString(w)+"            ");
        Token a = w.remove(0);

        for(;true;) {
            Integer s = stack.peek();



            Action action = table.actions.get(s).get(a.t);

            if (action == null) {
                throw new Main.ParsingException("This is not accepted");
            }
            if (action.action == ShiftReduce.Shift){
                stack.push(action.stateOrRule);
                System.out.println(printStack(stack)+"          "+inputToString(w)+"            "+actionToString(action));
                a = w.remove(0);
            } else if (action.action == ShiftReduce.Reduce) {
                ArrayList<Terminals> rule = rules.get(action.stateOrRule);
                int size = rule.size()-1;
                for(int i = 0;i<size;i++) {
                    stack.pop();
                }
                int t = stack.peek();

                stack.push(table.gotos.get(t).get(rule.get(0)));
                rulesApplied.push(rules.get(action.stateOrRule));
                System.out.println(printStack(stack)+"          "+inputToString(w)+"            "+actionToString(action));

            } else if (action.action == ShiftReduce.Accept) {

                System.out.println("ACCEPTED");
                Tree parseTree = new Tree(rulesApplied);
                System.out.println(Tree.printTree(parseTree.head));
                return true;
            } else {
                throw new Main.ParsingException("This is not accepted");
            }

        }


    }
}
