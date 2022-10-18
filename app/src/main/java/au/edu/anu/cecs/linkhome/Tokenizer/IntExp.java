package au.edu.anu.cecs.linkhome.Tokenizer;

/**
 * IntExp: it is extended from the abstract class Exp,
 * This class is used to represented the expression of 32-bit unsigned integer
 */

public class IntExp extends Exp {

    private Integer value;

    public IntExp(Integer value) {
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
    public int evaluateInt() {
        return value;
    }
}
