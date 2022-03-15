package Model;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class ConfigGame {
    private static Properties config;

    static {
        try {
            Path p = Path.of("gameconfig.properties");
            Reader reader = Files.newBufferedReader(p);
            config = new Properties();
            config.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int get(String key) {
        return Integer.parseInt(config.getProperty(key));
    }

    public static int map_m = get("map_m");
    public static int map_n = get("map_n");
    public static float virus_spawn_rate = Float.parseFloat(config.getProperty("virus_spawn_rate"));
    public static int intitial_credits = get("intitial_credits");
    public static int atb_place_cost = get("atb_place_cost");
    public static int virus_maxHP = get("virus_maxHP");
    public static int antibody_maxHP = get("antibody_maxHP");
    public static int virus_atk = get("virus_atk");
    public static int virus_gain = get("virus_gain");
    public static int antibody_atk = get("antibody_atk");
    public static int antibody_gain = get("antibody_gain");
    public static int move_cost = get("move_cost");
    public static int credit_gain = get("credit_gain");

    public static void main(String[] args) {
        System.out.println(ConfigGame.map_m);
        System.out.println(ConfigGame.map_n);
        System.out.println(ConfigGame.virus_spawn_rate);
        System.out.println(ConfigGame.intitial_credits);
        System.out.println(ConfigGame.atb_place_cost);
    }

}
