package Model;

public class Antibody extends Host{
    int AntibodyPlaceCost = ConfigGame.atb_place_cost ;
    int AntibodyMoveCost = ConfigGame.move_cost;
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
