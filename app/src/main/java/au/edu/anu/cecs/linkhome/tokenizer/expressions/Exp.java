package au.edu.anu.cecs.linkhome.tokenizer.expressions;

/**
 * Abstract class Exp to represent expressions
 * Adapted from Lab07
 *
 * @author Devanshi DHall, Hao Zhang
 */
public abstract class Exp {

    public abstract String show();

    public abstract boolean evaluate();

    public abstract int evaluateInt();
}

