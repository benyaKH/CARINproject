package GameState;

import Parser.SyntaxError;

public interface State {
    State render() throws SyntaxError;
}
