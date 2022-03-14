package Parser;

import Model.Host;
import java.util.Map;

public class ActionCommand implements Expr{
    private final Host host;
    private final String command;
    private final String direction;

    ActionCommand(Host host,String command,String direction){
        this.host = host;
        this.command = command;
        this.direction = direction;
    }

    @Override
    public int eval(Map<Expr,Integer> data){
        switch(command){
            case "move" -> host.move(direction);
            case "shoot" -> host.shoot(direction);
        }
        return 0;
    }

    @Override
    public void prettyPrint(StringBuilder s) {
    }
}
