package GameState;

import Model.Body;

public class WinState implements State{
    Body body ;
    public WinState(Body body){
        this.body = body ;
    }
    public State render(){
        System.out.println("=============="); 
        System.out.println("   YOU WINS!    ");      
        System.out.println("==============");   
        return  new StartState(this.body) ;    
    }
}
