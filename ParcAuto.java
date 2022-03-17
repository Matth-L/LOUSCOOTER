import java.util.ArrayList;
import java.util.Scanner;

public class ParcAuto {

    static ArrayList<Scooter> tabScooter;// L'arrayList qui va servir pour le tableau d'objet

    void afficheScooter3(int id) {
        // tabScooter.get(id) => tabScooter[id] pour une Arraylist
        Scooter scooterDemande = tabScooter.get(id);
        System.out.println("id :" + scooterDemande.getId());
        System.out.println("marque : " + scooterDemande.getMarque());
        System.out.println("modele : " + scooterDemande.getModele());
        System.out.println("kiolmetrage : " + scooterDemande.getKilometrage());
        if (scooterDemande.getEtat()) {
            System.out.println("etat : Libre");
        } else {
            System.out.println("etat : Occupé");
        }
    }

    void afficheAll4() {

    }

    void afficheStat5() {

    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // pour ajouter des scooters dans la base de donnée il faut faire
        tabScooter = new ArrayList<Scooter>();
        // tabScooter.add()

        Menu affiche = new Menu(); // affiche le menu
        int val = 1;
        while (val != 6) {
            // nettoie la console
            System.out.print("\033[H\033[2J");
            System.out.flush();
            // menu
            affiche.menu();
            System.out.print("Entrez un nombre : ");
            val = scan.nextInt();
        }
        scan.close();
    }

}