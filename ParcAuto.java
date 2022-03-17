import java.util.ArrayList;
import java.util.Scanner;

public class ParcAuto {

    static ArrayList<Scooter> tabScooter;// L'arrayList qui va servir pour le tableau d'objet

    static Scooter getScooter(int id) {
        for (int i = 0; i < tabScooter.size(); i++) {
            if (id == tabScooter.get(i).getId()) {
                return tabScooter.get(i);
            }
        }
        return null;
    }

    static void afficheScooter3(int id) {

        Scooter scooterDemande = getScooter(id);
        if (scooterDemande == null) {
            // fonction demander id
        }
        System.out.println("id :" + scooterDemande.getId());
        System.out.println("marque : " + scooterDemande.getMarque());
        System.out.println("modele : " + scooterDemande.getModele());
        System.out.println("kiolmetrage : " + scooterDemande.getKilometrage());
        if (scooterDemande.getEtat())

        {
            System.out.println("etat : Libre");
        } else {
            System.out.println("etat : Occupé");
        }
    }

    void afficheAll4() {
        // affiche tous les soucteurs disopnibles ou non.
    }

    void afficheStat5() {
    // permet d'afficher :
    //Le Nombre total de scooters
    // Le Nombre de scooters en location et leur N° d’identification,
    //Le Nombre de scooters disponibles et leur N° d’identification,
    //Le kilométrage moyen de l’ensemble des scooter
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // pour ajouter des scooters dans la base de donnée il faut faire
        tabScooter = new ArrayList<Scooter>();
        // tabScooter.add()

        Scooter a = new Scooter();
        a.setId(0);
        a.setKilometrage(0);
        a.setMarque("honda");
        a.setModele("x");
        a.setEtat(true);
        Menu affiche = new Menu(); // affiche le menu
        int val = 1;
        tabScooter.add(a);
        afficheScooter3(0);
        // nettoie la console
        System.out.print("\033[H\033[2J");
        System.out.flush();
        // menu
        affiche.menu();
        System.out.print("Entrez un nombre : ");
        val = scan.nextInt();
        switch (val) {
            case 1: // demander le scan et changer l'état
            case 2: // retour scooter
            case 3: /// etat
            case 4: // afficher état du parc des scooters
            case 5: // saisie du parc des scooters
                // si c'est 6 ça close de base si on le rappel pas
        }

        scan.close();
    }

}