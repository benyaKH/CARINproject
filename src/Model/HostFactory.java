package Model;

import GameState.GameState;

public class HostFactory {
    public static Virus spawnVirus(int col,int row){
        return new Virus(col, row,"./src/Parser/GeneticCodeVirus.txt");
    }
    public static Antibody spawnAntibody(int col,int row){
        return new Antibody(col, row,"./src/Parser/GeneticCodeAntibody.txt");
    }
}
