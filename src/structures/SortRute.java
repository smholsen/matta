package structures;

public class SortRute extends Rute {
    public SortRute(int rad, int kolonne, Labyrint labyrint) {
        super(rad, kolonne, labyrint);
    }

    @Override
    public int finn(Rute fra, int visit) {
        if (fra == null) {
            System.out.println("Kan ikke starte i sort rute");
        }
        return visit;
    }

    @Override
    public String toString() {
        return "#";
    }
}