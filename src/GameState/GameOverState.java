package GameState;

import java.util.Scanner;

import Model.Body;

public class GameOverState implements State{
    Body body ;
    public GameOverState(Body body){
        this.body = body ;
    }
    public State render(){
        System.out.println("=============="); 
        System.out.println("    GameOve    ");      
        System.out.println("==============");
        String c ;
        do{
            System.out.println("Wan to start?(Y/n)"); 
            Scanner console = new Scanner(System.in);
            c = console.next();
        }while(c.equals("n")) ;
        return  new StartState() ;           
    }
}
