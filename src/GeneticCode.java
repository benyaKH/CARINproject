public interface GeneticCode{
    t = t + 1                                                       //# keeping track of time unit
    virusLoc = virus;
    if (virusLoc / 10 - 1){                                         //then # virus afar
        if (virusLoc % 10 - 7){move(upleft);}                       //then move upleft
        else if (virusLoc % 10 - 6){move(left);}                    //then move left
        else if (virusLoc % 10 - 5){move(downleft);}                //then move downleft
        else if (virusLoc % 10 - 4){move(down);}                    //then move down
        else if (virusLoc % 10 - 3){move(downright);}               //then move downright
        else if (virusLoc % 10 - 2){move(right);}                   //then move right
        else if (virusLoc % 10 - 1){move(upright);}                 //then move upright
        else {move(up);}                                            //move up
    }else if (virusLoc){                                            //then # virus nearby
        if (virusLoc % 10 - 7) {shoot(upleft);}                     //then shoot upleft
        else if (virusLoc % 10 - 6) {shoot(left);}                  //then shoot left
        else if (virusLoc % 10 - 5) {shoot(downleft);}              //then shoot downleft
        else if (virusLoc % 10 - 4) {shoot(down);}                  //then shoot down
        else if (virusLoc % 10 - 3) {shoot(downright);}             //then shoot downright
        else if (virusLoc % 10 - 2) {shoot(right);}                 //then shoot right
        else if (virusLoc % 10 - 1) {shoot(upright);}               //then shoot upright
        else {shoot(up);}                                           //shoot up
    }else{                                                          //# no virus; move in a random direction
        dir = random % 8
        if (dir - 6) {move(upleft);}                                //then move upleft
        else if (dir - 5) {move(left);}                             //then move left
        else if (dir - 4) {move(downleft);}                         //then move downleft
        else if (dir - 3) {move(down);}                             //then move down
        else if (dir - 2) {move(downright);}                        //then move downright
        else if (dir - 1) {move(right);}                            //then move right
        else if (dir) {shoot(upright);}                             //then move upright
        else {shoot(up);}                                           //move up
    }

}