package au.edu.anu.cecs.linkhome.tokenizer.expressions;

/**
 * LessExp: it is extended from the abstract class Exp.
 * This class is used to represent the expression of < operator.
 *
 * @author Devanshi Dhall, Hao Zhang
 */
public class LessExp extends Exp {

    /**
     * Empty constructor
     */
    public LessExp() {

    }

    @Override
    public String show() {
        return "<";
    }

    @Override
    public boolean evaluate() {
        return true;
    }

    @Override
    public int evaluateInt() {
        return 0;
    }
}
