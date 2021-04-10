package main.java.cig23.cam.ac.uk.SummerCompiler;

import java.util.ArrayList;

public class CanonicalCollection {
    public static ArrayList<ArrayList<Item>> allStates() {
        ArrayList<ArrayList<Terminals>> G = ProductionRules.generateRules();
        ArrayList<ArrayList<Item>> C = new ArrayList<>();
        Item startItem = new Item(0,0);
        ArrayList<Item> startList = new ArrayList<>();
        startList.add(startItem);
        C.add(Closure.closure(startList));
        Boolean noNewSets = false;
        for (;!noNewSets ;) {
            noNewSets=true;
            int myLength = C.size();
            for(int n=0; n<myLength;n++) {
                ArrayList<Item> set = C.get(n);
                for (Terminals X:Terminals.values()) {
                    ArrayList<Item> newSet = GoTo.GoTo(set,X);
                    if (!(newSet.isEmpty()) && !(C.contains(newSet))) {
                        C.add(newSet);
                        noNewSets=false;
                    }
                }

            }
        }
        return C;
    }
}
