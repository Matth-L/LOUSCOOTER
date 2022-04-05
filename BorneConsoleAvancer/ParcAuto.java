package BorneConsoleAvancer;


import java.io.*; // permet de traiter les exceptions ainsi que le fichier txt 
import java.util.ArrayList;
import java.util.Scanner;

//?ajouter des scooters ("évident") -> Ajouter un mode admin
//? rajouter un fichier bdClient qui contient pour chaque client (nom,prenom) id:l'adresse mail | demande de mpd client |
//? admin : bool -> demande de mdp  
//programme principal 
public class ParcAuto extends BaseDonne {

    static Scanner scan = new Scanner(System.in);
    static Menu affiche = new Menu(); // affiche le menu

    static Scooter getScooter(ArrayList<Scooter> tabScooter, int id) {
        for (Scooter s : tabScooter) { // boucle for Each
            if (id == s.getId()) {
                return s;
            }
        }
        return null;
    }

    static public int demandeId() {
        System.out.println("Veuillez entrez l'id du scooter: ");
        return scan.nextInt();
    }

    // ! choix menu est appelé tous le temps donc on doit rajouter tab client meme
    // ! quand ca semble pas utile je verrais comment changer ça plus tard
    static void choixMenu(ArrayList<Scooter> tabScooter, ArrayList<Client> tabClient) throws IOException {
        // menu
        affiche.mainMenu();
        System.out.print("Entrez un nombre : ");
        switch (scan.nextInt()) {
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
                // quand on quitte ça sauvegarde dans la bd avant
                saveDB(tabScooter);
                saveDBClients(tabClient);
                break;
            default:
                System.out.println("Veuillez entrez un nombre correcte ... ");
                choixMenu(tabScooter, tabClient);
                break;
        }
    }

    public static void main(String[] args) throws IOException {

        ArrayList<Scooter> tabScooter = new ArrayList<Scooter>();
        ArrayList<Client> tabClient = new ArrayList<Client>();

        // setScooterInDB(tabScooter); // ajout des élements dans le tab
        // saveDB(tabScooter);

        // met scooters dans l'arrayList
        getDB(tabScooter);
        // met client dans l'arrayList
        // getDBClients(tabClient);

        affiche.flushS();
        choixMenu(tabScooter, tabClient);
        affiche.flushS();
    }

}