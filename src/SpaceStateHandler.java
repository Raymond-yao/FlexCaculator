public class SpaceStateHandler extends TokenizerStateHandler {

    SpaceStateHandler() {
        super(Tokenizer.State.Space);
    }

    @Override
    public Tokenizer.State handleChar(char c, StringBuilder readBuffer) throws LexicalAnalysisException {
        switch (c) {
            case '\t':
            case ' ':
                readBuffer.append(c);
                break;
            case '\n':
            case '\0':
                this.moveCursor = false;
                this.createdType = Token.TokenType.SPACE;
                state = Tokenizer.State.Normal;
                break;
            default:
                    throw new LexicalAnalysisException();
        }
        return state;
    }
}
