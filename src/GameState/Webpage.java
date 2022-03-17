package GameState;

import Model.Body;
import Parser.SyntaxError;

public class Webpage {
    Body body = new Body();
    State s ;
    public Webpage(){
        s = new StartState(this.body) ;
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
