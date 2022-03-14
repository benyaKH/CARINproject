package Parser;

import java.util.Map;

interface Expr extends Node{
    int eval(Map<Expr,Integer> data);
}
