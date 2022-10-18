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
    private static final String[] testExample = new String[]{"rent<300", "rent>300", "city=Sydney", "city=Melbourne"};
    //private static final String SIMPLE_ASSOCIATIVE_CASE = "Canberra && rent<=500";
    //private static final String COMPLEX_CASE1 = " Canberra && Sydney && Melbourne && rent<=500 || rent>600 ";
    //private static final String COMPLEX_CASE2 = "Canberra && Sydney && Melbourne && rent>=200 && rent<=500 || rent>600 ";



    @Test
    public void testSimpleCase() {

        Tokenizer mathTokenizer = new Tokenizer(SIMPLE_CASE);
        Parser parser = new Parser(mathTokenizer);
        parser.parseExp();

        ArrayList list = parser.getFinalList();
          assertTrue(list.get(0)instanceof EqualExp);
          assertEquals(list.get(1),"Canberra");
          assertTrue(list.get(2)instanceof AndExp);
          assertTrue(list.get(3)instanceof Less);
          assertEquals(list.get(4), 400);
    }

    @Test
    public void test_only_rent_less() {
        Tokenizer mathTokenizer = new Tokenizer(testExample[0]);
        Parser parser = new Parser(mathTokenizer);
        parser.parseExp();

        ArrayList list = parser.getFinalList();
        assertTrue(list.get(0)instanceof Less);
        assertEquals(list.get(1), 300);
    }

    @Test
    public void test_only_rent_more() {
        Tokenizer mathTokenizer = new Tokenizer(testExample[1]);
        Parser parser = new Parser(mathTokenizer);
        parser.parseExp();

        ArrayList list = parser.getFinalList();
        assertTrue(list.get(0)instanceof More);
        assertEquals(list.get(1), 300);
    }

    @Test
    public void test_only_city_Sydney() {
        Tokenizer mathTokenizer = new Tokenizer(testExample[2]);
        Parser parser = new Parser(mathTokenizer);
        parser.parseExp();
        ArrayList list = parser.getFinalList();
        assertTrue(list.get(0) instanceof EqualExp);
        assertEquals(list.get(1),"Sydney");
    }

    @Test
    public void test_only_city_Melbourne() {
        Tokenizer mathTokenizer = new Tokenizer(testExample[3]);
        Parser parser = new Parser(mathTokenizer);
        parser.parseExp();
        ArrayList list = parser.getFinalList();
        assertTrue(list.get(0) instanceof EqualExp);
        assertEquals(list.get(1),"Melbourne");
    }
}

