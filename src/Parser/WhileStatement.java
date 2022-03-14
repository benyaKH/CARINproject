package Parser;

import java.util.Map;

public class WhileStatement implements Expr{
    private final Expr Expression;
    private final Expr true_statement;

    WhileStatement(Expr Expression,Expr true_statement){
        this.Expression = Expression;
        this.true_statement = true_statement;
    }

    @Override
    public int eval(Map<Expr,Integer> data){
        while (Expression.eval(data) == 0) {
            true_statement.eval(data);
        }
        return 0;
    }

    @Override
    public void prettyPrint(StringBuilder s) {
    }
}
