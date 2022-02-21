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
    public void move(String direction){
        switch (direction) {
            case "left" -> position[0]--;
            case "right" -> position[0]++;
            case "down" -> position[1]++;
            case "up" -> position[1]--;
            case "upright" -> {
                position[1]--;
                position[0]++;
            }
            case "upleft" -> {
                position[1]--;
                position[1]--;
            }
            case "downright" -> {
                position[1]++;
                position[0]++;
            }
            case "downleft" -> {
                position[1]++;
                position[1]--;
            }
        }
    }
    public boolean isDead(){
        return (HP <= 0) ;
    }
    public void shoot(String direction){

    }
}
