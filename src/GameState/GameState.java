package GameState;
import java.util.LinkedList;

import Model.Antibody;
import Model.Pair;
import Model.Virus;

public class GameState {
    LinkedList<Virus> viruslist;
    LinkedList<Antibody> antibodylist;
    int[][] map;

    public GameState(LinkedList<Virus> virus,LinkedList<Antibody> antibody,int[][] map){
        this.viruslist = virus;
        this.antibodylist = antibody;
        this.map = map;
    }
    
    public int location(String name,int x,int y){
        int ans = 0;
        int count = 0;
        int temp = 0;
        int a;
        int b;
        switch(name){
            case "virus" -> {
                for(Virus v : viruslist){
                    a = (int) v.getposition().fst() - x;
                    b = (int) v.getposition().snd() - y;
    
                    if(a != 0 || b!= 0){ // ignore own
                        if(a == b){ // oblique
                            if(a > 0 && b > 0) temp = 10*b+2; // up right
                            else if(a > 0 && b < 0) temp = 10*Math.abs(b)+4; // down right
                            else if(a < 0 && b < 0) temp = 10*Math.abs(b)+6; // down left
                            else if(a < 0 && b > 0) temp = 10*b+8; // up left
                        }else if(a == 0){ // up down
                            if(b > 0) temp = 10*b+1; // up
                            else if(b < 0) temp = 10*Math.abs(b)+5; // down
                        }else if(b == 0){ // left right
                            if(a > 0) temp = 10*a+3; // right
                            else if(a < 0) temp = 10*Math.abs(a)+7; // left
                        }
    
                        if(temp/10 < ans/10) ans = temp; // measure distance | 22 21 -> 2 < 2 -> ans = 22 dosen't change
                        else if(count == 0) ans = temp; // set first time ans
                        count++;
                    }
                }
    
                return ans;
            }
            case "antibody" -> {
                for(Antibody A : antibodylist){
                    a = (int) A.getposition().fst() - x;
                    b = (int) A.getposition().snd() - y;
    
                    if(a != 0 || b!= 0){ // ignore own
                        if(a == b){ // oblique
                            if(a > 0 && b > 0) temp = 10*b+2; // up right
                            else if(a > 0 && b < 0) temp = 10*Math.abs(b)+4; // down right
                            else if(a < 0 && b < 0) temp = 10*Math.abs(b)+6; // down left
                            else if(a < 0 && b > 0) temp = 10*b+8; // up left
                        }else if(a == 0){ // up down
                            if(b > 0) temp = 10*b+1; // up
                            else if(b < 0) temp = 10*Math.abs(b)+5; // down
                        }else if(b == 0){ // left right
                            if(a > 0) temp = 10*a+3; // right
                            else if(a < 0) temp = 10*Math.abs(a)+7; // left
                        }
    
                        if(temp/10 < ans/10) ans = temp; // measure distance | 22 21 -> 2 < 2 -> ans = 22 dosen't change
                        else if(count == 0) ans = temp; // set first time ans
                        count++;
                    }
                }
    
                return ans;
            }
        }

        return 0;
    }

    public double findD(Pair<Integer,Integer> a){
        return Math.sqrt(Math.pow((double) a.fst(),2)+Math.pow((double) a.snd(),2));
    }
}
