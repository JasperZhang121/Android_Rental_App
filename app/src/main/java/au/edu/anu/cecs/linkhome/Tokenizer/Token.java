package au.edu.anu.cecs.linkhome.Tokenizer;

import androidx.annotation.NonNull;
import java.util.Objects;

/**
 * Token class to save extracted token from tokenizer.
 * Each token has its surface form saved in {@code token}
 * and type saved in {@code type} which is one of the predefined type in Type enum.
 * The following are the different types of tokens:
 * INT: integer
 * AND: &&
 * OR: ||
 * LESS: <
 * MORE: >
 * EQUAL: =
 * TEXT: String
 * @author Adapted from Lab07, Devanshi Dhall, Hao Zhang
 */
public class Token {
    /**
     * The following enum defines different types of tokens
     */
    public enum Type {INT, LESS, MORE, EQUAL, AND, OR, TEXT}

    /**
     * The following exception should be thrown if a tokenizer attempts to tokenize something that is not of one
     * of the types of tokens.
     */
    public static class IllegalTokenException extends IllegalArgumentException {
        public IllegalTokenException(String errorMessage) {
            super(errorMessage);
        }
    }

    /**
     * Fields of the class Token.
     * String token: Token representation in string form
     * Type type: Type of the token
     */

    private final String token;
    private final Type type;

    public Token(String token, Type type) {
        this.token = token;
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public Type getType() {
        return type;
    }

    @NonNull
    @Override
    public String toString() {
        if (type == Type.INT) {
            return "INT(" + token + ")";
        } else {
            return type + "";
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Token)) return false;
        return this.type == ((Token) other).getType() && this.token.equals(((Token) other).getToken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, type);
    }
}

