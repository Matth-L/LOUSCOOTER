import java.io.*; // permet de traiter les exceptions ainsi que le fichier txt 
import java.util.ArrayList;
import java.util.Scanner;

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

    static void afficheScooter3(Scooter scooterDemande) {

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

    static void afficheAll4(ArrayList<Scooter> tabScooter) throws IOException {
        // affiche tous les scooters
        for (int i = 0; i < tabScooter.size(); i++) {
            afficheScooter3(tabScooter.get(i));
        }
        aMenu(tabScooter);
    }

    static void afficheStat5(ArrayList<Scooter> tabScooter) throws IOException {

        int louer = 0;
        int kilometrage = 0;
        // Le Nombre total de scooters
        System.out.println("Nombre total de scooter : " + tabScooter.size());
        // Le Nombre de scooters en location et leur N° d’identification,
        for (int i = 0; i < tabScooter.size(); i++) {
            if (tabScooter.get(i).getEtat()) {
                louer++;
            }
            kilometrage += tabScooter.get(i).getKilometrage();
        }
        System.out.println("Nombre de scooter en location :" + louer);
        // Le Nombre de scooters disponibles et leur N° d’identification
        System.out.println("Nombre de scooter disponible :" + (tabScooter.size() - louer));
        // Le kilométrage moyen de l’ensemble des scooter
        System.out.println("Kilometrage moyen : " + (kilometrage / tabScooter.size()));
        aMenu(tabScooter);
    }

    static void choix1(ArrayList<Scooter> tabScooter) throws IOException {
        Scooter S;
        S = getScooter(tabScooter, demandeId());
        if (S != null) {
            S.louer();
            aMenu(tabScooter);
        } else {
            int choix; // si id fausse!
            System.out.println("Ce scooter n'est pas dans la base de donnée");
            System.err.println(
                    "Que voulez-vous faire: \n 1) rentrer une autre id \n 2) retourner au menu \n 3) quitter ");
            choix = scan.nextInt();
            switch (choix) {
                case 1:
                    choix1(tabScooter);
                    break;
                case 2:
                    // retourner au menu
                    aMenu(tabScooter);
                    break;
                case 3:
                    // quitter
                    break;
                default:
                    aMenu(tabScooter);
                    System.out.println("valeurs rentrée incorecte, retour au menu.");
                    break;
            }
        }
    }

    static void choix2(ArrayList<Scooter> tabScooter) throws IOException {
        Scooter S;
        S = getScooter(tabScooter, demandeId());
        if (S != null) {
            S.retour();
            aMenu(tabScooter);
        } else {
            int choix;
            System.out.println("Ce scooter n'est pas dans la base de donnée");
            System.err.println(
                    "Que voulez-vous faire: \n 1) rentrer une autre id \n 2) retourner au menu \n 3) quitter ");
            choix = scan.nextInt();
            switch (choix) {
                case 1:
                    choix2(tabScooter);
                    break;
                case 2:
                    // retourner au menu
                    aMenu(tabScooter);
                    break;
                case 3:
                    // quitter
                    break;
                default:
                    aMenu(tabScooter);
                    System.out.println("valeurs rentrée incorecte, retour au menu.");
                    break;
            }
        }
    }

    static void choix3(ArrayList<Scooter> tabScooter) throws IOException {
        Scooter S;
        S = getScooter(tabScooter, demandeId());
        if (S != null) {
            afficheScooter3(S);
            aMenu(tabScooter);
        } else {
            int choix;
            System.out.println("Ce scooter n'est pas dans la base de donnée");
            System.err.println(
                    "Que voulez-vous faire: \n 1) rentrer une autre id \n 2) retourner au menu \n 3) quitter ");
            choix = scan.nextInt();
            switch (choix) {
                case 1:
                    choix3(tabScooter);
                    break;
                case 2:
                    // retourner au menu
                    aMenu(tabScooter);
                    break;
                case 3:
                    // quitter
                    break;
                default:
                    aMenu(tabScooter);
                    System.out.println("valeurs rentrée incorecte, retour au menu.");
                    break;
            }
        }
    }

    static void flush() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void aMenu(ArrayList<Scooter> tabScooter) throws IOException {
        int valInput = 1;
        // menu
        affiche.menu();
        System.out.print("Entrez un nombre : ");
        valInput = scan.nextInt();
        flush();
        switch (valInput) {
            case 1: // louer le scooter
                choix1(tabScooter);
                break;
            case 2:// retour scooter
                choix2(tabScooter);
                break;
            case 3: /// etat
                choix3(tabScooter);
                break;
            case 4: // afficher état du parc des scooters
                afficheAll4(tabScooter);
                break;
            case 5: // saisie du parc des scooters
                afficheStat5(tabScooter);
                break;
            case 6:
                saveDB(tabScooter); // quand on quitte ça sauvegarde dans la bd avant

        }
    }

    // crée le tableau a partir de la bd
    static void getDB(ArrayList<Scooter> tab) throws FileNotFoundException {
        File file = new File("bdScooter.txt");
        Scanner sc = new Scanner(file); // il faut créer un scanner pour le fichier
        while ((sc.hasNextLine()) && !(sc.hasNext("EOF"))) {// tant qu'on est pas au marqueur la fin du fichier
            // tant qu'on est toujours dans le meme scooter
            Scooter temp = new Scooter(); // le pb c'est que tous les scooters s'appellent temp mais ils ont quand meme
                                          // chacun des attributs propres a eux meme a voir si ça pose pb et si ça
                                          // mérite d'être corrigé
            temp.setId(sc.nextInt());
            sc.nextLine(); // obligé de mettre nextLine pour passer a la prochaine ligne car pour nextInt
                           // next Boolean etc il ne fait pas le \n seul
            temp.setEtat(sc.nextBoolean());
            sc.nextLine();
            temp.setKilometrage(sc.nextInt());
            sc.nextLine();
            temp.setMarque(sc.nextLine());
            temp.setModele(sc.nextLine());
            tab.add(temp);
        }
        sc.close();
    }

    static void saveDB(ArrayList<Scooter> tab) throws IOException { // permet de sauvegarder les scooters dans un txt
        File file = new File("bdScooter.txt"); // écrase les données précedents par contre pour les garder il faut
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
            // on pourrait mettre un marqueur de fin pour les scooters mais bon
        }
        pw.println("EOF"); // End of File
        pw.close(); // sans ça rien n'est écrit dans le txt
    }

    static void setScooterInDB(ArrayList<Scooter> tab) { // pour rajouter les scooters aux tab
        tab.add(a);
        tab.add(b);
        tab.add(c);
        tab.add(d);
        tab.add(e);
        tab.add(f);
        tab.add(g);
        tab.add(h);
        tab.add(i);
        tab.add(j);

    }

    public static void main(String[] args) throws IOException { // plein de throws ont été rajouté automatiquement a
                                                                // cause de la lecture du fichier
        // pour ajouter des scooters dans la base de donnée il faut faire
        ArrayList<Scooter> tabScooter = new ArrayList<Scooter>();
        getDB(tabScooter); // va chercher les informations a partir du fichier txt
        flush();
        aMenu(tabScooter);
        flush();
    }

}