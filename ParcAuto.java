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
        switch (valInput) {
            case 1: // louer le scooter
                affiche.louerScooter(tabScooter);
                break;
            case 2:// retour scooter
                affiche.retourScooter(tabScooter);
                break;
            case 3: /// etat
                affiche.afficheScooter(tabScooter);
                break;
            case 4: // afficher état du parc des scooters
                affiche.afficheParc(tabScooter);
                break;
            case 5: // saisie du parc des scooters
                affiche.afficheStatParc(tabScooter);
                break;
            case 6:
                saveDB(tabScooter);
                break; // quand on quitte ça sauvegarde dans la bd avant
            default:
                choixMenu(tabScooter);
                break;
        }
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Scooter> tabScooter = new ArrayList<Scooter>();
        // setScooterInDB(tabScooter); // ajout des élements dans le tab
        // saveDB(tabScooter);
        getDB(tabScooter); // va chercher les informations a partir du fichier txt
        affiche.flushS();
        choixMenu(tabScooter);
        affiche.flushS();
    }

}