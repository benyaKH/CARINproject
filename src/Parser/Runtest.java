package Parser;

import java.util.*;

public class Runtest {
    public static int[] runtest (String[] test,int n){
        int count = 0;
        int[] s = new int[n];
        // Map<String,Integer> data = new HashMap<>();
        // for(String src : test){
        //     try{
        //         ParserExpr parser = new ParserExpr();
        //         StringBuilder strBuild = new StringBuilder();
        //         Expr sum = parser.parse(src);
        //         sum.prettyPrint(strBuild);
        //         System.out.println(strBuild+" = "+sum.eval(data));
        //         s[count] = sum.eval(data);
        //     }catch (SyntaxError | ArithmeticException e){
        //         System.out.println("[" + src + "]" + " : " + e.getMessage());
        //     }catch (NoSuchElementException e){
        //         System.out.println("Empty");
        //     }catch (EvalError e){
        //         System.out.println("[" + src + "] : Incorrect operator");
        //     }
        //     count++;
        // }
        return s;
    }
}
