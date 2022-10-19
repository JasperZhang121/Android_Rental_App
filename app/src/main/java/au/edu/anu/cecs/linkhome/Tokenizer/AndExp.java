package au.edu.anu.cecs.linkhome.Tokenizer;

/**
 * AndExp: it is extended from the abstract class Exp.
 * This class is used to represent the expression of && operator.
 */

public class AndExp extends Exp {
    public AndExp() {
    }

    @Override
    public String show() {
        return "&&";
    }

    @Override
    public boolean evaluate() {
        return false;
    }

    @Override
    public int evaluateInt() {
        return 0;
    }
}
