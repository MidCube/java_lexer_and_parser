package main.java.cig23.cam.ac.uk.SummerCompiler;

public class Token {
        public final Terminals t;
        public final String c;
        public Token(Terminals t, String c) {
            this.t = t;
            this.c = c;
        }
        public String toString() {
            if(t == Terminals.Number) {
                return "Float<" + c + ">";
            }
            return t.toString();
        }
}
