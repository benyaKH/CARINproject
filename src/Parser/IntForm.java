package Parser;
import java.util.Map;

public class IntForm implements Expr{
    private final int val;

    public IntForm(int val) {
        this.val = val;
    }

    public int eval(Map<String, Integer> bindings) {
        return val;
    }

    public void prettyPrint(StringBuilder s) {
        s.append(val);
    }
}
