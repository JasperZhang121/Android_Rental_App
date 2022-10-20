package au.edu.anu.cecs.linkhome.tokenizer.expressions;

/**
 * AndExp: it is extended from the abstract class Exp.
 * This class is used to represent the expression of && operator.
 *
 * @author Devanshi Dhall, Hao Zhang
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
