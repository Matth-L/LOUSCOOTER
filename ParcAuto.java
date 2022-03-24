import java.io.*; // permet de traiter les exceptions ainsi que le fichier txt 
import java.util.ArrayList;
import java.util.Scanner;
// import java.util.*;
// import java.lang.*;

public class ParcAuto extends BaseDonne {
    static Scanner scan = new Scanner(System.in);
    static Menu affiche = new Menu(); // affiche le menu

    static Scooter getScooter(ArrayList<Scooter> tabScooter, int id) { // rend un Scooter a partir de son id
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

    static void aMenu(ArrayList<Scooter> tabScooter) throws IOException {
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

    static void testEOS(String s) {
        if (s.equals("EOS")) {
            System.err.println("base de donnée corrompue");
            System.exit(1);
        }
    }

    // crée le tableau a partir de la bd
    static void getDB(ArrayList<Scooter> tab) throws FileNotFoundException {
        File file = new File("bdScooter.txt");
        String tmp;
        Scanner sc = new Scanner(file); // il faut créer un scanner pour le fichier
        while ((sc.hasNextLine()) && !(sc.hasNext("EOF"))) {// tant qu'on est pas au marqueur la fin du fichier
            // tant qu'on est toujours dans le meme scooter
            Scooter temp = new Scooter(); // le pb c'est que tous les scooters s'appellent temp mais ils ont quand meme
                                          // chacun des attributs propres a eux meme a voir si ça pose pb et si ça
                                          // mérite d'être corrigé
            testEOS(tmp = sc.nextLine());
            temp.setId(Integer.parseInt(tmp));

            testEOS(tmp = sc.nextLine());
            temp.setEtat(Boolean.parseBoolean(tmp));

            testEOS(tmp = sc.nextLine());
            temp.setKilometrage(Integer.parseInt(tmp));

            testEOS(tmp = sc.nextLine());
            temp.setMarque(tmp);

            testEOS(tmp = sc.nextLine());
            temp.setModele(tmp);
            tab.add(temp);

            tmp = sc.nextLine();

            if (!(tmp.equals("EOS"))) { // si le fichier ne comprend pas un EOS a la fin
                System.err.println("base de donnée pas bonne ");
                System.exit(1);
            }
        }
        sc.close();
    }

    // permet de sauvegarder les scooters dans un txt
    static void saveDB(ArrayList<Scooter> tab) throws IOException {
        File file = new File("bdScooter.txt"); // écrase les données précedents, pour les garder il faut
                                               // mettre true après le nom du fichier
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);
        for (int count = 0; count < tab.size(); count++) {
            // écrit les attributs de chaque scooters
            pw.println(tab.get(count).getId());
            pw.println(tab.get(count).getEtat());
            pw.println(tab.get(count).getKilometrage());
            pw.println(tab.get(count).getMarque());
            pw.println(tab.get(count).getModele());
            pw.println("EOS");
        }
        pw.println("EOF"); // End of File
        pw.close(); // sans ça rien n'est écrit dans le txt
    }

    public static void main(String[] args) throws IOException { // plein de throws ont été rajouté automatiquement a
                                                                // cause de la lecture du fichier
        ArrayList<Scooter> tabScooter = new ArrayList<Scooter>();
        // setScooterInDB(tabScooter);
        // saveDB(tabScooter);
        getDB(tabScooter); // va chercher les informations a partir du fichier txt
        affiche.flush();
        aMenu(tabScooter);
        affiche.flush();
    }

}