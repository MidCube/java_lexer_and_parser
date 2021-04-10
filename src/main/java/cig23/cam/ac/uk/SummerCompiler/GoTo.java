package main.java.cig23.cam.ac.uk.SummerCompiler;

import java.util.ArrayList;

public class GoTo {
    public static ArrayList<Item> GoTo(ArrayList<Item> set, Terminals X){
        ArrayList<ArrayList<Terminals>> G = ProductionRules.generateRules();
        ArrayList<Item> relevantItems = new ArrayList<>();
        for (Item item: set) {
            Terminals next;
            if (item.DotPlace < G.get(item.ProductionRule).size()-1) {
                next = G.get(item.ProductionRule).get(item.DotPlace + 1);
            } else {
                continue;
            }
            if (next == X) {
                Item newItem = new Item(item.ProductionRule,item.DotPlace+1);
                relevantItems.add(newItem);
            }
        }
        return Closure.closure(relevantItems);

    }
}
