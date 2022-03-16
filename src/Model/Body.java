package Model;

import Parser.SyntaxError;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Body {
    int m = ConfigGame.map_m ;
    int n = ConfigGame.map_n ;
    float SpawRate = ConfigGame.virus_spawn_rate ;
    List<Pair<Pair<Integer,Integer>,Host>> map = new ArrayList<Pair<Pair<Integer,Integer>,Host>>() ;
    static Host [][] a = new Host[5][5];
    int Antibodycredit = ConfigGame.intitial_credits;
    int Antibodyleft ;
    int Virusleft ;
     public void Spaw(){
         Random rand = new Random() ;
         int percentspaw = rand.nextInt(100);
         System.out.println("Percent = "+percentspaw);
         if(percentspaw<70){
             int col = rand.nextInt(5);
             int row = rand.nextInt(5);
             System.out.println("(col,row) = "+col+" "+row);
             Virus a_virus = HostFactory.spawnVirus(col, row,"llll") ;
             map.add(new Pair<Pair<Integer,Integer>,Host>(a_virus.getposition(),a_virus));
             Virusleft++;
             System.out.println("(col,row) = "+col+" "+row);
             }
         
     }

     public  void AddAntibody(int col,int row) throws SyntaxError {
         if(col<6&&row<6){
            Pair<Integer,Integer> pos = new Pair<Integer,Integer>(col, row) ;
            map.add(new Pair<Pair<Integer,Integer>,Host>(pos,HostFactory.spawnAntibody(col, row,"llll") ));
         }else throw new SyntaxError("Wrong index") ;
     }

    public static Host getHost(int col,int row){
        return a[col][row] ;
    }
    // to see result in map
    public  void PrintMap(){
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("0 ");
            }
            System.out.println();
        }
    }
    // test
    public static void main(String[] args) throws SyntaxError {
        Body A = new Body();
        A.PrintMap();
        for(int i = 0;i<10;i++){
             A.Spaw() ;
            A.PrintMap();
            System.out.println(A.Virusleft);
        }
         A.AddAntibody(4,4);
        A.PrintMap();
        System.out.println("Model.Virus = "+A.Virusleft);
        System.out.println("Model.Antibody = "+A.Antibodyleft);
    }
}
