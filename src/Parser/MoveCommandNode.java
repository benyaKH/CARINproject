package Parser;

import Model.Host;

public class MoveCommandNode {
    String direction ;
    Host host ;
    public MoveCommandNode(String direction, Host e){
        this.direction = direction ;
        this.host = e ;
    }
}