import java.util.Random;

public class Host {
    int MaxHP ;
    int HP ;
    int ATK ;
    int[] position = new int[2];
    public void Attack(Host enemy){

    }
    public void getAttack(int dmg){
        this.HP  -= dmg ;
    }
    public boolean isDead(){
        return (HP <= 0) ;
    }
}
