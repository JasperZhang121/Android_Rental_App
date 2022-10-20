package au.edu.anu.cecs.linkhome;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

import au.edu.anu.cecs.linkhome.tokenizer.Token;
import au.edu.anu.cecs.linkhome.tokenizer.expressions.AndExp;
import au.edu.anu.cecs.linkhome.tokenizer.expressions.EqualExp;
import au.edu.anu.cecs.linkhome.tokenizer.expressions.LessExp;
import au.edu.anu.cecs.linkhome.tokenizer.expressions.MoreExp;
import au.edu.anu.cecs.linkhome.tokenizer.expressions.OrExp;
import au.edu.anu.cecs.linkhome.tokenizer.Parser;
import au.edu.anu.cecs.linkhome.tokenizer.Tokenizer;

/**
 * @author Devanshi Dhall, Hao Zhang
 */
public class ParserTest {

    private static final String SIMPLE_CASE_1 = "city=Canberra || rent<400";
    private static final String SIMPLE_CASE_2 = "city=Canberra && rent>800";
    private static final String[] testExampleRent = new String[]{"rent<300", "rent>400"};
    private static final String[] testExampleCity = new String[]{"city=Sydney", "city=Melbourne"};

    @Test
    public void testIllegalTokenException() {

        assertThrows(Token.IllegalTokenException.class, () -> {
            Tokenizer mathTokenizer = new Tokenizer("--");
            Parser parser = new Parser(mathTokenizer);
            parser.parseExp();
        });

        assertThrows(Token.IllegalTokenException.class, () -> {
            Tokenizer mathTokenizer = new Tokenizer("//");
            Parser parser = new Parser(mathTokenizer);
            parser.parseExp();
        });

        assertThrows(Token.IllegalTokenException.class, () -> {
            Tokenizer mathTokenizer = new Tokenizer("**");
            Parser parser = new Parser(mathTokenizer);
            parser.parseExp();
        });

        assertThrows(Token.IllegalTokenException.class, () -> {
            Tokenizer mathTokenizer = new Tokenizer("!!");
            Parser parser = new Parser(mathTokenizer);
            parser.parseExp();
        });

        assertThrows(Token.IllegalTokenException.class, () -> {
            Tokenizer mathTokenizer = new Tokenizer("|&");
            Parser parser = new Parser(mathTokenizer);
            parser.parseExp();
        });
    }


    @Test
    public void testSimpleCase1() {
        Tokenizer mathTokenizer = new Tokenizer(SIMPLE_CASE_1);
        Parser parser = new Parser(mathTokenizer);
        parser.parseExp();
        ArrayList<Object> list = parser.getFinalList();
        System.out.println(list);
        assertTrue(list.get(0)instanceof EqualExp);
        assertEquals(list.get(1),"Canberra");
        assertTrue(list.get(2)instanceof OrExp);
        assertTrue(list.get(3)instanceof LessExp);
        assertEquals(list.get(4), 400);
    }

    @Test
    public void testSimpleCase2() {
        Tokenizer mathTokenizer = new Tokenizer(SIMPLE_CASE_2);
        Parser parser = new Parser(mathTokenizer);
        parser.parseExp();
        ArrayList<Object> list = parser.getFinalList();
        assertTrue(list.get(0)instanceof EqualExp);
        assertEquals(list.get(1),"Canberra");
        assertTrue(list.get(2)instanceof AndExp);
        assertTrue(list.get(3)instanceof MoreExp);
        assertEquals(list.get(4), 800);
    }

    @Test
    public void test_only_rent_less() {
        Tokenizer mathTokenizer = new Tokenizer(testExampleRent[0]);
        Parser parser = new Parser(mathTokenizer);
        parser.parseExp();
        ArrayList<Object> list = parser.getFinalList();
        assertTrue(list.get(0)instanceof LessExp);
        assertEquals(list.get(1), 300);
    }

    @Test
    public void test_only_rent_more() {
        Tokenizer mathTokenizer = new Tokenizer(testExampleRent[1]);
        Parser parser = new Parser(mathTokenizer);
        parser.parseExp();
        ArrayList<Object> list = parser.getFinalList();
        assertTrue(list.get(0)instanceof MoreExp);
        assertEquals(list.get(1), 400);
    }

    @Test
    public void test_only_city_Sydney() {
        Tokenizer mathTokenizer = new Tokenizer(testExampleCity[0]);
        Parser parser = new Parser(mathTokenizer);
        parser.parseExp();
        ArrayList<Object> list = parser.getFinalList();
        assertTrue(list.get(0) instanceof EqualExp);
        assertEquals(list.get(1),"Sydney");
    }

    @Test
    public void test_only_city_Melbourne() {
        Tokenizer mathTokenizer = new Tokenizer(testExampleCity[1]);
        Parser parser = new Parser(mathTokenizer);
        parser.parseExp();
        ArrayList<Object> list = parser.getFinalList();
        assertTrue(list.get(0) instanceof EqualExp);
        assertEquals(list.get(1),"Melbourne");
    }
}

