package Model;

public class Virus extends Host{
    private Pair<Integer,Integer> position;
    private String geneticcode;

    public Virus(int col,int row,String geneticcode){
        this.position = new Pair<Integer,Integer>(col,row);
        this.geneticcode = geneticcode;
    }

    public Pair<Integer,Integer> getposition(){
        return position;
    }
}
