package Model;

import GameState.GameState;

public class Virus extends Host{
    private Pair<Integer,Integer> position ;
    private String geneticcode ;

    public Virus(int col,int row,String geneticcode){
        this.position = new Pair<Integer,Integer>(col,row);
        this.geneticcode = geneticcode;
        MaxHP = ConfigGame.virus_maxHP ;
        HP = ConfigGame.virus_maxHP ;
        ATK = ConfigGame.virus_atk ;
        gain = ConfigGame.virus_gain ;
        // this.gameState = gameState;
    }

    public Pair<Integer,Integer> getposition(){
        return position;
    }

    public String getGeneticcode(){
        return geneticcode;
    }
}
