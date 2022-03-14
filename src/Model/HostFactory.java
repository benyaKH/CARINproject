package Model;

public class HostFactory {
    public static Virus spawnVirus(int col,int row,String geneticcode){
        return new Virus(col, row, geneticcode);
    }
    public static Antibody spawnAntibody(int col,int row,String geneticcode){
        return new Antibody(col, row, geneticcode);
    }
}
