import java.util.HashSet;
import java.util.Set;

/*
 *  Records the token to be built
 * */
public class Token {
    public enum TokenType {
        KEYWORD, SIGN, EOF, NUMBER, ANNOTATION, SPACE, NEWLINE, IDENTIFIER
    }

    private static Set<String> keyWords = new HashSet<>();

    static {
        keyWords.add("let");
        keyWords.add("mod");
    }

    public TokenType type;
    public String value;

    public Token(TokenType type, String value) {
        switch (type) {
            case IDENTIFIER:
                if (keyWords.contains(value)) {
                    this.type = TokenType.KEYWORD;
                    this.value = value;
                }
                break;
            case ANNOTATION:
                this.value = value.substring(1); // get rid of # sign
                this.type = type;
            case SPACE:
                this.value = " ";
                this.type = type;
            default:
                this.type = type;
                this.value = value;
        }
    }

    @Override
    public String toString() {
        return "Token( Type: " + this.type + ", " + "Value: " + this.value;
    }
}


