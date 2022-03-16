package Parser;

import java.util.Map;

import Model.Host;


public class SensorExpr implements Expr{
    String name;
    String direction;
    Host host;
    int ans;

    public SensorExpr(String name,Host host){
        this.name = name;
        this.host = host;
    }

    public SensorExpr(String name,String direction,Host host){
        this.name = name;
        this.direction = direction;
        this.host = host;
    }

    @Override
    public int eval(Map<String,Integer> data){
        ans = host.gameState.location(name,host.getPosition().fst(),host.getPosition().snd());
        return ans;
    }

    @Override
    public void prettyPrint(StringBuilder s) {
        s.append(name);
        s.append("=");
        s.append(ans);
    }

}
