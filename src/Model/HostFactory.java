package Model;

public class HostFactory {
    public static Virus spawnVirus(int col,int row){
        return new Virus(col, row);
    }
    public static Antibody spawnAntibody(int col,int row){
        return new Antibody(col, row);
    }
}
