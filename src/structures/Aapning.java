package structures;

public class Aapning extends HvitRute {
    public Aapning(int rad, int kolonne, Labyrint labyrint) {
        super(rad, kolonne, labyrint);
    }

    @Override
    public int finn(Rute fra, int visit) {
        System.out.println("(" + rad + ", " + kolonne + ")");
        this.visit = visit;
        int nextVisit = visit + 1;

        // Kall finn-metoden på alle naboruter
        if (nord != null && nord != fra) {
            nextVisit = nord.finn(this, nextVisit);
        }
        if (sor != null && sor != fra) {
            nextVisit = sor.finn(this, nextVisit);
        }
        if (vest != null && vest != fra) {
            nextVisit = vest.finn(this, nextVisit);
        }
        if (ost != null && ost != fra) {
            nextVisit = ost.finn(this, nextVisit);
        }
        return nextVisit;
    }

    @Override
    public String toString() {
        return "A";
    }
}