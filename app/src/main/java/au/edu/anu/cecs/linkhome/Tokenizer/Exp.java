package au.edu.anu.cecs.linkhome.Tokenizer;

/**
 * Abstract class Exp to represent expressions
 *
 * @author dongwookim
 * @author xuyangshen
 */
public abstract class Exp {

    public abstract String show();

    public abstract boolean evaluate();

    public abstract int evaluateInt();
}

