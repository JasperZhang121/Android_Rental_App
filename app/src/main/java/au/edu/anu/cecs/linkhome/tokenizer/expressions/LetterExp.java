package au.edu.anu.cecs.linkhome.tokenizer.expressions;

/**
 * Letter: it is extended from the abstract class Exp.
 * This class is used to represent the alphabets.
 *
 * @author Devanshi DHall, Hao Zhang
 */
public class LetterExp extends Exp {

    private final String value;

    public LetterExp(String value) {
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
