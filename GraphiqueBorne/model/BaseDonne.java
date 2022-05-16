package GraphiqueBorne.model; //le temps du travaille ... 

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class BaseDonne {
    /*
     * une arrayList est un tableau dont la taille est modifié automatiquement et la
     * mémoire géré automatiquement
     */
    ArrayList<Scooter> tabScooter = new ArrayList<Scooter>();

    /*
     * -----------------------------------------------------------------------------
     * création de qq scooter pour mettre dans la base de donnée, pour tester mais
     * faisable directement dans ajout Scooter
     * Appeler que si le ficher n'est pas créer, n'existe pas déjà.
     */
    static Scooter a = new Scooter(1, 40, "Honda", "A");
    static Scooter b = new Scooter(2, 40, "Yamaha", "A");
    static Scooter c = new Scooter(3, 40, "Honda", "A");
    static Scooter d = new Scooter(4, 40, "Yamaha", "A");
    static Scooter e = new Scooter(5, 40, "Yamaha", "A");
    static Scooter f = new Scooter(6, 40, "Honda", "A");
    static Scooter g = new Scooter(7, 40, "Honda", "A");
    static Scooter h = new Scooter(8, 40, "Yamaha", "A");
    static Scooter i = new Scooter(9, 40, "Honda", "A");

    // création de qq location pour les scooters pour mettre dans la base de donnée
    static Location j = new Location(Location.stringToDate("20/10/2021"), Location.stringToDate("23/10/2021"), 1);
    static Location k = new Location(Location.stringToDate("22/5/2021"), Location.stringToDate("30/5/2021"), 2);
    static Location l = new Location(Location.stringToDate("18/01/2021"), Location.stringToDate("20/01/2021"), 4);
    static Location m = new Location(Location.stringToDate("14/9/2021"), Location.stringToDate("19/9/2021"), 5);
    static Location n = new Location(Location.stringToDate("27/12/2021"), Location.stringToDate("28/12/2021"), 72);
    static Location o = new Location(Location.stringToDate("12/3/2021"), Location.stringToDate("13/3/2021"), 88);

    // permet de rajouter les scooters aux tab
    static void setScooterInDB(ArrayList<Scooter> tab) {
        // on ne peut pas faire de boucle car de base le tableau est vide
        tab.add(a);
        tab.add(b);
        tab.add(c);
        tab.add(d);
        tab.add(e);
        tab.add(f);
        tab.add(g);
        tab.add(h);
        tab.add(i);
    }

    // permet de rajouter les locations aux tab
    static void setLocationScoot(ArrayList<Location> tab) {
        tab.add(j);
        tab.add(k);
        tab.add(l);
        tab.add(m);
        tab.add(j);
        tab.add(o);
    }

    // ----------------------------------------------------------------------------------------------------

    /*
     * il y a des marqueurs a la fin du texte cela vérifie juste qu'il n'y a pas eu
     * de problème dans le fichier texte EOS -> End of Scooter
     */
    void testEOS(String s) {
        if (s.equals("EOS")) {
            System.err.println("base de donnée corrompue");
            System.exit(1);
        }
    }
    // ----------------------------------------------------------------------------------------------------

    // * permet de sauvegarder les scooters dans un txt
    public static void saveDB(ArrayList<Scooter> tab) throws IOException {

        // boolean servant a vérifier si le fichier est déja crée
        boolean fileDejaCree = false;

        /*
         * si on souhaitait écraser les données ou append on aurait rajouter un booléan
         * sur le pw
         */

        /*
         * il faut créer un fichier -> un lecteur de fichier -> puis une instance qui va
         * écrire dans le fichier, c'est pw
         */
        File file = new File("GraphiqueBorne/model/baseDonne/bdScooter.txt");
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);
        // on parcourt le tableau et on écrit chaque propriété
        for (Scooter s : tab) {
            pw.println(s.getId());
            pw.println(s.getKilometrage());
            pw.println(s.getMarque());
            pw.println(s.getModele());
            pw.println(s.getEnreparation());
            saveLocation(fileDejaCree, s);// pour mettre également les locations du scooter
            pw.println("EOS");
            fileDejaCree = true;
        }

        pw.println("EOF"); // marqueur de fin EOF -> END OF FILE

        // si pw n'est pas fermé , rien n'est écrit (un peu comme le stringbuilder)
        pw.close();
    }

    /*
     * permet de sauvegarder les locations dans un txt,c'est le meme*
     * fonctionnement globalement que saveScooter on supprime juste le
     * fichier de location a chaque fois
     */
    static void saveLocation(boolean fichierCree, Scooter s) throws IOException {

        File file = new File("GraphiqueBorne/model/baseDonne/location.txt");
        FileWriter fw;

        if (fichierCree) {
            // on efface le fichier lors de la premiere création de location
            fw = new FileWriter(file, true);
        } else {
            fw = new FileWriter(file);
        }

        // une fois que le lecteur de fichier est crée on peut mettre l'écriture
        PrintWriter pw = new PrintWriter(fw);
        // ecrit les location
        for (Location l : s.tabLocation) {
            pw.println(Location.dateToString(l.getDate(true)));
            pw.println(Location.dateToString(l.getDate(false)));
        }

        // EOL -> End Of Location
        pw.println("EOL");
        pw.close();
    }

    // ----------------------------------------------------------------------------------------------------

    // * crée le tableau a partir de la bd
    public void getDB(ArrayList<Scooter> tab) throws FileNotFoundException {

        File file = new File("GraphiqueBorne/model/baseDonne/bdScooter.txt");

        /*
         * il faut créer un scanner pour le fichier
         */
        Scanner sc = new Scanner(file);

        /*
         * tant qu'on est pas au marqueur la fin du fichier et qu'il reste des lignes
         */
        while ((sc.hasNextLine()) && !(sc.hasNext("EOF"))) {
            Scooter temp = new Scooter(); // on crée un scooter et on ajoute les propriété a celui ci
            /*
             * obligé de mettre nextLine pour passer a la prochaine ligne car pour nextInt
             * next Boolean etc il ne fait pas le \n seul
             */
            temp.setId(sc.nextInt());
            sc.nextLine();

            temp.setKilometrage(sc.nextInt());
            sc.nextLine();

            temp.setMarque(sc.nextLine());

            temp.setModele(sc.nextLine());

            temp.setEnreparation(sc.nextBoolean());
            sc.nextLine();

            temp.tabLocation = getLoc(temp, tab.size());
            sc.nextLine();// pour skip le EOS

            // une fois tt les propriété mise on l'ajoute au tab
            tab.add(temp);
        }

        sc.close();
    }

    /*
     * crée le tableau de location a partir du fichier txt fonctionnement similaire
     * getDB
     */
    ArrayList<Location> getLoc(Scooter s, int sizeTab) throws FileNotFoundException {

        File file = new File("GraphiqueBorne/model/baseDonne/location.txt");

        // il faut créer un scanner pour le fichier
        Scanner sc = new Scanner(file);

        Date deb, fin; // on a besoin de deux date temp pour récupérer les informations
        ArrayList<Location> tabLoc = new ArrayList<Location>();

        /*
         * le but est de compter le nombre de EOL qu'on possède car il s'arrete toujours
         * pour ne pas enregistrer les memes premières lignes, pas trouvé de pointeur
         * permettant de reprendre a l'endroit précedant donc bcp de parcours
         */

        int count = 1;
        while (count <= sizeTab) {
            if (sc.hasNext("EOL")) {
                count++;
            }
            sc.nextLine();
        }

        /*
         * tant qu'on est pas au marqueur la fin de location on continue de mettre les
         * locations
         */
        while ((sc.hasNextLine()) && !(sc.hasNext("EOL"))) {
            deb = Location.stringToDate(sc.nextLine());
            fin = Location.stringToDate(sc.nextLine());
            tabLoc.add(new Location(deb, fin, s.getId()));
        }
        sc.close();
        return tabLoc;
    }

    // ----------------------------------------------------------------------------------------------------

    // ajout des élements dans le tab lors de la fermeture du fichier
    public static void setAll(ArrayList<Scooter> tabScooter) throws IOException {

        // on met chaque scooter dans le tableau
        setScooterInDB(tabScooter);

        /*
         * et pour chaque scooter on met les locations (c'est les meme pour tester
         * l'application )
         */
        for (int i = 0; i < tabScooter.size(); i++) {
            setLocationScoot(tabScooter.get(i).tabLocation);
        }

        saveDB(tabScooter);
    }

}