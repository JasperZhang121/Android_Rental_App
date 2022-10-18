package au.edu.anu.cecs.linkhome.Tokenizer;

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
