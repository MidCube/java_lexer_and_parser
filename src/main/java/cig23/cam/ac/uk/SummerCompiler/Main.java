package main.java.cig23.cam.ac.uk.SummerCompiler;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Main {

    public static class LexingException extends Exception {
        public LexingException(String message) {
            super(message);
        }
    }

    public static class ParsingException extends Exception {
        public ParsingException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {

        Scanner maths = new Scanner(System.in);
        System.out.println("Enter calculation");

        String calc = maths.nextLine();
        try {
            List<Token> tokens = Lexer.lex(calc);
            for(Token t : tokens) {
                System.out.println(t);
            }
            try {
                Parser.parse(tokens);
            } catch (ParsingException ex) {
                System.err.print(ex);
            }

        } catch (LexingException ex) {
            System.err.print(ex);
        }



    }
}
