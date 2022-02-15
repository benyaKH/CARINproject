public class ParserExpr {
    private final TokenizerExpr tkz;

    public ParserExpr(String src) throws SyntaxError {
        this.tkz = new TokenizerExpr(src);
    }

    // SensorExpression → virus | antibody | nearby | Direction
    public Expr SensorExpr() throws SyntaxError {
        return null;
    }

    // P → n | identifier | ( E ) | SensorExpression  *** identifier
    public Expr parseP() throws SyntaxError {
        String peek = tkz.peek();
        if (isNumber(peek)) {
            return new IntForm(Integer.parseInt(tkz.consume()));
        }else if(true){
            return null;
        }else if(peek.equals("(")){
            tkz.consume("(");
            Expr p = parseE();
            tkz.consume(")");
            return p;
        }else{
            Expr ss = SensorExpr();
            return ss;
        }
    }

    // F → F ^ P | P
    public Expr parseF() throws SyntaxError {
        Expr p = parseP();
        while (tkz.peek("^")) {
            String peek = tkz.peek();
            tkz.consume();
            switch (peek) {
                case "^" -> p = new BinaryArithExpr(p, "^", parseP());
            }
        }
        return p;
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
