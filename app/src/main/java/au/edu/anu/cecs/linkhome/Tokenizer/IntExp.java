package au.edu.anu.cecs.linkhome.Tokenizer;

/**
 * LitExp: it is extended from the abstract class Exp,
 * 		   This class is used to represented the expression of 32-bit unsigned integer
 *
 * You are not required to implement any function inside this class.
 * Please do not change any thing inside this class as well.
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
    public int evaluate() {
        return value;
    }
}
