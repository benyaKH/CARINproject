package Parser;

import java.util.Map;

public class AssignStatement implements Expr{
    private final Expr identifier;
    private final String eq;
    private final Expr expression;
    AssignStatement(Expr identifier,String eq,Expr expression){
        this.identifier = identifier;
        this.eq = eq;
        this.expression = expression;
    }

    @Override
    public int eval(Map<Expr,Integer> data) throws EvalError {
        switch (eq) {
            case "=" ->{
                data.put(identifier,expression.eval(data));
                return 0;
            }
        }
        throw new EvalError("Unknown op: " + eq);
    }

    @Override
    public void prettyPrint(StringBuilder s) {
    }
}
