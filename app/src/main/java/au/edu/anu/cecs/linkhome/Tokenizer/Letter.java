package au.edu.anu.cecs.linkhome.Tokenizer;

/**
 * Letter: it is extended from the abstract class Exp.
 * This class is used to represent the alphabets.
 * @author Devanshi DHall, Hao Zhang
 */
public class Letter extends Exp {

    private final String value;

    public Letter(String value) {
        this.value = value;
    }

    @Override
    public String show() {
        return value;
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
