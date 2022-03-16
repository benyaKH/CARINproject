package Parser;

import java.util.Map;

public class IfStatement implements Expr{
    protected Expr Expression;
    Expr true_statement;
    Expr false_statement;
    boolean check = false;

    public IfStatement(Expr Expression, Expr true_statement,Expr false_statement){
        this.Expression = Expression;
        this.true_statement = true_statement;
        this.false_statement = false_statement;
    }

    @Override
    public int eval(Map<String,Integer> data){
        if(Expression.eval(data) != 0){
            check = true;
            return true_statement.eval(data);
        }else return false_statement.eval(data);
    }

    @Override
    public void prettyPrint(StringBuilder s) {
        s.append("if");
        Expression.prettyPrint(s);
        s.append("{");
        if(check){
            s.append("then ");
            true_statement.prettyPrint(s);
        }
        else {
            s.append("else ");
            false_statement.prettyPrint(s);
        }
        s.append("}");
    }
}
