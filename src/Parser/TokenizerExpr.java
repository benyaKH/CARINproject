package Parser;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class TokenizerExpr implements Tokenizer{
    private String src;
    private String next;
    private int pos;
    public TokenizerExpr(String src) throws SyntaxError {
        if (src.replace(" ", "").isEmpty()) // Empty String
            throw new NoSuchElementException();
        this.src = src;
        pos = 0;
        computeNext();
    }

    private void computeNext() throws SyntaxError {
        StringBuilder s = new StringBuilder();
        StringTokenizer st = new StringTokenizer(src," ");
        while (st.hasMoreTokens()) {
            String c = st.nextToken();
            if (isNumeric(c)) {  // start of number
                s.append(c);
                for (pos++; pos < src.length() && Character.isDigit(src.charAt(pos)); pos++)
                    s.append(src.charAt(pos));
                break;
            } else if (c == "+" || c == "(" || c == ")" || c == "*" || c == "/" || c == "%" || c == "^") {
                s.append(c);
                break;
            }else if(c == "if" || c == "else" || c == "antibody" || c == "virus" || c == "move" || c == "shoot"){
                s.append(c) ;
            } else throw new SyntaxError("Unknown character: " + c);
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
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
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
