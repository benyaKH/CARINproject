package Parser;

import java.util.*;
import Model.Host;

public class ParserExpr {
    private Host host;
    private TokenizerExpr tkz;
    private Program program;
    private static final Set<String> reservedWords = new HashSet<>(Arrays.asList(
            "down", "downleft", "downright", "left", "right",
            "up", "upleft", "upright", "virus", "antibody",
            "while", "else", "if", "shoot", "then", "move", "nearby"
    ));

    /**
     * Program → Statement+
     * Statement → Command | BlockStatement | IfStatement | WhileStatement
     * Command → AssignmentStatement | ActionCommand
     * AssignmentStatement → <identifier> = Expression
     * ActionCommand → MoveCommand | AttackCommand
     * MoveCommand → move Direction
     * AttackCommand → shoot Direction
     * Direction → left | right | up | down | upleft | upright | downleft | downright
     * BlockStatement → { Statement* }
     * IfStatement → if ( Expression ) then Statement else Statement
     * WhileStatement → while ( Expression ) Statement
     * Expression → Expression + Term | Expression - Term | Term
     * Term → Term * Factor | Term / Factor | Term % Factor | Factor
     * Factor → Power ^ Factor | Power
     * Power → <number> | <identifier> | ( Expression ) | SensorExpression
     * SensorExpression → virus | antibody | nearby Direction
     *
     * @throws SyntaxError
     */

    public boolean isNumber(String s){
        try{
            Integer.parseInt(s);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    public Program parse(String src,Host host) throws SyntaxError {
        this.host = host;
        this.tkz = new TokenizerExpr(src);
        program = parseProgram();
        return program;
    }

    // Program → Statement+

    private Program parseProgram() throws SyntaxError {
        Program program = new Program();
        while (!tkz.empty()) {
            program.addStatement(parseStatement());
        }
        return program;
    }

    // Statement → Command | BlockStatement | ifStatement | WhileStatement
    private Expr parseStatement() throws SyntaxError {
        String this_peek = tkz.peek();
        return switch (this_peek) {
            case "{" -> parseBlockStatement();          //BlockStatement
            case "if" -> parseIfStatement();            //ifStatement
            case "while" -> parseWhileStatement();      // WhileStatement
            default -> parseCommand();                  //Command
        };
    }

    // Command → AssignmentStatement | ActionCommand
    private Expr parseCommand() throws SyntaxError {
        String this_peek = tkz.peek();
        // token.consume();
        if (this_peek.equals("move") | this_peek.equals("shoot")) {
            return parseActionCommand();
        } else {
            return parseAssignmentStatement();
        }
    }

    // AssignmentStatement → <identifier> = Expression
    private Expr parseAssignmentStatement() throws SyntaxError {
        String identifier = tkz.consume();
        tkz.consume("=");
        Expr expression = parseExpr();
        if (reservedWords.contains(identifier)) {
            throw new SyntaxError("Syntax Error");
        }else if(identifier.chars().allMatch(Character::isLetterOrDigit)) {
            return new AssignStatement(identifier, "=", expression);
        }else throw new SyntaxError("Syntax Error");
    }

    // ActionCommand → MoveCommand | AttackCommand
    private Expr parseActionCommand() throws SyntaxError {
        String this_peek = tkz.peek();
        if (this_peek.equals("move")) {
            return parseMoveCommand();
        } else if (this_peek.equals("shoot")) {
            return parseAttackCommand();
        } else {
            throw new SyntaxError("Syntax Error");
        }
    }

    // MoveCommand → move Direction
    private Expr parseMoveCommand() throws SyntaxError {
        String this_peek = tkz.peek();
        tkz.consume("move");
        if (this_peek.equals("move")) {
            return new ActionCommand(host,"move", parseDirection());
        } else throw new SyntaxError("Syntax Error");
    }

    // AttackCommand → shoot Direction
    private Expr parseAttackCommand() throws SyntaxError {
        String this_peek = tkz.peek();
        tkz.consume("shoot");
        if (this_peek.equals("shoot")) {
            return new ActionCommand(host,"shoot", parseDirection());
        } else throw new SyntaxError("Syntax Error");
    }


    private Expr parseExpr() throws SyntaxError {
        Expr v = parseE();
        if (tkz.getPos() < tkz.getLength())
            throw new SyntaxError("Syntax Error");
        return v;
    }

    // E → E + T | E - T | T
    private Expr parseE() throws SyntaxError {
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

    // T → T * F | T / F | T % F | F
    private Expr parseT() throws SyntaxError {
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

    // F → F ^ P | P
    private Expr parseF() throws SyntaxError {
        Expr f = parseP();
        while (tkz.peek("^")) {
            String peek = tkz.peek();
            tkz.consume();
            switch (peek) {
                case "^" -> f = new BinaryArithExpr(f, "^", parseP());
            }
        }
        return f;
    }

    // P → n | identifier | ( E ) | SensorExpression  *** identifier
    private Expr parseP() throws SyntaxError {
        String peek = tkz.peek();
        if (isNumber(peek)) {
            return new IntForm(Integer.parseInt(tkz.consume()));
        } else if (peek.equals("(")) {
            tkz.consume("(");
            Expr expression = parseE();
            tkz.consume(")");
            return expression;
        } else if (peek.equals("virus") || peek.equals("antibody") || peek.equals("nearby")) {
            return parseSensor();
        } else {
            return parseIdentifier();
        }
    }

    // Direction → return direction

    private String parseDirection() throws SyntaxError {
        String peek = tkz.consume();
        if (peek.equals("left") || peek.equals("right") || peek.equals("up") || peek.equals("down") || peek.equals("downleft") || peek.equals("downright") || peek.equals("upleft") || peek.equals("upright")) {
            return peek;
        } else {
            throw new SyntaxError("Syntax Error");
        }
    }

    // SensorExpression → virus | antibody | nearby | Direction

    private Expr parseSensor() throws SyntaxError {
        String peek = tkz.peek();
        tkz.consume();
        switch(peek) {
            case "virus" -> {return new SensorExpr("virus");}
            case "antibody" -> {return new SensorExpr("antibody");}
            case "nearby" -> {return new SensorExpr("nearby", parseDirection());}
            default -> throw new SyntaxError("Syntax Error");
        }
    }

    // identifier is valuable

    private Expr parseIdentifier() throws SyntaxError {
        String peek = tkz.peek();
        tkz.consume();
        if (reservedWords.contains(peek)) {
            throw new SyntaxError("Syntax Error");
        }else if(peek.chars().allMatch(Character::isLetterOrDigit)) {
            return new Identifier(peek);
        }
        throw new SyntaxError("Syntax Error");
    }

    // BlockStatement → { Statement* }
    private BlockStatement parseBlockStatement() throws SyntaxError {
        tkz.consume("{");

        LinkedList<Expr> prossed = new LinkedList<>();
        while (!tkz.peek("}")) {
            prossed.add(parseStatement());
        }

        tkz.consume("}");
        return new BlockStatement(prossed);
    }

    // ifStatement → if (Expression) then statement  else statement
    private Expr parseIfStatement() throws SyntaxError {
        tkz.consume("if");
        tkz.consume("(");
        Expr Expression = parseExpr();
        tkz.consume(")");
        tkz.consume("then");
        Expr true_statement = parseStatement();
        tkz.consume("else");
        Expr false_statement = parseStatement();

        return new IfStatement(Expression, true_statement, false_statement);
    }

    // WhileStatement → while ( Expression ) Statement
    private Expr parseWhileStatement() throws SyntaxError {
        tkz.consume("while");
        Expr Expression = parseExpr();

        Expr true_statement = parseStatement();

        return new WhileStatement(Expression, true_statement);
    }
}
