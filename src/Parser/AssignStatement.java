package Parser;

import java.util.Map;

public class AssignStatement implements Expr{
    private final String identifier;
    private final String op;
    private final Expr expression;
    AssignStatement(String identifier,String op,Expr expression){
        this.identifier = identifier;
        this.op = op;
        this.expression = expression;
    }

    @Override
    public int eval(Map<String,Integer> data) throws EvalError {
        if(!data.containsKey(identifier)){ // set default value
            data.put(identifier,0);
        }
        switch (op) {
            case "=" ->{
                data.put(identifier,expression.eval(data));
                return 0;
            }
        }
        throw new EvalError("Unknown op: " + op);
    }

    @Override
    public void prettyPrint(StringBuilder s) {
        s.append(identifier);
        s.append(op);
        expression.prettyPrint(s);
    }
}
