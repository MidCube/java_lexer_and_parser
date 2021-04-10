package main.java.cig23.cam.ac.uk.SummerCompiler;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    public static List<Token> lex(String input) throws Main.LexingException {
        List<Token> result = new ArrayList<Token>();
        for(int i = 0; i < input.length(); ) {

            switch(input.charAt(i)) {
                case '(':
                    result.add(new Token(Terminals.LBracket, "("));
                    i++;
                    break;
                case ')':
                    result.add(new Token(Terminals.Rbracket, ")"));
                    i++;
                    break;
                case '+':
                    result.add(new Token(Terminals.Plus, "+"));
                    i++;
                    break;
                case '*':
                    result.add(new Token(Terminals.Mult, "*"));
                    i++;
                    break;
                case '!':
                    result.add(new Token(Terminals.Bang, "!"));
                    i++;
                    break;
                case 'c':
                    if (i > input.length()-3) {
                        throw new Main.LexingException("You started writing cos but didn't finish");
                    }


                    if (input.charAt(i+1) == 'o' && input.charAt(i+2)=='s'){

                        result.add(new Token(Terminals.Cos, "cos"));
                        i+=3;
                    } else {
                        //error, invalid characters. Did you mean 'cos'
                        throw new Main.LexingException("Invalid characters found in the calculation. Did you mean cos?");
                    }
                    break;
                case '-':
                    if (i==0) {
                        if (input.length()>1) {
                            String number = getFloat.getFloat(input, i);
                            i += number.length();
                            result.add(new Token(Terminals.Number, number));
                        } else {
                            result.add(new Token(Terminals.Minus, "-"));
                            i++;
                        }

                    } else if (input.charAt(i-1) == '+' || input.charAt(i-1) == '-' || input.charAt(i-1) == '*' || input.charAt(i-1) == 's') {
                        String number = getFloat.getFloat(input, i);
                        i += number.length();
                        result.add(new Token(Terminals.Number, number));

                    } else {
                        result.add(new Token(Terminals.Minus, "-"));
                        i++;
                    }
                    break;
                default:
                    if(Character.isWhitespace(input.charAt(i))) {
                        i++;
                    } else {
                        String number = getFloat.getFloat(input, i);
                        i += number.length();
                        result.add(new Token(Terminals.Number, number));
                    }
                    break;
            }
        }
        return result;
    }
}
