/*
 * Copyright 2019 Andrew Rice <acr31@cam.ac.uk>, C.I. Griffiths
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
public class lexerTest {

  @Test
  public void lexer_handles_all_float_types() {
    //int
    String float1 = "7";
    List<Token> token1 = null;
    //float
    String float2 = "8643.354";
    List<Token> token2 = null;
    //leading decimal
    String float3 = ".8765";
    List<Token> token3 = null;
    //trailing decimal
    String float4 = "4456.";
    List<Token> token4 = null;
    //leading 0.
    String float5 = "0.35345";
    List<Token> token5 = null;
    //leading 0 without decimal
    String float6 = "09887";
    List<Token> token6 = null;
    //followed by something else
    String float7 = "98.76+6";
    List<Token> token7 = null;
    //0 on its own as last thing
    String float8 = "9+0";
    List<Token> token8 = null;
    try {
      token1 = Lexer.lex(float1);
      token2 = Lexer.lex(float2);
      token3 = Lexer.lex(float3);
      token4 = Lexer.lex(float4);
      token5 = Lexer.lex(float5);
      token6 = Lexer.lex(float6);
      token7 = Lexer.lex(float7);
      token8 = Lexer.lex(float8);
    } catch (Main.LexingException e) {
      e.printStackTrace();
    }
    assertEquals(token1.get(0).t,Terminals.Number);
    assertEquals(token2.get(0).t,Terminals.Number);
    assertEquals(token3.get(0).t,Terminals.Number);
    assertEquals(token4.get(0).t,Terminals.Number);
    assertEquals(token5.get(0).t,Terminals.Number);
    assertEquals(token6.size(),2);
    assertEquals(token7.size(),3);
    assertEquals(token8.get(token8.size()-1).t,Terminals.Number);

  }

  @Test
  public void lexer_accepts_unusualCasesThatWontParse() {

    String float1 = "!cos7";
    List<Token> token1 = null;
    //minus
    String float2 = "6++3";
    List<Token> token2 = null;
    //multiply
    String float3 = "5-(9+2";
    List<Token> token3 = null;
    //cos
    String float4 = "cos!";
    List<Token> token4 = null;
    //bang!
    String float5 = "()";
    List<Token> token5 = null;
    //left bracket
    String float6 = "345**-45";
    List<Token> token6 = null;
    //right bracket
    String float7 = "-7576cos+4";
    List<Token> token7 = null;
    try {
      token1 = Lexer.lex(float1);
      token2 = Lexer.lex(float2);
      token3 = Lexer.lex(float3);
      token4 = Lexer.lex(float4);
      token5 = Lexer.lex(float5);
      token6 = Lexer.lex(float6);
      token7 = Lexer.lex(float7);
    } catch (Main.LexingException e) {
      e.printStackTrace();
    }
    assertNotEquals( 0, token1.size());
    assertNotEquals( 0, token2.size());
    assertNotEquals( 0, token3.size());
    assertNotEquals( 0, token4.size());
    assertNotEquals( 0, token5.size());
    assertNotEquals( 0, token6.size());
    assertNotEquals( 0, token7.size());




  }

  @Test
  public void lexer_handles_all_other_types() {
    //plus
    String float1 = "+";
    List<Token> token1 = null;
    //minus
    String float2 = "-";
    List<Token> token2 = null;
    //multiply
    String float3 = "*";
    List<Token> token3 = null;
    //cos
    String float4 = "cos";
    List<Token> token4 = null;
    //bang!
    String float5 = "!";
    List<Token> token5 = null;
    //left bracket
    String float6 = "(";
    List<Token> token6 = null;
    //right bracket
    String float7 = ")";
    List<Token> token7 = null;
    //right bracket but with whitespace
    //right bracket
    String float8 = " )";
    List<Token> token8 = null;
    try {
      token1 = Lexer.lex(float1);
      token2 = Lexer.lex(float2);
      token3 = Lexer.lex(float3);
      token4 = Lexer.lex(float4);
      token5 = Lexer.lex(float5);
      token6 = Lexer.lex(float6);
      token7 = Lexer.lex(float7);
      token8 = Lexer.lex(float8);
    } catch (Main.LexingException e) {
      e.printStackTrace();
    }
    assertEquals(Terminals.Plus,token1.get(0).t);
    assertEquals(Terminals.Minus,token2.get(0).t);
    assertEquals(Terminals.Mult,token3.get(0).t);
    assertEquals(Terminals.Cos,token4.get(0).t);
    assertEquals(Terminals.Bang,token5.get(0).t);
    assertEquals(Terminals.LBracket,token6.get(0).t);
    assertEquals(Terminals.Rbracket,token7.get(0).t);
    assertEquals(Terminals.Rbracket,token8.get(0).t);

  }

  @Test(expected = Main.LexingException.class)
  public void lex_stops_invalid_input1() throws Main.LexingException {
    //invalid because of two decimals
    String test = "642435.2543.2345";
    Lexer.lex(test);
  }

  @Test(expected = Main.LexingException.class)
  public void lex_stops_invalid_input2() throws Main.LexingException {
    //invalid because of letters
    String test = "adfhjadsf";
    Lexer.lex(test);
  }

  @Test(expected = Main.LexingException.class)
  public void lex_stops_invalid_input3() throws Main.LexingException {
    //invalid because of symbols not in grammar
    String test = "5/6";
    Lexer.lex(test);
  }

  @Test(expected = Main.LexingException.class)
  public void lex_stops_invalid_input4() throws Main.LexingException {
    //invalid because of cos wasn't finished
    String test = "co";
    Lexer.lex(test);
  }

  @Test(expected = Main.LexingException.class)
  public void lex_stops_invalid_input5() throws Main.LexingException {
    //invalid because of cos was changed
    String test = "cod";
    Lexer.lex(test);
  }

  @Test(expected = Main.LexingException.class)
  public void lex_stops_invalid_input6() throws Main.LexingException {
    //invalid because of multiple +- ect with - as second one
    String test = "+--";
    Lexer.lex(test);
  }

  @Test(expected = Main.LexingException.class)
  public void lex_stops_invalid_input7() throws Main.LexingException {
    //invalid because of multiple +- ect
    String test = "++--";
    Lexer.lex(test);
  }

}
