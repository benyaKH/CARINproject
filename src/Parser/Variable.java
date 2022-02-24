package Parser;
import java.util.Map;

public class Variable implements Expr{
    private String name;
    private Map<String,Integer> data;

    public Variable(String name,Map<String,Integer> data) {
        this.name = name;
        this.data = data;
    }

    public int eval() {
        if (data.containsKey(name))
            return data.get(name);
        throw new EvalError("undefined variable: " + name);
    }
    public void prettyPrint(StringBuilder s) {
    s.append(name);
  }
}
