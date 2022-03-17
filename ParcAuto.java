import java.util.ArrayList;
import java.util.Scanner;

public class ParcAuto {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Scooter> tabScooter = new ArrayList();// L'arrayList qui va servir pour le tableau d'objet
        // pour ajouter des scooters dans la base de donnée il faut faire
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