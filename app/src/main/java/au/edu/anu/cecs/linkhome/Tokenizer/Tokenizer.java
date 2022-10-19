package au.edu.anu.cecs.linkhome.Tokenizer;

/**
 * @author Adapted from Lab07, Devanshi DHall, Hao Zhang
 */
public class Tokenizer {
    private String buffer;
    private Token currentToken;

    /**
     * Tokenizer class constructor
     * The constructor extracts the first token and save it to currentToken
     */
    public Tokenizer(String text) {
        buffer = text;
        next();
    }

    /**
     * This function will find and extract a next token from {@code _buffer} and
     * save the token to {@code currentToken}.
     */
    public void next() {
        buffer = buffer.trim();

        if (buffer.isEmpty()) {
            currentToken = null;
            return;
        }

        char firstChar = buffer.charAt(0);
        String firstTwoChars = buffer.substring(0, 2);

        if (firstTwoChars.equals("&&"))
            currentToken = new Token("&&", Token.Type.AND);

        else if (firstTwoChars.equals("||"))
            currentToken = new Token("||", Token.Type.OR);

        else if (firstChar == '<')
            currentToken = new Token("<", Token.Type.LESS);

        else if (firstChar == '>')
            currentToken = new Token(">", Token.Type.MORE);

        else if (firstChar == '=')
            currentToken = new Token("=", Token.Type.EQUAL);

        //Check for a letter
        else if (Character.isLetter(firstChar)) {
            StringBuilder letter = new StringBuilder();
            for (int i = 0; i < buffer.length(); i++)
                if (Character.isLetter(buffer.charAt(i))) {
                    letter.append(buffer.charAt(i));
                } else {
                    break;
                }
            currentToken = new Token(letter.toString().trim(), Token.Type.TEXT);
        }

        // Check for a digit
        else if (Character.isDigit(firstChar)) {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < buffer.length(); i++) {
                if (Character.isDigit(buffer.charAt(i))) {
                    result.append(buffer.charAt(i));
                } else
                    break;
            }
            currentToken = new Token(result.toString().trim(), Token.Type.INT);
        }
        else {
            throw new Token.IllegalTokenException("Incorrect");
        }

        // Remove the extracted token from buffer
        int tokenLen = currentToken.getToken().length();
        buffer = buffer.substring(tokenLen);
    }

    /**
     * Returns the current token extracted by {@code next()}
     * @return type: Token
     */
    public Token current() {
        return currentToken;
    }

    /**
     * Check whether tokenizer still has tokens left
     * @return type: boolean
     */
    public boolean hasNext() {
        return currentToken != null;
    }
}

