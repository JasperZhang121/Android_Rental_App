package au.edu.anu.cecs.linkhome.Tokenizer;

/**
 * AddExp: it is extended from the abstract class Exp.
 *         This class is used to represent the expression of addition
 *
 * You are not required to implement any function inside this class.
 * Please do not change anything inside this class as well.
 */

public class AndExp extends Exp {

    private Exp term;
    private Exp exp;

    public AndExp() {
    }

    @Override
    public String show() {
        return "(" + term.show() + " && " + exp.show() + ")";
    }

    @Override
    public boolean evaluate() {
        return (term.evaluate() && exp.evaluate());
    }

    @Override
    public String evaluateString() {
        return null;
    }

    @Override
    public int evaluateInt() {
        return 0;
    }
}
