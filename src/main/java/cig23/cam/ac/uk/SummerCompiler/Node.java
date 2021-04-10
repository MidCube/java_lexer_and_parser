package main.java.cig23.cam.ac.uk.SummerCompiler;

public class Node {

    public Terminals value;

    public Node left;
    public Node mid;
    public Node right;

    public Node(){
        this.left = null;
        this.right = null;
        this.mid = null;
        this.value = null;
    }

    public Node(Terminals value, Node left, Node mid, Node right){
        this.left = left;
        this.right = right;
        this.mid = mid;
        this.value = value;
    }

    @Override
    public String toString() {
        if (value==null) {
            return "";
        } else {
            return value.toString();
        }

    }
}
