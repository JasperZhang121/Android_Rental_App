package au.edu.anu.cecs.linkhome.tokenizer.expressions;

/**
 * EqualExp: it is extended from the abstract class Exp.
 * This class is used to represent the expression of = operator.
 */
public class EqualExp extends Exp {

    public EqualExp() {

    }

    @Override
    public String show() {
        return "=";
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
