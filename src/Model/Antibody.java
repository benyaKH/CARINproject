package Model;

public class Antibody extends Host{
    int AntibodyPlaceCost ;
    int AntibodyMoveCost ;
    public Antibody(int col, int row){
        this.position[0] = col ;
        this.position[1] = row ;
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