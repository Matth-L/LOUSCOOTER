import java.util.Scanner;

public class Scooter {
    private int id;
    private boolean etat;// True louer, False non louer
    private int kilometrage;
    private String marque;
    private String modele;

    Scooter() {

    }

    Scooter(int nb, boolean e, int km, String mar, String mod) {
        id = nb;
        etat = e;
        kilometrage = km;
        marque = mar;
        modele = mod;
    }

    public int getId() {
        return this.id;
    }

    public boolean getEtat() {
        return this.etat;
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

    public void setId(int x) {
        this.id = x;
    }

    public void setEtat(boolean x) {
        this.etat = x;
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

    public void louer() {
        if (!this.etat) {
            this.etat = true;
            System.out.println("Votre action c'est bien déroulé. Très bon choix!");
        } else {
            System.out.println("Ce scooter est déjà loué...");
        }
    }

    public void retour() {
        Scanner sccan = new Scanner(System.in);
        if (this.etat) {
            System.out.println("Entrez le nb de km effectué : ");
            int i = sccan.nextInt();
            this.setKilometrage(this.kilometrage + i);
            System.out.println(i);
            this.etat = false;
            System.out.println("Le scooter a bien été rendu. Au plaisir de vous revoir!");
        } else {
            System.out.println("Le scooter n'a jamais été en état de location ... ");
        }
    }
}
