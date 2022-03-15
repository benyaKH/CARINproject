package Parser;

import java.util.Map;

public class AssignStatement implements Expr{
    private final Expr identifier;
    private final String op;
    private final Expr expression;
    AssignStatement(Expr identifier,String op,Expr expression){
        this.identifier = identifier;
        this.op = op;
        this.expression = expression;
    }

    @Override
    public int eval(Map<String,Integer> data) throws EvalError {
        switch (op) {
            case "=" ->{
                data.put(identifier.toString(),expression.eval(data));
                return 0;
            }
        }
        throw new EvalError("Unknown op: " + op);
    }

    @Override
    public void prettyPrint(StringBuilder s) {
        s.append(identifier.toString());
        s.append(op);
        expression.prettyPrint(s);
    }
}
