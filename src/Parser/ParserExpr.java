package Parser;

import java.util.HashMap;
import java.util.Map;

public class ParserExpr {
    private final TokenizerExpr tkz;
    private Map<String,Integer> data = new HashMap<>();

    public ParserExpr(String src) throws SyntaxError {
        this.tkz = new TokenizerExpr(src);
    }

    public void parseDirection() throws SyntaxError {
        String peek = tkz.peek();
        switch(peek) {
            case "up" ->
            case "upright" ->
            case "right" ->
            case "downright" ->
            case "down" ->
            case "downleft" ->
            case "left" ->
            case "upleft" ->
        }
    }

    // SensorExpression → virus | antibody | nearby | Direction
    // public Parser.Expr SensorExpr() throws Parser.SyntaxError {
    //     return null;
    // }
    public Expr parseSensor() throws SyntaxError {
        String peek = tkz.peek();
        switch(peek) {
            case "virus" -> {
                tkz.consume();
                return Virus(x,y).location();
            }
            case "antibody" -> {
                tkz.consume();
                return Antibody(x,y).location();
            }
            case "nearby" -> {
                tkz.consume();
                return Nearby(x,y,parseDirection());
            }
        }

        throw new SyntaxError("Syntax Error");
    }

    // P → n | identifier | ( E ) | SensorExpression  *** identifier
    public Expr parseP() throws SyntaxError {
        String peek = tkz.peek();
        if (isNumber(peek)) {
            return new IntForm(Integer.parseInt(tkz.consume()));
        }else if (!peek.equals("(") && !peek.equals(")") && !peek.equals("virus") && !peek.equals("antibody") && !peek.equals("nearby")) {
            String name = tkz.consume();
            Expr p = new Variable(name, data);
            tkz.consume("=");
            data.put(name, parseE().eval());
            return p;
        }else if (peek.equals("(")){
            tkz.consume("(");
            Expr p = parseE();
            tkz.consume(")");
            return p;
        }else{
            Expr ps = parseSensor();
            return ps;
        }
    }

    // F → F ^ P | P
    public Expr parseF() throws SyntaxError {
        Expr f = parseP();
        while (tkz.peek("^")) {
            String peek = tkz.peek();
            tkz.consume();
            switch (peek) {
                case "^" -> f = new BinaryArithExpr(f, "^", parseP());
            }
        }
        return f;
    }

    // T → T * F | T / F | T % F | F
    public Expr parseT() throws SyntaxError {
        Expr t = parseF();
        while (tkz.peek("*") || tkz.peek("/") || tkz.peek("%")) {
            String peek = tkz.peek();
            tkz.consume();
            switch (peek) {
                case "*" -> t = new BinaryArithExpr(t, "*", parseF());
                case "/" -> t = new BinaryArithExpr(t, "/", parseF());
                case "%" -> t = new BinaryArithExpr(t, "%", parseF());
            }
        }
        return t;
    }

    // E → E + T | E - T | T
    public Expr parseE() throws SyntaxError {
        Expr e = parseT();
        while (tkz.peek("+") || tkz.peek("-")) {
            String peek = tkz.peek();
            tkz.consume();
            switch (peek) {
                case "+" -> e = new BinaryArithExpr(e, "+", parseT());
                case "-" -> e = new BinaryArithExpr(e, "-", parseT());
            }
        }
        return e;
    }

    public Expr parseExpr() throws SyntaxError {
        Expr v = parseE();
        if (tkz.getPos() < tkz.getLength())
            throw new SyntaxError("Syntax Error");
        return v;
    }

    public boolean isNumber(String s){
        try{
            Integer.parseInt(s);
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }

}
