package GameState;

import Parser.SyntaxError;

public class Webpage {
    State s ;
    public Webpage(){
        s = new StartState() ;
    }
    public void render() throws SyntaxError{
        s = s.render() ;
    }
    public static void main(String[] args) throws SyntaxError {
        Webpage w = new Webpage();
        while(true){
            w.render();
        }
    }
}
