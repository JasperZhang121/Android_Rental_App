package au.edu.anu.cecs.linkhome;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import au.edu.anu.cecs.linkhome.Tokenizer.AndExp;
import au.edu.anu.cecs.linkhome.Tokenizer.EqualExp;
import au.edu.anu.cecs.linkhome.Tokenizer.Exp;
import au.edu.anu.cecs.linkhome.Tokenizer.Less;
import au.edu.anu.cecs.linkhome.Tokenizer.Letter;
import au.edu.anu.cecs.linkhome.Tokenizer.More;
import au.edu.anu.cecs.linkhome.Tokenizer.Parser;
import au.edu.anu.cecs.linkhome.Tokenizer.Tokenizer;

public class ParserTest {

    private static Tokenizer tokenizer;

    private static final String SIMPLE_CASE = "city=Canberra && rent<400";

    private static final String SIMPLE_ASSOCIATIVE_CASE = "Canberra && rent<=500";
    private static final String MID_CASE = "Canberra && Sydney && rent>600 ";
    private static final String COMPLEX_CASE1 = " Canberra && Sydney && Melbourne && rent<=500 || rent>600 ";
    private static final String COMPLEX_CASE2 = "Canberra && Sydney && Melbourne && rent>=200 && rent<=500 || rent>600 ";
    private static final String[] testExample = new String[]{"rent<300", "rent>300", "city=Sydney", "city=Melbourne"};


    @Test
    public void testSimpleAdd() {

        Tokenizer mathTokenizer = new Tokenizer(SIMPLE_CASE);
        Parser parser = new Parser(mathTokenizer);
        parser.parseExp();

        ArrayList list = parser.getFinalList();
//        assertTrue(list.get(0)instanceof More);
//        assertEquals(list.get(1),300);
          assertTrue(list.get(0)instanceof EqualExp);
          assertEquals(list.get(1),"Canberra");
          assertTrue(list.get(2)instanceof AndExp);
          assertTrue(list.get(3)instanceof Less);
          assertEquals(list.get(4), 400);
    }

//    @Test(timeout=1000)
//    public void testSimleSub() {
//        Tokenizer mathTokenizer = new Tokenizer(testExample[1]);
//        Exp t1 = new Parser(mathTokenizer).parseExp();
//        assertEquals(1, t1.evaluate());
//    }
//
//    @Test(timeout=1000)
//    public void testSimleMul() {
//        Tokenizer mathTokenizer = new Tokenizer(testExample[2]);
//        Exp t1 = new Parser(mathTokenizer).parseExp();
//        assertEquals(2, t1.evaluate());
//    }
//
//    @Test(timeout=1000)
//    public void testSimleDiv() {
//        Tokenizer mathTokenizer = new Tokenizer(testExample[3]);
//        Exp t1 = new Parser(mathTokenizer).parseExp();
//        assertEquals(2, t1.evaluate());
//    }
//
//    @Test(timeout=1000)
//    public void testSimpleCase(){
//        tokenizer = new Tokenizer(SIMPLE_CASE);
//        try{
//            Exp exp = new Parser(tokenizer).parseExp();
//            assertEquals("incorrect display format", "(1 + 2)", exp.show());
//            assertEquals("incorrect evaluate value", 3, exp.evaluate());
//        }catch (Exception e){
//            fail(e.getMessage());
//        }
//    }
//
//    @Test(timeout=1000)
//    public void testSimpleAssociativeCase(){
//        tokenizer = new Tokenizer(SIMPLE_ASSOCIATIVE_CASE);
//        try{
//            Exp exp = new Parser(tokenizer).parseExp();
//            assertEquals("incorrect display format", "(5 + (2 + (9 + 8)))", exp.show());
//            assertEquals("incorrect evaluate value", 24, exp.evaluate());
//        }catch (Exception e){
//            fail(e.getMessage());
//        }
//    }
//
//    @Test(timeout=1000)
//    public void testMidCase(){
//        tokenizer = new Tokenizer(MID_CASE);
//        try{
//            Exp exp = new Parser(tokenizer).parseExp();
//            assertEquals("incorrect display format", "((12 * 5) - 3)", exp.show());
//            assertEquals("incorrect evaluate value", 57, exp.evaluate());
//        }catch (Exception e){
//            fail(e.getMessage());
//        }
//    }
//
//    @Test(timeout=1000)
//    public void testComplexCase1() {
//        tokenizer = new Tokenizer(COMPLEX_CASE1);
//        try{
//            Exp exp = new Parser(tokenizer).parseExp();
//            assertEquals("incorrect display format","(((10 - 2) * (10 / 2)) + 1)", exp.show());
//            assertEquals("incorrect evaluate value", 41, exp.evaluate());
//        }catch (Exception e){
//            fail(e.getMessage());
//        }
//    }
//
//    @Test(timeout=1000)
//    public void testComplexCase2() {
//        tokenizer = new Tokenizer(COMPLEX_CASE2);
//        try{
//            Exp exp = new Parser(tokenizer).parseExp();
//            assertEquals("incorrect display format","(((4 + 5) / 3)!)", exp.show());
//            assertEquals("incorrect evaluate value", 6, exp.evaluate());
//        }catch (Exception e){
//            fail(e.getMessage());
//        }
//    }
//
//    @Test(timeout=1000)
//    public void testIllegalProductionException() {
//        // Provide a series of tokens that should invoke this exception
//        assertThrows(Parser.IllegalProductionException.class, () -> {
//            tokenizer = new Tokenizer("++");
//            Exp exp = new Parser(tokenizer).parseExp();
//        });
//
//        assertThrows(Parser.IllegalProductionException.class, () -> {
//            tokenizer = new Tokenizer("-+");
//            Exp exp = new Parser(tokenizer).parseExp();
//        });
//
//        assertThrows(Parser.IllegalProductionException.class, () -> {
//            tokenizer = new Tokenizer("1 + -");
//            Exp exp = new Parser(tokenizer).parseExp();
//        });
//
//        assertThrows(Parser.IllegalProductionException.class, () -> {
//            tokenizer = new Tokenizer("1 5");
//            Exp exp = new Parser(tokenizer).parseExp();
//        });
//
//        assertThrows(Parser.IllegalProductionException.class, () -> {
//            tokenizer = new Tokenizer("(");
//            Exp exp = new Parser(tokenizer).parseExp();
//        });
//
//        assertThrows(Parser.IllegalProductionException.class, () -> {
//            tokenizer = new Tokenizer("(1+2");
//            Exp exp = new Parser(tokenizer).parseExp();
//        });
//
//        assertThrows(Parser.IllegalProductionException.class, () -> {
//            tokenizer = new Tokenizer("1+2)");
//            Exp exp = new Parser(tokenizer).parseExp();
//        });
//
//        assertThrows(Parser.IllegalProductionException.class, () -> {
//            tokenizer = new Tokenizer("1-2)");
//            Exp exp = new Parser(tokenizer).parseExp();
//        });
//
//        assertThrows(Parser.IllegalProductionException.class, () -> {
//            tokenizer = new Tokenizer("1/2)");
//            Exp exp = new Parser(tokenizer).parseExp();
//        });
//
//        assertThrows(Parser.IllegalProductionException.class, () -> {
//            tokenizer = new Tokenizer("1*2)");
//            Exp exp = new Parser(tokenizer).parseExp();
//        });
//
//        assertThrows(Parser.IllegalProductionException.class, () -> {
//            tokenizer = new Tokenizer("((1+2)/2");
//            Exp exp = new Parser(tokenizer).parseExp();
//        });
//
//        assertThrows(Parser.IllegalProductionException.class, () -> {
//            tokenizer = new Tokenizer("5 / )");
//            Exp exp = new Parser(tokenizer).parseExp();
//        });
//
//        assertThrows(Parser.IllegalProductionException.class, () -> {
//            tokenizer = new Tokenizer("5 5 2 + )");
//            Exp exp = new Parser(tokenizer).parseExp();
//        });
//
//        assertThrows(Parser.IllegalProductionException.class, () -> {
//            tokenizer = new Tokenizer("5 + !");
//            Exp exp = new Parser(tokenizer).parseExp();
//        });
//
//        assertThrows(Parser.IllegalProductionException.class, () -> {
//            tokenizer = new Tokenizer("!3");
//            Exp exp = new Parser(tokenizer).parseExp();
//        });
//
//        assertThrows(Parser.IllegalProductionException.class, () -> {
//            tokenizer = new Tokenizer("10! !");
//            Exp exp = new Parser(tokenizer).parseExp();
//        });
//    }
}

