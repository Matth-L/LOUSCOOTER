package BorneConsole;

import java.util.ArrayList;
import java.util.Scanner;

//! MenuMethod -> OptionMenu -> ErreurMenu -> FinalMenu
public class Menu1Method {
    static Scanner scan = new Scanner(System.in);

    Scooter getScooter(ArrayList<Scooter> tabScooter, int id) {
        for (Scooter s : tabScooter) { // boucle for Each
            if (id == s.getId()) {
                return s;
            }
        }
        return null;
    }

    int demandeId() {
        System.out.println("Veuillez entrez l'id du scooter: ");
        return scan.nextInt();
    }

    // se mettre au plus bas dans l'interface de commande(retire le texte des
    // actions précédentes)
    void flushS() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    void clearBoard() { // retire le texte des actions précédantes ap que l'utilisateur est appuyé sur
                        // un touche sauf espace puis affiche le menu.
        System.out.println("appuyer sur n'importe quelle touche pour retourner au menu sauf espace");
        scan.nextLine();
        scan.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // affiche les info du scooter renté en param.
    void infoScooter(Scooter scooterDemande) {
        System.out.println("id :" + scooterDemande.getId());
        System.out.println("marque : " + scooterDemande.getMarque());
        System.out.println("modèle : " + scooterDemande.getModele());
        System.out.println("kilométrage : " + scooterDemande.getKilometrage());
        // affiche les différentes dates
        for (Location l : scooterDemande.tabLocation) {
            // a modifier l'affichage des dates par la suite
            System.out.printf("date de location : %s\n", l.getDate(true));
            System.out.printf("date de retour : %s\n", l.getDate(false));
        }
    }

}
