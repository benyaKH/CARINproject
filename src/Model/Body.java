package Model;

import Parser.SyntaxError;

import java.util.Random;

public class Body {
    static Host[][] map  = new Host[5][5];
    int Antibodyleft ;
    int Virusleft ;
    // public void Spaw(){
    //     Random rand = new Random() ;
    //     int percentspaw = rand.nextInt(100);
    //     System.out.println("Percent = "+percentspaw);
    //     if(percentspaw<70){
    //         int col = rand.nextInt(5);
    //         int row = rand.nextInt(5);
    //         while (true) {
    //             if(map[col][row]== 0){
    //                 System.out.println("(col,row) = "+col+" "+row);
    //                 map[col][row]= 1 ;
    //                 Virusleft++;
    //                 break;
    //             }else {
    //                 col++;
    //             }
    //             System.out.println("(col,row) = "+col+" "+row);
    //         }
    //     }
    // }

    // public  void AddAntibody(int col,int row) throws SyntaxError {
    //     if(col<6&&row<6){
    //         map[col][row] = 2 ;
    //         Antibodyleft++;
    //     }else throw new SyntaxError("Wrong index") ;
    // }

    public static Host getHost(int col,int row){
        return map[col][row] ;
    }
    //to see result in map
    public  void PrintMap(){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
    //test
    public static void main(String[] args) throws SyntaxError {
        Body A = new Body();
        A.PrintMap();
        for(int i = 0;i<10;i++){
            // A.Spaw() ;
            A.PrintMap();
            System.out.println(A.Virusleft);
        }
        // A.AddAntibody(4,4);
        A.PrintMap();
        System.out.println("Model.Virus = "+A.Virusleft);
        System.out.println("Model.Antibody = "+A.Antibodyleft);
    }
}
