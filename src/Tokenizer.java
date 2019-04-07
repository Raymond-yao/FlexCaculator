import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Tokenizer {

    public enum State {
        Normal, Space, NewLine, Annotation, Identifier, Sign
    }

    private Map<State, TokenizerStateHandler> stateMap = new HashMap<>();

    private TokenizerStateHandler currentStateHandler;
    private boolean moveCursor = true;
    private Token.TokenType createdType = null;
    private StringBuilder readBuffer = new StringBuilder();
    private Token endToken = null;

    public Tokenizer() {
        stateMap.put(State.Normal, new NormalStateHandler());
        stateMap.put(State.Space, new SpaceStateHandler());
        stateMap.put(State.NewLine, new NewLineStateHandler());
        stateMap.put(State.Annotation, new AnnotationStateHanlder());
        stateMap.put(State.Identifier, new IdentifierStateHandler());
        stateMap.put(State.Sign, new SignStateHandler());

        this.currentStateHandler = stateMap.get(State.Normal);
    }

    public Token read(BufferedInputStream reader) {
        try {
            if (endToken != null) {
                return endToken;
            }
            Token result = null;
            char charRead = '\0';
            while (result == null) {
                if (moveCursor) {
                    int readResult = reader.read();
                   charRead  = readResult == -1 ? '\0' : (char) readResult;
                }
                State newState = this.currentStateHandler.handleChar(charRead, this.readBuffer);
                moveCursor = this.currentStateHandler.shouldMoveCursor();
                createdType = this.currentStateHandler.getCreatedType();
                currentStateHandler = stateMap.get(newState);
                if (createdType != null) {
                    result = createToken(createdType, readBuffer);
                    if (createdType == Token.TokenType.EOF) {
                        endToken = result;
                    }
                }
            }

            return result;
        } catch (Exception e) {
            if (e instanceof LexicalAnalysisException) {
                // TODO
            }
            e.printStackTrace();
        }

        return null;
    }

    private Token createToken(Token.TokenType type, StringBuilder readBuffer) {
        return new Token(type, readBuffer.toString());
    }
}
