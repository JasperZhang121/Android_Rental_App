package au.edu.anu.cecs.linkhome.Tokenizer;

public class OrExp extends Exp {

    private Exp term;
    private Exp exp;

    public OrExp() {

    }

    @Override
    public String show() {
        return "(" + term.show() + " || " + exp.show() + ")";
    }

    @Override
    public boolean evaluate() {
        return (term.evaluate() || exp.evaluate());
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

