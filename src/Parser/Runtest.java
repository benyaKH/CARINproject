package Parser;

import java.util.NoSuchElementException;
import java.util.HashMap;
import java.util.Map;

public class Runtest {
    public static int[] runtest (String[] test,int n){
        int count = 0;
        int[] s = new int[n];
        Map<String,Integer> data = new HashMap<>();
        for(String o : test){
            try{
                ParserExpr parser = new ParserExpr(o);
                StringBuilder strBuild = new StringBuilder();
                Expr sum = parser.parseExpr();
                sum.prettyPrint(strBuild);
                System.out.println(strBuild+" = "+sum.eval(data));
                s[count] = sum.eval(data);
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
