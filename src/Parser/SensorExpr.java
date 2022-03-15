package Parser;

import java.util.Map;

public class SensorExpr implements Expr{
    String name;
    String direction;
        int ans = 0;
        int count = 0;
        int temp = 0;
        int a;
        int b;

    public SensorExpr(String name){
        this.name = name;
    }

    public SensorExpr(String name,String direction){
        this.name = name;
        this.direction = direction;
    }

    @Override
    public int eval(Map<String,Integer> data){
        switch(name){
            case "virus" ->{}
            case "antibody" ->{}
            case "nearby" ->{}
        }
        return ans;
    }

    @Override
    public void prettyPrint(StringBuilder s) {
        s.append(name);
        s.append("=");
        s.append(ans);
    }

}
