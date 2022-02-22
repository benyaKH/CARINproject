package Model;

import java.util.Random;

public class Host {
    int MaxHP ;
    int HP ;
    int ATK ;
    int Xposition ,Yposition;
    public void getAttack(int dmg){
        this.HP  -= dmg ;
    }
    public void move(String direction){
        switch (direction) {
            case "left" -> Xposition--;
            case "right" -> Xposition++;
            case "down" -> Yposition++;
            case "up" -> Yposition--;
            case "upright" -> {
                Yposition--;
                Xposition++;
            }
            case "upleft" -> {
                Yposition--;
                Xposition--;
            }
            case "downright" -> {
                Yposition++;
                Xposition++;
            }
            case "downleft" -> {
                Yposition++;
                Xposition--;
            }
        }
    }
    public boolean isDead(){
        return (HP <= 0) ;
    }
    public void shoot(String direction){
        
    }
}
