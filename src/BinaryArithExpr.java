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
    public int eval() throws EvalError {
        int lv = left.eval();
        int rv = right.eval();
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
}
