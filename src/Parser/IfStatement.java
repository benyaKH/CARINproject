package Parser;

import java.util.Map;

public class IfStatement implements Expr{
    protected Expr Expression;
    Expr true_statement;
    Expr false_statement;

    public IfStatement(Expr Expression, Expr true_statement,Expr false_statement){
        this.Expression = Expression;
        this.true_statement = true_statement;
        this.false_statement = false_statement;
    }

    @Override
    public int eval(Map<Expr,Integer> data){
        if(Expression.eval(data) == 0){
            return true_statement.eval(data);
        }else return false_statement.eval(data);
    }

    @Override
    public void prettyPrint(StringBuilder s) {
    }
}
