package BorneConsole;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class Scooter {
    static int countScoot = 0;
    private int id;
    private int kilometrage;
    private String marque;
    private String modele;
    // un tableau contenant les locations du scooters
    ArrayList<Location> tabLocation = new ArrayList<Location>();

    Scooter() { // pour le mode admin pas encore testé
        countScoot++;
        id = countScoot;
        kilometrage = 0;
    }

    Scooter(int nb, int km, String mar, String mod) {
        id = nb;
        kilometrage = km;
        marque = mar;
        modele = mod;
    }

    // filtre pour la disponibilité des locations
    boolean isDispo(Date debut, Date fin) {
        for (Location l : tabLocation) {
            // test de la plage
        }
        return false;
    }

    // getteur: \\
    public int getId() {
        return this.id;
    }

    public int getKilometrage() {
        return this.kilometrage;
    }

    public String getMarque() {
        return this.marque;
    }

    public String getModele() {
        return this.modele;
    }

    // setteur \\
    public void setId(int x) {
        this.id = x;
    }

    public void setKilometrage(int x) {
        this.kilometrage = x;
    }

    public void setMarque(String x) {
        this.marque = x;
    }

    public void setModele(String x) {
        this.modele = x;
    }

    // fct:
    public void louer() {
        if (!this.etat) {
            this.setEtat(true);
            System.out.println("Votre action c'est bien déroulé. Très bon choix!");
        } else {
            System.out.println("Ce scooter est déjà loué...");
        }
    }

    public void retour() {
        Scanner scan = new Scanner(System.in);
        if (this.etat) {
            System.out.println("Entrez le nb de km effectué : ");
            this.setKilometrage(this.kilometrage + scan.nextInt());
            this.setEtat(false);
            System.out.println("Le scooter a bien été rendu. Au plaisir de vous revoir!");
        } else {
            System.out.println("Le scooter n'a jamais été en état de location ... ");
        }
        // ! scan.close(); fait crash le programme.
    }

}
