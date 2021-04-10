package main.java.cig23.cam.ac.uk.SummerCompiler;

import main.java.cig23.cam.ac.uk.SummerCompiler.Lexer;
import main.java.cig23.cam.ac.uk.SummerCompiler.Main;
import main.java.cig23.cam.ac.uk.SummerCompiler.Terminals;
import main.java.cig23.cam.ac.uk.SummerCompiler.Token;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class parserTest {
    @Test
    public void parser_acceptsStandardInputs() {
        //6+5
        List<Token> calc1 = new ArrayList<>();
        calc1.add(new Token(Terminals.Number,"6"));
        calc1.add(new Token(Terminals.Plus,"+"));
        calc1.add(new Token(Terminals.Number,"5"));
        Boolean token1 = null;
        //6+5-5
        List<Token> calc2 = new ArrayList<>();
        calc2.add(new Token(Terminals.Number,"6"));
        calc2.add(new Token(Terminals.Plus,"+"));
        calc2.add(new Token(Terminals.Number,"5"));
        calc2.add(new Token(Terminals.Minus,"-"));
        calc2.add(new Token(Terminals.Number,"5"));
        Boolean token2 = null;
        //cos3
        List<Token> calc3 = new ArrayList<>();
        calc3.add(new Token(Terminals.Cos,"cos"));
        calc3.add(new Token(Terminals.Number,"3"));

        Boolean token3 = null;
        //cos(3)!
        List<Token> calc4 = new ArrayList<>();
        calc4.add(new Token(Terminals.Cos,"cos"));
        calc4.add(new Token(Terminals.LBracket,"("));
        calc4.add(new Token(Terminals.Number,"3"));
        calc4.add(new Token(Terminals.Rbracket,")"));
        calc4.add(new Token(Terminals.Bang,"!"));
        Boolean token4 = null;

        try {
            token1 = Parser.parse(calc1);
            token2 = Parser.parse(calc2);
            token3 = Parser.parse(calc3);
            token4 = Parser.parse(calc4);


        } catch (Main.ParsingException e) {
            e.printStackTrace();
        }
        assertEquals(true,token1);
        assertEquals(true,token2);
        assertEquals(true,token3);
        assertEquals(true,token4);

    }

    @Test(expected = Main.ParsingException.class)
    public void parse_stops_invalid_calc1() throws Main.ParsingException {
        //invalid because just operator
        List<Token> calc1 = new ArrayList<>();

        calc1.add(new Token(Terminals.Plus,"+"));

        Parser.parse(calc1);
    }

    @Test(expected = Main.ParsingException.class)
    public void parse_stops_invalid_calc2() throws Main.ParsingException {
        //invalid because unequal number of brackets
        List<Token> calc1 = new ArrayList<>();

        calc1.add(new Token(Terminals.LBracket,"("));
        calc1.add(new Token(Terminals.Number,"6"));
        calc1.add(new Token(Terminals.Plus,"+"));
        calc1.add(new Token(Terminals.Number,"5"));

        Parser.parse(calc1);
    }

    @Test(expected = Main.ParsingException.class)
    public void parse_stops_invalid_calc3() throws Main.ParsingException {
        //invalid because cos can never follow a number directly
        List<Token> calc1 = new ArrayList<>();

        calc1.add(new Token(Terminals.Number,"3"));
        calc1.add(new Token(Terminals.Cos,"cos"));
        calc1.add(new Token(Terminals.Number,"5"));

        Parser.parse(calc1);
    }

    //Everything from LexerTest that the lexer will accept but will be rejected at parsing

    @Test(expected = Main.ParsingException.class)
    public void parse_stops_invalid_calc4() throws Main.ParsingException {

        //!cos7
        List<Token> calc1 = new ArrayList<>();

        calc1.add(new Token(Terminals.Bang,"!"));
        calc1.add(new Token(Terminals.Cos,"cos"));
        calc1.add(new Token(Terminals.Number,"7"));

        Parser.parse(calc1);
    }

    @Test(expected = Main.ParsingException.class)
    public void parse_stops_invalid_calc5() throws Main.ParsingException {

        //6++3
        List<Token> calc1 = new ArrayList<>();

        calc1.add(new Token(Terminals.Number,"6"));
        calc1.add(new Token(Terminals.Plus,"+"));
        calc1.add(new Token(Terminals.Plus,"+"));
        calc1.add(new Token(Terminals.Number,"3"));

        Parser.parse(calc1);
    }

    @Test(expected = Main.ParsingException.class)
    public void parse_stops_invalid_calc6() throws Main.ParsingException {

        //5-(9+2
        List<Token> calc1 = new ArrayList<>();

        calc1.add(new Token(Terminals.Number,"5"));
        calc1.add(new Token(Terminals.Minus,"-"));
        calc1.add(new Token(Terminals.LBracket,"("));
        calc1.add(new Token(Terminals.Number,"9"));
        calc1.add(new Token(Terminals.Plus,"+"));
        calc1.add(new Token(Terminals.Number,"2"));

        Parser.parse(calc1);
    }

    @Test(expected = Main.ParsingException.class)
    public void parse_stops_invalid_calc7() throws Main.ParsingException {

        //cos!
        List<Token> calc1 = new ArrayList<>();

        calc1.add(new Token(Terminals.Cos,"cos"));
        calc1.add(new Token(Terminals.Bang,"!"));

        Parser.parse(calc1);
    }

    @Test(expected = Main.ParsingException.class)
    public void parse_stops_invalid_calc8() throws Main.ParsingException {

        //()
        List<Token> calc1 = new ArrayList<>();

        calc1.add(new Token(Terminals.LBracket,"("));
        calc1.add(new Token(Terminals.Rbracket,")"));


        Parser.parse(calc1);
    }

    @Test(expected = Main.ParsingException.class)
    public void parse_stops_invalid_calc9() throws Main.ParsingException {

        //345**-45
        List<Token> calc1 = new ArrayList<>();

        calc1.add(new Token(Terminals.Number,"345"));
        calc1.add(new Token(Terminals.Mult,"*"));
        calc1.add(new Token(Terminals.Mult,"*"));
        calc1.add(new Token(Terminals.Number,"-45"));

        Parser.parse(calc1);
    }

    @Test(expected = Main.ParsingException.class)
    public void parse_stops_invalid_calc10() throws Main.ParsingException {

        //-7576cos+4
        List<Token> calc1 = new ArrayList<>();

        calc1.add(new Token(Terminals.Number,"-7576"));
        calc1.add(new Token(Terminals.Cos,"cos"));
        calc1.add(new Token(Terminals.Plus,"+"));
        calc1.add(new Token(Terminals.Number,"4"));

        Parser.parse(calc1);
    }





}
