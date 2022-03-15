package Parser;

import java.util.Iterator;
import java.util.*;

public class Program{
    LinkedList<Expr> statementlist = new LinkedList<>();
    Iterator<Expr> itr;

    public void addStatement(Expr statement){
        statementlist.add(statement);
        itr = statementlist.iterator();
    }

    public Expr nextStatement(){
        return itr.next();
    }

    public boolean hasNext(){
        return itr.hasNext();
    }

    public void resetIterator(){
        statementlist.clear();
    }
}
