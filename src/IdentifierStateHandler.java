public class IdentifierStateHandler extends TokenizerStateHandler {
    
    public IdentifierStateHandler() {
        super(Tokenizer.State.Identifier);
    }

    @Override
    public Tokenizer.State handleChar(char c, StringBuilder readBuffer) {
        return null;
    }
}
