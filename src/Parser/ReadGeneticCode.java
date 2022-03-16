package Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Model.Host;

public class ReadGeneticCode {
    GeneticCodeParser evaluator = new GeneticCodeParser();
    ParserExpr parser;
    StringBuilder strBuild = new StringBuilder();
    static Scanner myReader;

    public static void main(String[] args) {
        Host host = new Host();
        ReadGeneticCode RGC = new ReadGeneticCode();
        //String src = RGC.readfile("./src/Parser/GeneticCode.txt");
        String src = "t = 0 + 1";
        try {
            RGC.evaluate(src,host);
        } catch (SyntaxError e) {
            e.printStackTrace();
        }
        //src = "if (10 % 10 - 7) then move upleft";
        //RGC.evaluate(src,host);
    }

    public String readfile(String filename){
        try {
            File myObj = new File(filename);
            myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              strBuild.append(myReader.nextLine());
            }
            myReader.close();
            return strBuild.toString();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
        return null;
    }

    public String evaluate(String src,Host host) throws SyntaxError{
        parser = new ParserExpr(src, host);
        Program program = parser.parseProgram();
        System.out.println(evaluator.evalProgram(program, host));
        return "true";
    }
}
