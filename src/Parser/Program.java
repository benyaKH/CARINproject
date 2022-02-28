package Parser;

import java.util.Iterator;
import java.util.*;

public class Program{
    LinkedList<Expr> statementlist = new LinkedList<>();

    public void addStatement(Expr statement){
        statementlist.add(statement);
    }

    public Expr nextStatement(){
        return statementlist.iterator().next();
    }

    public boolean hasNext(){
        return statementlist.iterator().hasNext();
    }

    public void resetIterator(){
        statementlist.clear();
    }
}
