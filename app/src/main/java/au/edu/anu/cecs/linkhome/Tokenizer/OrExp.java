package au.edu.anu.cecs.linkhome.Tokenizer;

public class OrExp extends Exp {

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

