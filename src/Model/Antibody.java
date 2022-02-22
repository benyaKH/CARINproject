package Model;

public class Antibody extends Host{
    int AntibodyPlaceCost ;
    int AntibodyMoveCost ;
    public Antibody(int col, int row){
        this.Xposition = col ;
        this.Yposition = row ;
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
