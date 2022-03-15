package Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Model.Host;

public class ReadGeneticCode {
    GeneticCodeParser evaluator = new GeneticCodeParser();
    ParserExpr parser = new ParserExpr();
    static Scanner myReader;

    public static void main(String[] args) {
        Host host = new Host();
        ReadGeneticCode RGC = new ReadGeneticCode();
        String src;
        // for(int i=0; i <= 10; i++){
        //     src = RGC.readfile("./src/Parser/GeneticCode.txt");
        //     if(src != null)
        //     RGC.evaluate(src,host);
        //     else break;
        // }
        // src = "if (10 % 10 - 7) then move upleft";
        src = " virusLoc = 50 ";
        RGC.evaluate(src,host);

        //myReader.close();
    }

    public String readfile(String filename){
        try {
            File myObj = new File(filename);
            myReader = new Scanner(myObj);
            if (myReader.hasNextLine()) {
              String src = myReader.nextLine();
              return src;
            }else return null;
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
        return null;
    }

    public String evaluate(String src,Host host){
        Program program = new Program();
        try {
            program = parser.parse(src, host);
        } catch (SyntaxError e) {
            e.printStackTrace();
        }
        System.out.println(evaluator.evalProgram(program, host));
        return "true";
    }
}
