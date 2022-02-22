package Parser;

import java.util.NoSuchElementException;

public class Runtest {
    public static int[] runtest (String[] test,int n){
        int count = 0;
        int[] s = new int[n];
        for(String o : test){
            try{
                ParserExpr parser = new ParserExpr(o);
                StringBuilder strBuild = new StringBuilder();
                Expr sum = parser.parseExpr();
                sum.prettyPrint(strBuild);
                System.out.println(strBuild+" = "+sum.eval());
                s[count] = sum.eval();
            }catch (SyntaxError | ArithmeticException e){
                System.out.println("[" + o + "]" + " : " + e.getMessage());
            }catch (NoSuchElementException e){
                System.out.println("Empty");
            }catch (EvalError e){
                System.out.println("[" + o + "] : Incorrect operator");
            }
            count++;
        }
        return s;
    }
}
