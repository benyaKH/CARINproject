package Model;

public class Antibody extends Host{
    int MaxHP = ConfigGame.antibody_maxHP ;
    int HP = ConfigGame.antibody_maxHP ;
    int ATK = ConfigGame.antibody_atk ;
    int gain = ConfigGame.antibody_gain ;
    private Pair<Integer,Integer> position;
    private String geneticcode;

    public Antibody(int col, int row,String geneticcode){
        this.position = new Pair<Integer,Integer>(col,row);
        this.geneticcode = geneticcode;
    }

    public Pair<Integer,Integer> getposition(){
        return position;
    }

    private void TurntoVirus(){

    }

    @Override
    public void getAttack(int dmg){
        this.HP  -= dmg ;
        if(isDead()){
            this.TurntoVirus();
        }
    }

}
