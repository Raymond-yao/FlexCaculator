public class NewLineStateHandler extends TokenizerStateHandler {
    public NewLineStateHandler() {
        super(Tokenizer.State.NewLine);
    }

    @Override
    public Tokenizer.State handleChar(char c, StringBuilder readBuffer) {

        switch (c) {
            case '\n':
            case '\r':
                readBuffer.append(c);
                break;
            default:
                   moveCursor = false;
                   state = Tokenizer.State.Normal;
                   createdType = Token.TokenType.NEWLINE;
                    break;
        }

        return state;
    }
}
