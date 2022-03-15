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
        StringBuilder strBuild = new StringBuilder();
        statement.prettyPrint(strBuild);
        System.out.println(strBuild);
        return statement.eval(data);
    }

    public int evalProgram(Program program,Host host){
        while(program.hasNext()){
            evalStatement(program.nextStatement());
        }
        System.out.println(data.get("virusLoc"));
        return 0;
    }
}
