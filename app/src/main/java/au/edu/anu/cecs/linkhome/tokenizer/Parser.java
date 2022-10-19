package au.edu.anu.cecs.linkhome.tokenizer;

import java.util.ArrayList;
import java.util.Objects;
import java.lang.Object;

import au.edu.anu.cecs.linkhome.tokenizer.expressions.AndExp;
import au.edu.anu.cecs.linkhome.tokenizer.expressions.EqualExp;
import au.edu.anu.cecs.linkhome.tokenizer.expressions.Exp;
import au.edu.anu.cecs.linkhome.tokenizer.expressions.IntExp;
import au.edu.anu.cecs.linkhome.tokenizer.expressions.LessExp;
import au.edu.anu.cecs.linkhome.tokenizer.expressions.LetterExp;
import au.edu.anu.cecs.linkhome.tokenizer.expressions.MoreExp;
import au.edu.anu.cecs.linkhome.tokenizer.expressions.OrExp;

/**
 * It should be able to parser the following grammar rule:
 * <exp>    ::=  <term> || <exp> | <term> && <exp> | <term>
 *     // We do not have term, how to modify this grammar?
 * <term>   ::=  <factor> | <factor> * <term> | <factor> / <term>
 * <operator> ::= < < | > | = >
 * <coefficient> ::= <unsigned integer>
 * <letter> ::= <alphabets>
 * @author Devanshi Dhall, Hao Zhang, Adapted some parts from Lab07
 */
public class Parser {

    private final ArrayList<Object> finalList = new ArrayList<>();
    private final Tokenizer tokenizer;

    /**
     * Parser class constructor
     * Simply sets the tokenizer field.
     */
    public Parser(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    /**
     * Create an array list to store the necessary tokens in the list
     * @return finalList
     */
    public ArrayList<Object> getFinalList() {
        return finalList;
    }

    /**
     * Adheres to the grammar rule:
     * <exp>    ::= <term> || <exp> | <term> && <exp> | <term>
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
     * @return type: Exp.
     */
    public Exp parseOperators() {

        switch (this.tokenizer.current().getType()) {
            case LESS:
                LessExp less = new LessExp();
                finalList.add(less);
                if (tokenizer.hasNext()) {
                    tokenizer.next();
                    parseCoefficient();
                }
                return new LessExp();

            case MORE:
                MoreExp more = new MoreExp();
                finalList.add(more);
                if (tokenizer.hasNext()) {
                    tokenizer.next();
                    parseCoefficient();
                }
                return new MoreExp();

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
     * @return type: Exp.
     */
    public Exp parseCoefficient() {

        IntExp result;

        if (this.tokenizer.current().getType() == Token.Type.INT) {
            result = new IntExp(Integer.parseInt(this.tokenizer.current().getToken()));
            finalList.add(result.evaluateInt());
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
                if (tokenizer.current().getType() == Token.Type.EQUAL || tokenizer.current().getType() == Token.Type.MORE || tokenizer.current().getType() == Token.Type.LESS) {
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
                else if(this.tokenizer.current()!=null && this.tokenizer.current().getType() == Token.Type.OR){
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

        return new LetterExp(tokenizer.current().getToken());
    }
}

