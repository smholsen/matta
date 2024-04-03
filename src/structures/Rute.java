package structures;

public abstract class Rute {
    protected int rad;
    protected int kolonne;
    protected Labyrint labyrint;
    protected Rute nord;
    protected Rute sor;
    protected Rute vest;
    protected Rute ost;
    int visit = -1;

    public Rute(int rad, int kolonne, Labyrint labyrint) {
        this.rad = rad;
        this.kolonne = kolonne;
        this.labyrint = labyrint;
    }

    public void settNaboer(Rute nord, Rute sor, Rute vest, Rute ost) {
        this.nord = nord;
        this.sor = sor;
        this.vest = vest;
        this.ost = ost;
    }

    public abstract int finn(Rute fra, int visit);

    public String visitPrint() {
        if (visit > -1) {
            return padLeft(String.valueOf(visit), 5);
        }
        return "    #";
    }

    public static String padLeft(String s, int n) {
        return String.format("%" + n + "s", s);
    }

    @Override
    public abstract String toString();
}