package Parser;

public class IfStatementNode implements Node {
    Node expression ;
    Node statement ;
    public  IfStatementNode(Node expression,Node statement){
        this.expression = expression ;
        this.statement = statement ;
    }
    @Override
    public void prettyPrint(StringBuilder s){

    }
}
