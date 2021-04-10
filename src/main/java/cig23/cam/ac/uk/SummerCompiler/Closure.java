package main.java.cig23.cam.ac.uk.SummerCompiler;

import java.util.ArrayList;
import java.util.Iterator;

public class Closure {
    public static ArrayList<Item> closure(ArrayList<Item> set){
        ArrayList<ArrayList<Terminals>> G = ProductionRules.generateRules();
        ArrayList<Item> J = set;
        Boolean noneAdded = false;
        for (; !noneAdded ;) {
            noneAdded = true;
            int originalLength = J.size();
            for (int j = 0; j <originalLength; j++) {
                Item item = J.get(j);
                Terminals next;
                if (item.DotPlace < G.get(item.ProductionRule).size()-1) {
                    next = G.get(item.ProductionRule).get(item.DotPlace+1);
                } else {
                    continue;
                }

                ArrayList<Integer> possibleProductions = new ArrayList<>();
                for (int i=0;i<G.size();i++) {
                    if (G.get(i).get(0) == next) {
                        possibleProductions.add(i);
                    }
                }
                for (int production:possibleProductions) {
                    Item here = new Item(production,0);
                    if (!(J.contains(here))) {
                        J.add(here);
                        noneAdded = false;
                    }
                }

            }
        }

        return J;
    }
}
