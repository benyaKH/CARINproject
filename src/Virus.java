public class Virus{
    private int hp;
    private Pair<Integer,Integer> position;
    private String geneticcode;

    public Virus(int hp,int x,int y,String geneticcode){
        this.hp = hp;
        this.position = new Pair(x,y);
        this.geneticcode = geneticcode;
    }

    public Pair getposition(){
        return position;
    }
}
