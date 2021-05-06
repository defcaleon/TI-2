public class PublicKey {
    private int y;
    private int g;
    private int p;


    public PublicKey(int y, int g, int p) {
        this.y = y;
        this.g = g;
        this.p = p;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    @Override
    public String toString() {
        return "PublicKey{" +
                "y=" + y +
                ", g=" + g +
                ", p=" + p +
                '}';
    }
}
