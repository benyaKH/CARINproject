package Parser;

import java.util.*;

public class ParserExpr {
    private TokenizerExpr tkz;
    private Map<String,Integer> data = new HashMap<>();
    private Program program;
    private static final Set<String> reservedWords = new HashSet<>(Arrays.asList(
            "down", "downleft", "downright", "left", "right",
            "up", "upleft", "upright", "virus", "antibody",
            "while", "else", "if", "shoot", "then", "move", "nearby"
    ));

    public ParserExpr(String src) throws SyntaxError {
        this.tkz = new TokenizerExpr(src);
    }

    public boolean isNumber(String s){
        try{
            Integer.parseInt(s);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
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
    private Statement parseStatement() throws SyntaxError {
        String this_peek = tkz.peek();
        return switch (this_peek) {
            case "{" -> parseBlockStatement();          //BlockStatement
            case "if" -> parseIfStatement();            //ifStatement
            case "while" -> parseWhileStatement();      // WhileStatement
            default -> parseCommand();                  //Command
        };
    }

    // Command → AssignmentStatement | ActionCommand
    private Statement parseCommand() throws SyntaxError {
        String this_peek = tkz.peek();
        // token.consume();
        if (this_peek.equals("move") | this_peek.equals("shoot")) {
            return parseActionCommand();
        } else {
            return parseAssignmentStatement();
        }
    }

    // AssignmentStatement → <identifier> = Expression
    private Statement parseAssignmentStatement() throws SyntaxError {
        Statement identifier = parseIdentifier();
        tkz.consume_check("=");
        Statement expression = parseExpr();
        return new AssignStatement(identifier, "=", expression);
    }

    // ActionCommand → MoveCommand | AttackCommand
    private Statement parseActionCommand() throws SyntaxError {
        String this_peek = tkz.peek();
        if (this_peek.equals("move")) {
            return parseMoveCommand();
        } else if (this_peek.equals("shoot")) {
            return parseAttackCommand();
        } else {
            throw new SyntaxError();
        }
    }

    // MoveCommand → move Direction
    private Statement parseMoveCommand() throws SyntaxError {
        String this_peek = tkz.peek();
        tkz.consume_check("move");
        if (this_peek.equals("move")) {
            return new ActionCommand("move", parseDirection());
        } else throw new SyntaxError();
    }

    // AttackCommand → shoot Direction
    private Statement parseAttackCommand() throws SyntaxError {
        String this_peek = tkz.peek();
        tkz.consume_check("shoot");
        if (this_peek.equals("shoot")) {
            return new ActionCommand("shoot", parseDirection());
        } else throw new SyntaxError();
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
            case "virus" ->  return GameState.location("virus",x,y);
            case "antibody" -> return GameState.location("antibody",x,y);
            case "nearby" ->  return GameState.location("nearby",x,y,parseDirection());
            default -> throw new SyntaxError("Syntax Error");
        }
    }

    // identifier is valuable

    private Expr parseIdentifier() throws SyntaxError {
        String peek = tkz.peek();
        tkz.consume();
        if (reservedWords.contains(peek)) {
            throw new SyntaxError("Syntax Error");
        }else if (!isNumber("" + peek.charAt(0))) {
            // If after this_peak[0] are alphanumeric
            if (peek.substring(1).chars().allMatch(Character::isLetterOrDigit)) {
                return new Identifier(peek);
            }
        }
        throw new SyntaxError("Syntax Error");
    }

    // BlockStatement → { Statement* }
    private BlockStatement parseBlockStatement() throws SyntaxError {
        tkz.consume("{");

        LinkedList<Statement> prossed = new LinkedList<>();
        while (!tkz.peek("}")) {
            prossed.add(parseStatement());
        }

        tkz.consume("}");
        return new BlockStatement(prossed);
    }

    // ifStatement → if (Expression) then statement  else statement
    private Statement parseIfStatement() throws SyntaxError {
        tkz.consume("if");
        tkz.consume("(");
        Statement Expression = parseExpr();
        tkz.consume(")");
        tkz.consume("then");
        Statement true_statement = parseStatement();
        tkz.consume("else");
        Statement false_statement = parseStatement();

        return new IfStatement(Expression, true_statement, false_statement);
    }

    // WhileStatement → while ( Expression ) Statement
    private Statement parseWhileStatement() throws SyntaxError {
        tkz.consume("while");
        Statement Expression = parseExpression();

        Statement true_statement = parseStatement();

        return new WhileStatement(Expression, true_statement);
    }
}
