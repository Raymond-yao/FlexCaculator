public abstract class TokenizerStateHandler {

    Tokenizer.State state = null;
    boolean moveCursor = true;
    Token.TokenType createdType = null;
    public abstract Tokenizer.State handleChar(char c, StringBuilder readBuffer) throws LexicalAnalysisException;

    public TokenizerStateHandler(Tokenizer.State state) {
        this.state = state;
    }

    boolean shouldMoveCursor() {
        return this.moveCursor;
    }

    Token.TokenType getCreatedType() {
        return this.createdType;
    }
}
