package structures;

public class Labyrint {
    private final Rute[][] ruter;
    public final int antallRader;
    public final int antallKolonner;

    public Labyrint(int antallRader, int antallKolonner) {
        this.antallRader = antallRader;
        this.antallKolonner = antallKolonner;
        ruter = new Rute[antallRader][antallKolonner];
    }

    public void leggTilRute(int rad, int kolonne, Rute rute) {
        ruter[rad][kolonne] = rute;
    }

    public void finnUtveiFra(int rad, int kol) {
        System.out.println("Utveier:");
        Rute startRute = ruter[rad][kol];
        startRute.finn(null, 1);
    }

    public void printMedVisits() {
        System.out.println("Her er labyrinten slik du gikk gjennom den");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < antallRader; i++) {
            for (int j = 0; j < antallKolonner; j++) {
                sb.append(ruter[i][j].visitPrint());
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public void settNaboer() {
        for (int rad = 0; rad < antallRader; rad++) {
            for (int kol = 0; kol < antallKolonner; kol++) {
                Rute rute = ruter[rad][kol];
                Rute nord = (rad > 0) ? ruter[rad - 1][kol] : null;
                Rute sor = (rad < antallRader - 1) ? ruter[rad + 1][kol] : null;
                Rute vest = (kol > 0) ? ruter[rad][kol - 1] : null;
                Rute ost = (kol < antallKolonner - 1) ? ruter[rad][kol + 1] : null;

                rute.settNaboer(nord, sor, vest, ost);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < antallRader; i++) {
            for (int j = 0; j < antallKolonner; j++) {
                sb.append(ruter[i][j].toString());
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}