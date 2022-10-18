package au.edu.anu.cecs.linkhome.Tokenizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.lang.Object;

/**
 * Note: You will need to have completed task 1 to complete this task.
 * <p>
 * Welcome to task 2. In this task your job is to implement a simple parser.
 * It should be able to parser the following grammar rule:
 * <exp>    ::=  <term> | <term> + <exp> | <term> - <exp>
 * <term>   ::=  <factor> | <factor> * <term> | <factor> / <term>
 * <factor> ::=  <coefficient> | <coefficient> !
 * <coefficient> ::= <unsigned integer> | ( <exp> )
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
//    public static void main(String[] args) {
//        // Create a scanner to get the user's input.
//        Scanner scanner = new Scanner(System.in);
//
//        /*
//         Continue to get the user's input until they exit.
//         To exit press: Control + D or providing the string 'q'
//         Example input you can try: ((1 + 2) * 5)/2
//         Note that evaluations will round down to negative infinity (because they are integers).
//         */
//        System.out.println("Provide a mathematical string to be parsed:");
//        while (scanner.hasNext()) {
//            String input = scanner.nextLine();
//
//            // Check if 'quit' is provided.
//            if (input.equals("q"))
//                break;
//
//            // Create an instance of the tokenizer.
//            Tokenizer tokenizer = new Tokenizer(input);
//
//            // Print out the expression from the parser.
//            Parser parser = new Parser(tokenizer);
//            Exp expression = parser.parseExp();
//            System.out.println("Parsing: " + expression.show());
//            System.out.println("Evaluation: " + expression.evaluate());
//        }
//    }

    public ArrayList<Object> getFinalList() {
        return finalList;
    }

    /**
     * Adheres to the grammar rule:
     * <exp>    ::= <term> || <exp> | <term> && <exp> | <term>
     *
     * @return type: Exp.
     */
    public Exp parseExp() {

        if (this.tokenizer.current().getType().equals(Token.Type.TEXT)) {
            tokenizer.next();
            return parseOperators();
        }

        if (this.tokenizer.hasNext()) {
            tokenizer.next();
        }
        return null;
    }

    /**
     * Adheres to the grammar rule:
     * <operator> ::= < < | > | = >
     *
     * @return type: Exp.
     */
    public Exp parseOperators() {

        switch (this.tokenizer.current().getType()) {
            case LESS:
                Less less = new Less();
                finalList.add(less);
                if (tokenizer.hasNext()) {
                    tokenizer.next();
                    parseCoefficient();

                }
                return new Less();
            case MORE:
                More more = new More();
                finalList.add(more);
                if (tokenizer.hasNext()) {
                    tokenizer.next();
                    parseCoefficient();

                }
                return new More();
            case EQUAL:
                EqualExp equalExp = new EqualExp();
                finalList.add(equalExp);
                if (tokenizer.hasNext()) {
                    tokenizer.next();
                    parseCoefficient();

                }
                return new EqualExp();
            case AND:
                AndExp andExp = new AndExp();
                finalList.add(andExp);
                if (tokenizer.hasNext()) {
                    tokenizer.next();
                    parseCoefficient();
                }
                return new AndExp();
            case OR:
                OrExp orExp = new OrExp();
                finalList.add(orExp);
                if (tokenizer.hasNext()) {
                    tokenizer.next();
                    parseCoefficient();
                }
                return new OrExp();
        }
        return null;
    }

    /**
     * Adheres to the grammar rule:
     * <coefficient> ::= <unsigned integer>
     *
     * @return type: Exp.
     */
    public Exp parseCoefficient() {
        IntExp result;

        // Check for the corner cases
        if (this.tokenizer.current().getType() == Token.Type.INT) {
            result = new IntExp(Integer.parseInt(this.tokenizer.current().getToken()));
            finalList.add(result.evaluateInt());
            System.out.println("FINAL LIST : " + Arrays.toString(finalList.toArray()));
            return result;
        }
        if (this.tokenizer.current().getType() == Token.Type.TEXT) {
            parseLetter();
        }

        return null;
    }

    /**
     * Adheres to the grammar rule:
     * <letter> ::= <alphabets>
     *
     * @return type: Exp.
     */

    public Exp parseLetter() {

        if (Objects.equals(this.tokenizer.current().getToken(), "city")) {
            if (tokenizer.hasNext()) {
                tokenizer.next();
                if (tokenizer.current().getType() == Token.Type.EQUAL) {
                    parseOperators();
                }
            }

        } else if (Objects.equals(this.tokenizer.current().getToken(), "rent")) {
            if (tokenizer.hasNext()) {
                tokenizer.next();
                if (tokenizer.current().getType() == Token.Type.EQUAL
                        || tokenizer.current().getType() == Token.Type.MORE
                        || tokenizer.current().getType() == Token.Type.LESS) {
                    parseOperators();
                }
            }
        } else if (this.tokenizer.current().getType() == Token.Type.TEXT) {
            finalList.add(tokenizer.current().getToken());
            if (tokenizer.hasNext()) {
                tokenizer.next();
                if (this.tokenizer.current()!=null && this.tokenizer.current().getType() == Token.Type.AND) {
                    parseOperators();
                }
                if(this.tokenizer.current()==null){
                    return new OrExp();
                }
            }

        } else if (tokenizer.hasNext()) {
            tokenizer.next();
            parseOperators();
            finalList.add(tokenizer.current().getToken());
        }

        return new Letter(tokenizer.current().getToken());
    }
}

