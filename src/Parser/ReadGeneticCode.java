package Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Model.Host;

public class ReadGeneticCode {
    GeneticCodeParser evaluator = new GeneticCodeParser();
    ParserExpr parser = new ParserExpr();

    public static void main(String[] args) {
        Host host = new Host();
        ReadGeneticCode RGC = new ReadGeneticCode();
        String src = RGC.readfile("GeneticCode.java");
        RGC.evaluate(src,host);
    }

    public String readfile(String filename){
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            if (myReader.hasNextLine()) {
              String src = myReader.nextLine();
              myReader.close();
              return src;
            }
            myReader.close();
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
