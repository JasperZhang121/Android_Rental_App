package au.edu.anu.cecs.linkhome.tokenizer.expressions;

/**
 * MoreExp: it is extended from the abstract class Exp.
 * This class is used to represent the expression of > operator.
 *
 * @author Devanshi DHall, Hao Zhang
 */

public class MoreExp extends Exp {

    /**
     * Empty Constructor
     */
    public MoreExp() {

    }

    @Override
    public String show() {
        return ">";
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
