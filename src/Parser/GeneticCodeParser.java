package Parser;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import Model.Host;

import java.util.*;

public class GeneticCodeParser {
    int MAX_RAND_BOUND = 100;

    Host host;
    int loopcounter;
    Random rand = new Random();
    Map<String,Integer> data = new HashMap<>();

    public int evalStatement(Expr statement){
        return statement.eval(data);
    }

    public String evalProgram(Program program,Host host){
        return ;
    }
}
