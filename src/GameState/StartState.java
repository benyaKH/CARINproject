package GameState;

import java.util.Scanner;

import Model.Body;
import Parser.SyntaxError;

public class StartState implements State{
    Body body ;
    public StartState(){
        this.body = new Body() ;
    }
    public State render() throws SyntaxError{
        String c ;
        do{
            System.out.println("Wan to start?(Y/n)"); 
            Scanner console = new Scanner(System.in);
            c = console.next();
        }while(c.equals("n")) ;
        if(c.equals("Y")){
            return new BeforeFirstVirus(this.body) ;
        }else throw new SyntaxError("this ans undefined") ;
    }
}
