package Model;

import Parser.SyntaxError;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Body {
    int m = ConfigGame.map_m ;
    int n = ConfigGame.map_n ;
    float SpawRate = ConfigGame.virus_spawn_rate ;
    static Map<Pair<Integer,Integer>,Host> map = new HashMap<Pair<Integer,Integer>,Host>() ;
    int Antibodycredit = ConfigGame.intitial_credits;
    int Antibodyleft ;
    static int Virusleft ;
    int AntibodyPlaceCost = ConfigGame.atb_place_cost ;
    int AntibodyMoveCost = ConfigGame.move_cost;
    public int time = 0 ;
    public int getVirusleft(){
        return Virusleft ;
    }
     public void Spaw(){
         Random rand = new Random() ;
         int percentspaw = rand.nextInt(100);
         System.out.println("Percent = "+percentspaw);
         if(percentspaw<(SpawRate*100)){
             int col = rand.nextInt(5);
             int row = rand.nextInt(5);
             System.out.println("(col,row) = "+col+" "+row);
             Virus a_virus = HostFactory.spawnVirus(col, row) ;
             map.put(a_virus.getposition(),a_virus);
             Virusleft++;
             System.out.println("(col,row) = "+col+" "+row);
             }
         
     }
     public static  void Addvirus(int col,int row) throws SyntaxError {
        if(col<6&&row<6){
            Virus a_virus = HostFactory.spawnVirus(col, row) ;
            map.put(a_virus.getposition(),a_virus);
            Virusleft++;
        }else throw new SyntaxError("Wrong index") ;
    }
    public  void AddAntibody(int col,int row) throws SyntaxError {
        if(Antibodycredit<AntibodyPlaceCost){
            System.out.println("You don't have enough credit!!!");
        }else{
            if(col<6&&row<6){
                Antibody a_antibody = HostFactory.spawnAntibody(col, row) ;
                map.put(a_antibody.getposition(),a_antibody);
                Antibodycredit = Antibodycredit-AntibodyPlaceCost ;
                Antibodyleft++;
             }else throw new SyntaxError("Wrong index") ;

        }
     }
     public  void MoveAntibody(int Oldcol,int Oldrow,int Newcol,int Newrow) throws SyntaxError {
        if(Oldcol<m && Oldrow<n&&Newcol<m && Newrow<n){
            Pair<Integer,Integer> Oldpos = new Pair<Integer,Integer>(Oldcol, Oldrow) ;
           Pair<Integer,Integer> Newpos = new Pair<Integer,Integer>(Newcol, Newrow) ;
           Antibody anti = null  ;
           for(Pair<Integer,Integer> i :map.keySet()){
               if(i.equals(Oldpos)){
                   anti = (Antibody) map.get(i) ;
                   Oldpos = i ;
                   break ;
               }
            }
            if(anti != null){
                if(anti.HP<AntibodyMoveCost){
                    System.out.println("This Antibody doesn't have enough HP!!!");
                }else{
                 map.remove(Oldpos) ;
                 anti.setPos(Newpos);
                 anti.HP = anti.HP-AntibodyPlaceCost ;
                 map.put(Newpos,anti) ;
                }
            }else throw new SyntaxError("no such an element") ;
        }else throw new SyntaxError("Wrong index") ;
    }

    public static Host getHost(int col,int row){
        Pair<Integer,Integer> pos = new Pair<Integer,Integer>(col, row) ;
        return map.get(pos); 
    }
    // to see result in map
    public  void PrintMap(){
        String[][] mmmm = new String[m][n] ;
        for(Pair<Integer,Integer> i :map.keySet()){
            if(map.get(i).getClass().toString().equals("class Model.Virus"))
            mmmm[i.fst][i.snd] = "V" ;
            else
            mmmm[i.fst][i.snd] = "A" ;

        }
        System.out.println("Antibodycredit = "+Antibodycredit);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(mmmm[i][j]==null) System.out.print("0 ") ;
                else
                System.out.print(mmmm[i][j]+" ");
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
        A.Addvirus(4, 3);
        A.AddAntibody(4,4);
        A.PrintMap();
        System.out.println("Virusleft = "+A.Virusleft);
        System.out.println("Antibodyleft = "+A.Antibodyleft);

    }
}
