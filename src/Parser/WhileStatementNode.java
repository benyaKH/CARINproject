package Parser;

public class WhileStatementNode implements Node {
    Node expression ;
    Node statement ;
    public  WhileStatementNode(Node expression,Node statement){
        this.expression =expression ;
        this.statement = statement ;
    }
    @Override
    public void prettyPrint(StringBuilder s) {

    }
}
