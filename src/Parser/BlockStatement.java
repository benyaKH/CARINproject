package Parser;

import java.util.*;

public class BlockStatement implements Expr{
    LinkedList<Expr> prossed = new LinkedList<>();

    public BlockStatement(LinkedList<Expr> proseed){
        this.prossed.addAll(proseed);
    }

    @Override
    public int eval(Map<Expr,Integer> data) {
        Iterator itr = prossed.listIterator();
        while(itr.hasNext()){
            Expr statement = (Expr) itr.next();
            statement.eval(data);
        }
        return 0;
    }

    @Override
    public void prettyPrint(StringBuilder s) {
    }
}
