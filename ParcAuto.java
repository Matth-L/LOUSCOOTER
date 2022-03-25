import java.io.*; // permet de traiter les exceptions ainsi que le fichier txt 
import java.util.ArrayList;
import java.util.Scanner;

//programme principal 
public class ParcAuto extends BaseDonne {

    static Scanner scan = new Scanner(System.in);
    static Menu affiche = new Menu(); // affiche le menu

    static Scooter getScooter(ArrayList<Scooter> tabScooter, int id) {
        for (int i = 0; i < tabScooter.size(); i++) {
            if (id == tabScooter.get(i).getId()) {
                return tabScooter.get(i);
            }
        }
        return null;
    }

    static public int demandeId() {
        int id;
        System.out.println("Veuillez entrez l'id du scooter: ");
        id = scan.nextInt();
        return id;
    }

    static void choixMenu(ArrayList<Scooter> tabScooter) throws IOException {
        int valInput = 1;
        // menu
        affiche.mainMenu();
        System.out.print("Entrez un nombre : ");
        valInput = scan.nextInt();
        affiche.flush();
        switch (valInput) {
            case 1: // louer le scooter
                affiche.choix1(tabScooter);
                break;
            case 2:// retour scooter
                affiche.choix2(tabScooter);
                break;
            case 3: /// etat
                affiche.choix3(tabScooter);
                break;
            case 4: // afficher état du parc des scooters
                affiche.afficheAll4(tabScooter);
                break;
            case 5: // saisie du parc des scooters
                affiche.afficheStat5(tabScooter);
                break;
            case 6:
                saveDB(tabScooter); // quand on quitte ça sauvegarde dans la bd avant
        }
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Scooter> tabScooter = new ArrayList<Scooter>();
        // setScooterInDB(tabScooter); // ajout des élements dans le tab
        getDB(tabScooter); // va chercher les informations a partir du fichier txt
        affiche.flush();
        choixMenu(tabScooter);
        affiche.flush();
    }

}