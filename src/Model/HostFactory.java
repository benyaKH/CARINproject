package Model;

import GameState.GameState;

public class HostFactory {
    public static Virus spawnVirus(int col,int row,GameState gameState){
        return new Virus(col, row,"./src/Parser/GeneticCodeVirus.txt", gameState);
    }
    public static Antibody spawnAntibody(int col,int row,GameState gameState){
        return new Antibody(col, row,"./src/Parser/GeneticCodeAntibody.txt", gameState);
    }
}
