
public class NormalStateHandler extends TokenizerStateHandler {

    char[] SPACE = {'\t', ' '};
    char[] NEWLINE = {'\n', '\r'};
    char[] SIGN = {'+', '-', '*', '/', '=', '(', ')', '{', '}', '^', '%'};
    char[] NUMBER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

    NormalStateHandler() {
        super(Tokenizer.State.Normal);
    }

    @Override
    public Tokenizer.State handleChar(char c, StringBuilder readBuffer) throws LexicalAnalysisException {
        if (include(SPACE, c)) {
            state = Tokenizer.State.Space;
        } else if (include(NEWLINE, c)) {
            state = Tokenizer.State.NewLine;
        } else if (include(SIGN, c)) {
            state = Tokenizer.State.Sign;
        } else if (validIdentifierChar(c)) {
            state = Tokenizer.State.Identifier;
        } else if (c == '\0') {
            createdType = Token.TokenType.EOF;
        } else {
            throw new LexicalAnalysisException();
        }
        moveCursor = true;
        clearBuffer(readBuffer);
        return state;
    }

    private void clearBuffer(StringBuilder buf) {
        buf.setLength(0);
    }

    private boolean include(char[] set, char c) {
        for (char cha : set) {
            if (cha == c) return true;
        }
        return false;
    }

    private boolean validIdentifierChar(char c) {
        return include(NUMBER, c) || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }
}
