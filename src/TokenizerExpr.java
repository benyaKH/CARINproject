public class TokenizerExpr implements Tokenizer{
    private String src;
    private String next;
    private int pos;
    public TokenizerExpr(String src) throws SyntaxError {
        this.src = src;
        pos = 0;
        computeNext();
    }

    private void computeNext() throws SyntaxError {
        StringBuilder s = new StringBuilder();
        while (pos < src.length() && Character.isWhitespace(src.charAt(pos))) pos++;  // ignore whitespace
        if (pos < src.length()) {
            char c = src.charAt(pos);
            if (Character.isDigit(c)) {  // start of number
                s.append(c);
                for (pos++; pos < src.length() &&
                        Character.isDigit(src.charAt(pos)); pos++)
                    s.append(src.charAt(pos));
            } else if (Character.isLetter(c)) {  // start of string
                s.append(c);
                pos++;
            }
            else if (c == '+' || c == '(' || c == ')' || c == '*' || c == '/' || c == '%' || c == '^') {
                s.append(c);
                pos++;
            } else throw new SyntaxError("Syntax Error");
        }
        next = s.toString();

    }

    public boolean peek(String s){
        if(!src.isEmpty()) return peek().equals(s);
        return false;
    }

    public void consume(String s) throws SyntaxError {
        if(peek(s)) {
            consume();
        }else
            throw new SyntaxError("Syntax Error");
    }

    public int getPos(){
        return pos;
    }

    public int getLength(){
        return src.length();
    }

    @Override
    public String peek() { return next; }
    @Override
    public String consume() throws SyntaxError {
        String result = next;
        computeNext();
        return result;
    }
}
