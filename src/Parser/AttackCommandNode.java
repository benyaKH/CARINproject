package Parser;

import Model.Host;

public class AttackCommandNode {
    String direction ;
    Host host ;
    public AttackCommandNode(String direction,Host e){
        this.direction = direction ;
        this.host = e ;
    }
}
