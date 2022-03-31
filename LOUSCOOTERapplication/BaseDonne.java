package LOUSCOOTERapplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

// File file = new File("../baseDonne/bdScooter.txt"); // mal positionné

// tout ce qui traite la base de donnée est la 
public class BaseDonne {
    static Scooter a = new Scooter(1, true, 40, "Honda", "A");
    static Scooter b = new Scooter(2, true, 40, "Yamaha", "A");
    static Scooter c = new Scooter(3, false, 40, "Honda", "A");
    static Scooter d = new Scooter(4, true, 40, "Yamaha", "A");
    static Scooter e = new Scooter(5, false, 40, "Yamaha", "A");
    static Scooter k = new Scooter(14, false, 40, "Honda", "A");
    static Scooter i = new Scooter(25, false, 40, "Honda", "A");
    static Scooter g = new Scooter(72, true, 40, "Yamaha", "A");
    static Scooter f = new Scooter(88, false, 40, "Honda", "A");

    // pour rajouter les scooters aux tab
    static void setScooterInDB(ArrayList<Scooter> tab) {
        tab.add(a);
        tab.add(b);
        tab.add(c);
        tab.add(d);
        tab.add(e);
        tab.add(f);
        tab.add(g);
        tab.add(i);
    }

    // crée le tableau a partir de la bd pour les scooters
    static void getDB(ArrayList<Scooter> tab) throws FileNotFoundException {

        File file = new File("baseDonne/bdScooter.txt");
        // il faut créer un scanner pour le fichier
        Scanner sc = new Scanner(file);

        // tant qu'on est pas au marqueur la fin du fichier
        while ((sc.hasNextLine()) && !(sc.hasNext("EOF"))) {

            // le pb c'est que tous les scooters s'appellent temp mais ils ont quand meme
            Scooter temp = new Scooter();

            // obligé de mettre nextLine pour passer a la prochaine ligne car pour nextInt
            // next Boolean etc il ne fait pas le \n seul

            temp.setId(sc.nextInt());
            sc.nextLine();
            temp.setEtat(sc.nextBoolean());
            sc.nextLine();
            temp.setKilometrage(sc.nextInt());
            sc.nextLine();
            temp.setMarque(sc.nextLine());
            temp.setModele(sc.nextLine());
            sc.nextLine();

            tab.add(temp);
        }
        sc.close();
    }

    // crée le tableau a partir de la bd Client
    static void getDBClients(ArrayList<Client> tab) throws FileNotFoundException {

        File file = new File("baseDonne/bdClient.txt");
        // il faut créer un scanner pour le fichier
        Scanner sc = new Scanner(file);

        // tant qu'on est pas au marqueur la fin du fichier
        while ((sc.hasNextLine()) && !(sc.hasNext("EOF"))) {
            String nom, prenom, mail, mdp;
            nom = sc.nextLine();
            prenom = sc.nextLine();
            mail = sc.nextLine();
            mdp = sc.nextLine(); // a voir pour crypter plus tard
            tab.add(new Client(nom, prenom, mail, mdp));

            // tab.add(new Client(sc.nextLine(), sc.nextLine(),sc.nextLine(),sc.nextLine()))

            // pour passer a traver le EOA
            sc.nextLine();
        }
        sc.close();
    }

    // permet de sauvegarder les scooters dans un txt
    static void saveDB(ArrayList<Scooter> tab) throws IOException {

        File file = new File("baseDonne/bdScooter.txt"); // écrase les données
        // précedents, pour les garder il faut mettre true après le nom du fichier
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);
        for (Scooter s : tab) {
            // écrit les attributs de chaque scooters
            pw.println(s.getId());
            pw.println(s.getEtat());
            pw.println(s.getKilometrage());
            pw.println(s.getMarque());
            pw.println(s.getModele());
            pw.println("EOA");
        }
        // End of File
        pw.println("EOF");
        // sans ça rien n'est écrit dans le txt
        pw.close();
    }

    // crée un marqueur de fin pour les scooters
    static void isMarquer(String s) {
        // EOA -> End Of Array
        if (s.equals("EOA")) {
            System.err.println("base de donnée corrompue");
            System.exit(1);
        }
    }

}