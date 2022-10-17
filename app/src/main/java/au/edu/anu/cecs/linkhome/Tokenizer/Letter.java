package au.edu.anu.cecs.linkhome.Tokenizer;

public class Letter extends Exp{

    private String value;

    public Letter(String value) {
        this.value = value;
    }

    @Override
    public String show() {
        return value.toString();
    }

    @Override
    public boolean evaluate() {
        return false;
    }


    @Override
    public String evaluateString() {
        return value;
    }

    @Override
    public int evaluateInt() {
        return 0;
    }
}
