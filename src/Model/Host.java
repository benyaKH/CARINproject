package Model;


public class Host {
    int MaxHP  = 10;
    int HP = 10;
    int ATK = 10;
    private Pair<Integer,Integer> position;
    public void getAttack(int dmg){
        this.HP  -= dmg ;
    }
    private Pair<Integer,Integer> getDirection(Pair<Integer,Integer> pos,String direction){
        int fst = pos.fst ;
        int snd = pos.snd ;
        switch (direction) {
            case "left" -> fst--;
            case "right" -> fst++;
            case "down" -> snd++;
            case "up" -> snd--;
            case "upright" -> {
                snd--;
                fst++;
            }
            case "upleft" -> {
                snd--;
                fst--;
            }
            case "downright" -> {
                snd++;
                fst++;
            }
            case "downleft" -> {
                snd++;
                fst--;
            }
        }
        return new Pair<Integer,Integer>(fst, snd);
    }
    public void move(String direction){
        Pair<Integer,Integer> NewPos = getDirection(this.position, direction);
        this.position = NewPos ;
    }
    public boolean isDead(){
        return (HP <= 0) ;
    }
    public void shoot(String direction){
        Pair<Integer,Integer> enemyPos = getDirection(this.position, direction);
        Host Enemy = Body.getHost(enemyPos.fst,enemyPos.snd);
        Enemy.getAttack(this.ATK);
    }
}
