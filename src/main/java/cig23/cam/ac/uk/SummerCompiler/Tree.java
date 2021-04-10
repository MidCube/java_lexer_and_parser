package main.java.cig23.cam.ac.uk.SummerCompiler;

import java.util.*;

public class Tree {

    public Node head;

    public Tree() {
        this.head = null;
    }

    public Tree(Stack<ArrayList<Terminals>> ruleStack){
        ArrayList<ArrayList<Terminals>> rules = new ArrayList<>();
        int stackSize = ruleStack.size();
        for(int i=0;i<stackSize;i++){
            rules.add(ruleStack.pop());
        }
        this.head = new Node(rules.get(0).get(0),null,null,null);
        if (rules.get(0).size()==4) {
            this.head.left = new Node(rules.get(0).get(1),null,null,null);
            this.head.mid = new Node(rules.get(0).get(2),null,null,null);
            this.head.right = new Node(rules.get(0).get(3),null,null,null);
        } else if (rules.get(0).size()==3) {
            this.head.left = new Node(rules.get(0).get(1),null,null,null);
            this.head.mid = new Node(rules.get(0).get(2),null,null,null);
        } else if (rules.get(0).size()==2) {
            this.head.left = new Node(rules.get(0).get(1),null,null,null);
        }
        for(int i=1;i<rules.size();i++) {

            Node newNode = new Node(rules.get(i).get(0),null,null,null);
            if (rules.get(i).size()==4) {
                newNode.left = new Node(rules.get(i).get(1),null,null,null);
                newNode.mid = new Node(rules.get(i).get(2),null,null,null);
                newNode.right = new Node(rules.get(i).get(3),null,null,null);
            } else if (rules.get(i).size()==3) {
                newNode.left = new Node(rules.get(i).get(1),null,null,null);
                newNode.mid = new Node(rules.get(i).get(2),null,null,null);
            } else if (rules.get(i).size()==2) {
                newNode.left = new Node(rules.get(i).get(1),null,null,null);
            }
            nodePlace(this.head,newNode);
        }

    }

    public boolean nodePlace(Node head ,Node newNode) {
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


        if (head.left != null) {
            if (head.left.value == newNode.value && head.left.left==null && !actualTerminals.contains(newNode.value) ) {
                head.left = newNode;
                return true;
            }
        }
        if (head.mid != null) {
            if (head.mid.value == newNode.value && head.mid.left==null && !actualTerminals.contains(newNode.value)) {
                head.mid = newNode;
                return true;
            }
        }
        if (head.right != null) {
            if (head.right.value == newNode.value && head.right.left==null && !actualTerminals.contains(newNode.value)) {
                head.right = newNode;
                return true;
            }
        }
        if (head.left != null) {
            boolean left = nodePlace(head.left, newNode);
            if (left) {
                return left;
            } else {
                if (head.mid != null) {
                    boolean mid = nodePlace(head.mid, newNode);
                    if (mid) {
                        return mid;
                    } else {
                        if (head.right!=null) {
                            return nodePlace(head.right, newNode);
                        }
                    }
                }
            }
        }
        return false;

    }

    public static String printTree(Node head){
        if (head==null) {
            return "";
        } else {
            return head.toString() + "(" + printTree(head.left) + printTree(head.mid) + printTree(head.right) + ")";
        }
    }
}
