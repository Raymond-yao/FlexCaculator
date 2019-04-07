public class AnnotationStateHanlder extends TokenizerStateHandler {

    AnnotationStateHanlder() {
        super(Tokenizer.State.Annotation);
    }

    @Override
    public Tokenizer.State handleChar(char c, StringBuilder readBuffer) {
        switch (c) {
            case '\n':
            case '\0':
                moveCursor = false;
                this.createdType = Token.TokenType.ANNOTATION;
                state = Tokenizer.State.Normal;
                break;
                default:
                    readBuffer.append(c);
                    break;
        }

        return state;
    }
}
