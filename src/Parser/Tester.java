package Parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Tester {
    @Test
    void normaltest(){
        System.out.println("normaltest");
        String[] test = {"12 + 23 + 25 + 27","23 * ( 26 / 26 ) + 1","1 + 2 * 3 / 9","234 - 4 - 2 - 5 - 56","( ( 5 % 5 ) % 5 )","261200","2 ^ 3"};
        int[] check = {87,24,1,167,0,261200,8};
        assertArrayEquals(check, Runtest.runtest(test,check.length));
        System.out.println();
    }
    @Test
    void errortest(){
        System.out.println("errortest");
        String[] test = {"+","( ( 1 )","28 + 29 -","++",""};
        int[] check = {0,0,0,0,0}; // 0 if test fail
        assertArrayEquals(check, Runtest.runtest(test,check.length));
        System.out.println();
    }
    @Test
    void zerotest(){
        System.out.println("zerotest");
        String[] test = {"2 + 1 / 0","5 + 1 % 0","0 / 1","0 % 1"};
        int[] check = {0,0,0,0};
        assertArrayEquals(check, Runtest.runtest(test,check.length));
        System.out.println();
    }
}
