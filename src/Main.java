import structures.Aapning;
import structures.HvitRute;
import structures.Labyrint;
import structures.SortRute;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Main <filename>");
            return;
        }

        String filename = args[0];
        Labyrint lab = readLabyrintFromFile(filename);

        if (lab != null) {
            System.out.println("Slik ser labyrinten ut:");
            System.out.println(lab);

            // Les brukerinput og finn utveier fra angitt startpunkt
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Skriv inn koordinater <rad> <kolonne> ('-1' for aa avslutte)");
                int rad = scanner.nextInt();
                if (rad == -1) {
                    break;
                }
                int kol = scanner.nextInt();
                if (rad < 0 || rad >= lab.antallRader || kol < 0 || kol >= lab.antallKolonner) {
                    System.out.println("Ugyldige koordinater.");
                    continue;
                }
                lab.finnUtveiFra(rad, kol);
                lab.printMedVisits();
            }
            scanner.close();
        }
    }

    private static Labyrint readLabyrintFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();
            String[] dimensions = line.split(" ");
            int antallRader = Integer.parseInt(dimensions[0]);
            int antallKolonner = Integer.parseInt(dimensions[1]);

            Labyrint lab = new Labyrint(antallRader, antallKolonner);

            for (int rad = 0; rad < antallRader; rad++) {
                line = reader.readLine();
                for (int kolonne = 0; kolonne < antallKolonner; kolonne++) {
                    char symbol = line.charAt(kolonne);
                    if (symbol == '#') {
                        lab.leggTilRute(rad, kolonne, new SortRute(rad, kolonne, lab));
                    } else if (symbol == '.') {
                        if (rad == 0 || rad == antallRader - 1 || kolonne == 0 || kolonne == antallKolonner - 1) {
                            lab.leggTilRute(rad, kolonne, new Aapning(rad, kolonne, lab));
                        } else {
                            lab.leggTilRute(rad, kolonne, new HvitRute(rad, kolonne, lab));
                        }
                    }
                }
            }
            lab.settNaboer();

            return lab;
        } catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Feil ved lesing av fil: " + e.getMessage());
            return null;
        }
    }
}