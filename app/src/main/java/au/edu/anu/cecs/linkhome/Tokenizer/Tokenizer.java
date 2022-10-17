package au.edu.anu.cecs.linkhome.Tokenizer;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Welcome to Task 1.
 * In this task your job is to implement the next() method. Just some rules:
 * 1. You may NOT modify any other classes in this task 1 package.
 * 2. You may not modify any of the methods or fields (that already exist) within this class.
 * 3. You may create additional fields or methods to finish you implementation within this class.
 * <p>
 * Tokenization, within the context of this lab, is the process of splitting a string into
 * small units called, 'Tokens', to be passed onto the Parser.
 */
public class Tokenizer {
    private String buffer;          // String to be transformed into tokens each time next() is called.
    private Token currentToken;     // The current token. The next token is extracted when next() is called.


    /**
     * The following exception should be thrown if the tokenizer is faced with token/s that do not
     * correlate with any of the defined accepted tokens.
     */
    public static class IllegalTokenException extends IllegalArgumentException {
        public IllegalTokenException(String errorMessage) {
            super(errorMessage);
        }
    }

    /**
     * To help you both test and understand what this tokenizer is doing, we have included a main method
     * which you can run. Once running, provide a mathematical string to the terminal and you will
     * receive back the result of your tokenization.
     */
    public static void main(String[] args) {
        // Create a scanner to get the user's input.
        Scanner scanner = new Scanner(System.in);

        /*
         Continue to get the user's input until they exit.
         To exit press: Control + D or providing the string 'q'
         Example input you can try: city='Canberra' && rent < '350'
         */
        System.out.println("Provide an expression string to be tokenized:");
        while (scanner.hasNext()) {
            String input = scanner.nextLine();

            // Check if 'quit' is provided.
            if (input.equals("q"))
                break;

            // Create an instance of the tokenizer.
            Tokenizer tokenizer = new Tokenizer(input);

            // Print all the tokens.
            while (tokenizer.hasNext()) {
                System.out.print(tokenizer.current() + " ");
                tokenizer.next();
            }
            System.out.println();
        }
    }

    /**
     * Tokenizer class constructor
     * The constructor extracts the first token and save it to currentToken
     * **** please do not modify this part ****
     */
    public Tokenizer(String text) {
        buffer = text;          // save input text (string)
        next();                 // extracts the first token.
    }

    /**
     * This function will find and extract a next token from {@code _buffer} and
     * save the token to {@code currentToken}.
     */
    public void next() {
        buffer = buffer.trim();     // remove whitespace

        if (buffer.isEmpty()) {
            currentToken = null;    // if there's no string left, set currentToken null and return
            return;
        }

        /*
        To help you, we have already written the first few steps in the tokenization process.
        The rest will follow a similar format.
         */

        //String parameters = buffer.substring(0,5);

        char firstChar = buffer.charAt(0);
        String firstTwoChars = buffer.substring(0,2);
        if (firstTwoChars.equals("&&"))
            currentToken = new Token("&&", Token.Type.AND);
        else if (firstTwoChars.equals("||"))
        currentToken = new Token("||", Token.Type.OR);


        /*
         TODO: Implement multiplication and division tokenizing.
         TODO: Implement left round bracket and right round bracket.
         TODO: Implement Factorial tokenizing
         TODO: Implement integer literal tokenizing.
         TODO: Throw an IllegalTokenException when a character which does not correlate to any token type is provided.
         Hint: Character.isDigit() may be useful.
         */
            // ########## YOUR CODE STARTS HERE ##########

        else if (firstChar == '<')
            currentToken = new Token("<", Token.Type.LESS);
        else if (firstChar == '>')
            currentToken = new Token(">", Token.Type.MORE);
        else if (firstChar == '=')
            currentToken = new Token("=", Token.Type.EQUAL);
        else if(Character.isLetter(firstChar)){
            StringBuilder letter = new StringBuilder();
            for(int i=0;i<buffer.length();i++)
                if(Character.isLetter(buffer.charAt(i))){
                    letter.append(buffer.charAt(i));
                }
            else{
                break;
                }
        }
            // Check for a digit
        else if(Character.isDigit(firstChar)){
            StringBuilder result = new StringBuilder();
            for(int i=0;i<buffer.length();i++){
                if(Character.isDigit(buffer.charAt(i))){
                    result.append(buffer.charAt(i));
                }
                else
                    break;
            }
            currentToken = new Token(result.toString().trim(), Token.Type.INT);

        }

        else{
            throw new Token.IllegalTokenException("Incorrect");
        }

        // ########## YOUR CODE ENDS HERE ##########
        // Remove the extracted token from buffer
        int tokenLen = currentToken.getToken().length();
        buffer = buffer.substring(tokenLen);
    }

    /**
     * Returns the current token extracted by {@code next()}
     * **** please do not modify this part ****
     *
     * @return type: Token
     */
    public Token current() {
        return currentToken;
    }

    /**
     * Check whether tokenizer still has tokens left
     * **** please do not modify this part ****
     *
     * @return type: boolean
     */
    public boolean hasNext() {
        return currentToken != null;
    }
}

