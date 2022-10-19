package au.edu.anu.cecs.linkhome.tokenizer.expressions;

/**
 * Abstract class Exp to represent expressions
 *
 * @author Adapted from Lab07, Devanshi DHall, Hao Zhang
 */
public abstract class Exp {

    public abstract String show();

    public abstract boolean evaluate();

    public abstract int evaluateInt();
}

