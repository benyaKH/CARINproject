package Parser;
import java.util.Map;

public class BinaryArithExpr implements Expr{
    private final Expr left;
    private final Expr right;
    private final String op;

    public BinaryArithExpr(Expr left, String op, Expr right) {
        this.left = left;
        this.op = op;
        this.right = right;
    }

    @Override
    public int eval(Map<String, Integer> bindings) throws EvalError {
        int lv = left.eval(bindings);
        int rv = right.eval(bindings);
        switch (op) {
            case "+":
                return lv + rv;
            case "-":
                return lv - rv;
            case "*":
                return lv * rv;
            case "/":
                return lv / rv;
            case "%":
                return lv % rv;
            case "^":
                return (int) Math.pow(lv, rv);
        }
        throw new EvalError("Unknown op: " + op);
    }

    @Override
    public void prettyPrint(StringBuilder s) {
        s.append("(");
        left.prettyPrint(s);
        s.append(op);
        right.prettyPrint(s);
        s.append(")");
    }
}
