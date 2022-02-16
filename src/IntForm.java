public class IntForm implements Expr{
    private final int val;

    public IntForm(int val) {
        this.val = val;
    }

    public int eval() {
        return val;
    }
}
