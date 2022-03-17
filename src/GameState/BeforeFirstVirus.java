package GameState;

import java.util.Scanner;

import Model.Body;
import Parser.SyntaxError;

public class BeforeFirstVirus implements State{
    Body body ;
    public BeforeFirstVirus(Body body){
        this.body = body;
    }
    public State render() throws SyntaxError{
        Scanner console = new Scanner(System.in);
        body.Spaw();
        System.out.println("Time unit = "+ body.time);
        body.PrintMap(); 
        System.out.println("What you you want to do?");
        System.out.println("A-Add Antibody  M-Move Antibody  S-Skip  R-Restart");
        String choice = console.next() ;
        if(choice.equals("A")){
            System.out.println("col row");
            int col = console.nextInt() ;
            int row = console.nextInt() ;
            body.AddAntibody(col, row);
        }else if(choice.equals("M")){
            System.out.println("Which one?(col row)");
            int Ocol = console.nextInt() ;
            int Orow = console.nextInt() ;
            System.out.println("Move to?(col row)");
            int Ncol = console.nextInt() ;
            int Nrow = console.nextInt() ;
            body.MoveAntibody(Ocol, Orow, Ncol, Nrow);
        }else if(choice.equals("S")){

        }else if(choice.equals("R")){
            return  new StartState() ;
        }
        body.time++;
        if(body.getVirusleft() == 0){
            return new BeforeFirstVirus(this.body) ;
        }else
        return new Defaltstate(this.body) ;
    }

    
}
