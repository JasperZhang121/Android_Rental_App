package au.edu.anu.cecs.linkhome.Tokenizer;

public class More extends Exp {
    private Exp term;
    private Exp exp;

    public More() {

    }

    @Override
    public String show() {
        return "(" + term.show() + " > " + exp.show() + ")";
    }

    @Override
    public boolean evaluate() {
        return true;
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
