package au.edu.anu.cecs.linkhome.Tokenizer;

/**
 * OrExp: it is extended from the abstract class Exp.
 * This class is used to represent the expression of || operator.
 */
public class OrExp extends Exp {

    /**
     * Empty Constructor
     */
    public OrExp() {

    }

    @Override
    public String show() {
        return "||";
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

