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
}
