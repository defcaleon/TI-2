public class PublicKey {
    private int a;
    private int p;
    private int y;


    public PublicKey(int a, int p, int y) {
        this.a = a;
        this.p = p;
        this.y = y;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "PublicKey{" +
                "a=" + a +
                ", p=" + p +
                ", y=" + y +
                '}';
    }
}
