package au.edu.anu.cecs.linkhome.Tokenizer;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.lang.Object;

/**
 * Note: You will need to have completed task 1 to complete this task.
 *
 * Welcome to task 2. In this task your job is to implement a simple parser.
 * It should be able to parser the following grammar rule:
 * <exp>    ::=  <term> | <term> + <exp> | <term> - <exp>
 * <term>   ::=  <factor> | <factor> * <term> | <factor> / <term>
 * <factor> ::=  <coefficient> | <coefficient> !
 * <coefficient> ::= <unsigned integer> | ( <exp> )
 *
 * Here are some rules you must abide by for this task:
 * 1. You may NOT modify any other classes in this task 2 package.
 * 2. You may create additional fields or methods to finish you implementation within this class.
 * <p>
 * Parsing, within the context of this lab, is the process of taking a bunch of tokens and
 * evaluating them. You will not need to 'evaluate' them within this class, instead, just
 * return an expression which can be evaluated.
 */
public class Parser {
    /**
     * The following exception should be thrown if the parse is faced with series of tokens that do not
     * correlate with any possible production rule.
     */
    public static class IllegalProductionException extends IllegalArgumentException {
        public IllegalProductionException(String errorMessage) {
            super(errorMessage);
        }
    }

    ArrayList<Object> finalList = new ArrayList<>();

    // The tokenizer (class field) this parser will use.
    Tokenizer tokenizer;

    /**
     * Parser class constructor
     * Simply sets the tokenizer field.
     * **** please do not modify this part ****
     */
    public Parser(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    /**
     * To help you both test and understand what this parser is doing, we have included a main method
     * which you can run. Once running, provide a mathematical string to the terminal and you will
     * receive back the result of your parsing.
     */
    public static void main(String[] args) {
        // Create a scanner to get the user's input.
        Scanner scanner = new Scanner(System.in);

        /*
         Continue to get the user's input until they exit.
         To exit press: Control + D or providing the string 'q'
         Example input you can try: ((1 + 2) * 5)/2
         Note that evaluations will round down to negative infinity (because they are integers).
         */
        System.out.println("Provide a mathematical string to be parsed:");
        while (scanner.hasNext()) {
            String input = scanner.nextLine();

            // Check if 'quit' is provided.
            if (input.equals("q"))
                break;

            // Create an instance of the tokenizer.
            Tokenizer tokenizer = new Tokenizer(input);

            // Print out the expression from the parser.
            Parser parser = new Parser(tokenizer);
            Exp expression = parser.parseExp();
            System.out.println("Parsing: " + expression.show());
            System.out.println("Evaluation: " + expression.evaluate());
        }
    }

    public ArrayList<Object> getFinalList(){return finalList;}



    /**
     * Adheres to the grammar rule:
     * <exp>    ::= <term> || <exp> | <term> && <exp> | <term>
     *
     * @return type: Exp.
     */
    public Exp parseExp() {
        /*
         TODO: Implement parse function for <exp>.
         TODO: Throw an IllegalProductionException if provided with tokens not conforming to the grammar.
         Hint 1: you know that the first item will always be a term (according to the grammar).
         Hint 2: the possible grammar return '<term> + <exp>' correlates with the class (AddExp(term, exp)).
         */
        // ########## YOUR CODE STARTS HERE ##########

        Exp termSol = parseTerm();
        if (this.tokenizer.current() == null) {
            return termSol;

        }

        if(this.tokenizer.hasNext()){
            if(tokenizer.current().equals(Less.class)){
                parseLetter();
            }
            tokenizer.next();

        }
//        if(Objects.equals(this.tokenizer.current().getToken(), "city"))
//        {
//            if(tokenizer.hasNext()){
//                tokenizer.next();
//                if(tokenizer.current().getType() == Token.Type.EQUAL){
//                    parseOperators();
//                }
//            }
//
//        }
//
//        if(Objects.equals(this.tokenizer.current().getToken(), "rent"))
//        {
//            if(tokenizer.hasNext()){
//                tokenizer.next();
//                if(tokenizer.current().getType() == Token.Type.EQUAL){
//                    parseOperators();
//                }
//            }
//
//        }
//        if(this.tokenizer.current().getType() == Token.Type.ADD)
//        {
//            this.tokenizer.next();
//            Exp exp = parseExp();
//            return new AndExp(termSol, exp);
//        }
//        if (this.tokenizer.current().getType() == Token.Type.SUB)
//        {
//            this.tokenizer.next();
//            Exp exp = parseExp();
//            return new SubExp(termSol, exp);
//        }

        // 2 integers cannot be together without any arithmetic operator
        if(this.tokenizer.current().getType()== Token.Type.INT){
            throw new IllegalProductionException("Incorrect");
        }



        return termSol;


        // Change this return (if you want). It is simply a placeholder to prevent an error.
        // ########## YOUR CODE ENDS HERE ##########
    }

    /**
     * Adheres to the grammar rule:
     * <term>   ::=  <city=><letter> | <rent>
     *
     * @return type: Exp.
     */
    public Exp parseTerm() {
        /*
         TODO: Implement parse function for <term>.
         TODO: Throw an IllegalProductionException if provided with tokens not conforming to the grammar.
         Hint: you know that the first item will always be a factor (according to the grammar).
         */
        // ########## YOUR CODE STARTS HERE ##########

        Exp rent = parseRent();
        if (this.tokenizer.hasNext()) {
            if (this.tokenizer.current() == null) {
                return rent;
            }
//            if (this.tokenizer.current().getType() == Token.Type.MUL) {
//                this.tokenizer.next();
//                Exp term = parseTerm();
//                return new MultExp(factor, term);
//            }
//            if (this.tokenizer.current().getType() == Token.Type.DIV) {
//                this.tokenizer.next();
//                Exp term = parseTerm();
//                return new DivExp(factor, term);
//            }
        }
        return rent;

        // Change this return (if you want). It is simply a placeholder to prevent an error.
        // ########## YOUR CODE ENDS HERE ##########
    }

    /**
     * Adheres to the grammar rule:
     * <rents> ::=  <operator> <coefficient>
     *
     * @return type: Exp.
     */
    public Exp parseRent() {
        /*
         TODO: Implement parse function for <factor>.
         TODO: Throw an IllegalProductionException if provided with tokens not conforming to the grammar.
         Hint 1: you can use Integer.parseInt() to convert a string into an integer.
         Fun fact: Integer.parseInt() is using a parser!
         Hint 2: you know that the first item will always be a coefficient (according to the grammar).
         */
        // ########## YOUR CODE STARTS HERE ##########

        Exp coefficient = parseCoefficient();
        Exp operator = parseOperators();
//        this.tokenizer.next();
//        if (this.tokenizer.current()!=null) {
//            if (this.tokenizer.current().getType() == Token.Type.FAC) {
//                this.tokenizer.next();
//                if (tokenizer.current() != null && this.tokenizer.current().getType() == Token.Type.FAC) {
//                    throw new IllegalProductionException("Incorrect");
//                }
//                //return new FacExp(coefficient);
//            }
//        }


        return coefficient;

        // Change this return (if you want). It is simply a placeholder to prevent an error.
        // ########## YOUR CODE ENDS HERE ##########
    }

    /**
     * Adheres to the grammar rule:
     * <operator> ::= < < | > | = >
     *
     * @return type: Exp.
     */
    public Exp parseOperators() {
        /*
         TODO: Implement parse function for <coefficient>.
         TODO: Throw an IllegalProductionException if provided with tokens not conforming to the grammar.
         */
        // ########## YOUR CODE STARTS HERE ##########

        // Check for the corner cases

        if (this.tokenizer.current().getType() == Token.Type.LESS) {
            if(tokenizer.hasNext()){
                tokenizer.next();
                Less less = new Less();
                finalList.add(less);
            }
            return new Less();
        }
        else if (this.tokenizer.current().getType() == Token.Type.MORE) {
            if(tokenizer.hasNext()){
                tokenizer.next();
                More more = new More();
                finalList.add(more);
            }

            return new More();
        }
        else if (this.tokenizer.current().getType() == Token.Type.EQUAL) {
            if(tokenizer.hasNext()){
                tokenizer.next();
                EqualExp equalExp = new EqualExp();
                finalList.add(equalExp);
            }

            return new EqualExp();

        }
        else if (this.tokenizer.current().getType() == Token.Type.AND) {
            if(tokenizer.hasNext()){
                tokenizer.next();
                AndExp andExp = new AndExp();
                finalList.add(andExp);
            }

            return new AndExp();
        }

        else if (this.tokenizer.current().getType() == Token.Type.OR) {
            if(tokenizer.hasNext()){
                tokenizer.next();
                OrExp orExp = new OrExp();
                finalList.add(orExp);
            }

            return new OrExp();
        }

        else{
            throw new IllegalProductionException("Incorrect");
        }


    }

    /**
     * Adheres to the grammar rule:
     * <coefficient> ::= <unsigned integer>
     *
     * @return type: Exp.
     */
    public Exp parseCoefficient() {
        /*
         TODO: Implement parse function for <coefficient>.
         TODO: Throw an IllegalProductionException if provided with tokens not conforming to the grammar.
         */
        // ########## YOUR CODE STARTS HERE ##########
        IntExp result;

        // Check for the corner cases

        if (this.tokenizer.current().getType() == Token.Type.INT) {
            result = new IntExp(Integer.parseInt(this.tokenizer.current().getToken()));
            finalList.add(tokenizer.current().getToken());
            return result;
        }
        else{
            throw new IllegalProductionException("Incorrect");
        }

    }

    /**
     * Adheres to the grammar rule:
     * <letter> ::= <alphabets>
     *
     * @return type: Exp.
     */

    public Exp parseLetter() {
        /*
         TODO: Implement parse function for <coefficient>.
         TODO: Throw an IllegalProductionException if provided with tokens not conforming to the grammar.
         */
        // ########## YOUR CODE STARTS HERE ##########

        // Check for the corner cases

        if(Objects.equals(this.tokenizer.current().getToken(), "city"))
        {
            if(tokenizer.hasNext()){
                tokenizer.next();
                if(tokenizer.current().getType() == Token.Type.EQUAL){
                    parseOperators();
                }
            }

        }

        else if(Objects.equals(this.tokenizer.current().getToken(), "rent"))
        {
            if(tokenizer.hasNext()){
                tokenizer.next();
                if(tokenizer.current().getType() == Token.Type.EQUAL
                        || tokenizer.current().getType() == Token.Type.MORE ||
                        tokenizer.current().getType() == Token.Type.LESS){
                    parseOperators();
                }
            }

        }

        else if(tokenizer.hasNext()){
            tokenizer.next();
            parseOperators();
            finalList.add(tokenizer.current().getToken());
        }

        return new Letter(tokenizer.current().getToken());

    }

}

