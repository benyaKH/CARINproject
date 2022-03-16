import java.util.Random;

import GameState.GameState;
import Model.*;
import Parser.ReadGeneticCode;
import Parser.SyntaxError;

public class Runner {
    public static void main(String[] args) {
        Random rand = new Random();
        HostFactory hostfact = new HostFactory();
        ConfigGame config = new ConfigGame();
        GameState gamesState = new GameState(new Pair(config.map_m,config.map_n));
        for (int i = 0; i < 3; i++) {
            gamesState.addVirus(hostfact.spawnVirus(rand.nextInt(config.map_m), rand.nextInt(config.map_n),gamesState));
        }
        for (int j = 0; j < 3; j++) {
            gamesState.addAntibody(hostfact.spawnAntibody(rand.nextInt(config.map_m), rand.nextInt(config.map_n),gamesState));
        }
        ReadGeneticCode RGC = new ReadGeneticCode();

        while(gamesState.getViruslist().isEmpty() && gamesState.getAntibodylist().isEmpty()){
            for (Virus virus : gamesState.getViruslist()) {
                String src = RGC.readfile(virus.getGeneticcode());
                try {
                    RGC.evaluate(src,virus);
                } catch (SyntaxError e) {
                    e.printStackTrace();
                }
            }
            for (Antibody antibody : gamesState.getAntibodylist()) {
                String src = RGC.readfile(antibody.getGeneticcode());
                try {
                    RGC.evaluate(src,antibody);
                } catch (SyntaxError e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
