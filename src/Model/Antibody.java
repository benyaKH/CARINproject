package Model;

import java.util.Random;

import GameState.GameState;

public class Antibody extends Host{
    private Pair<Integer,Integer> position;
    private String geneticcode;

    public Antibody(int col, int row,String geneticcode,GameState gameState){
        this.position = new Pair<Integer,Integer>(col,row);
        this.geneticcode = geneticcode;
        MaxHP = ConfigGame.virus_maxHP ;
        HP = ConfigGame.virus_maxHP ;
        ATK = ConfigGame.virus_atk ;
        gain = ConfigGame.virus_gain ;
        this.gameState = gameState;
    }

    public Pair<Integer,Integer> getposition(){
        return position;
    }

    private void TurntoVirus(){
        HostFactory hf = new HostFactory();
        Random rand = new Random();
        Virus virus = hf.spawnVirus(rand.nextInt(gameState.map.fst()), rand.nextInt(gameState.map.snd()), gameState);
        gameState.addVirus(virus);
    }

    @Override
    public void getAttack(int dmg){
        this.HP  -= dmg ;
        if(isDead()){
            this.TurntoVirus();
        }
    }

    public String getGeneticcode(){
        return geneticcode;
    }
}
