package GameState;
public class Pair<T1, T2> {
    protected T1 fst;
    protected T2 snd;

    public Pair(T1 fst, T2 snd) {
        this.fst = fst;
        this.snd = snd;
    }

    public T1 fst() {
        return fst;
    }

    public T2 snd() {
        return snd;
    }

    @Override
    public String toString() {
        return "(" + fst + ", " + snd + ")";
    }
    public boolean equals(Pair<T1,T2> f){
        return (f.fst.equals(this.fst)&&f.snd.equals(this.snd));
    }
    public static void main(String[] args) {
        Pair<Integer,Integer> a = new Pair<Integer,Integer>(4, 4);
        Pair<Integer,Integer> b = new Pair<Integer,Integer>(4, 4);
        System.out.println(a.equals(b));

    }
}
