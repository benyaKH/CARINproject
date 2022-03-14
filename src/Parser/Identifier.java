package Parser;
import java.util.Map;

public class Identifier implements Expr{
    private String name;

    public Identifier(String name) {
        this.name = name;
    }

    public int eval(Map<Expr,Integer> data) {
        if (data.containsKey(name))
            return data.get(name);
        throw new EvalError("undefined Identifier: " + name);
    }
    public void prettyPrint(StringBuilder s) {
    s.append(name);
  }
}
