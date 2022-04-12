package BorneConsole; //le temps du travaille ... 

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

// tout ce qui traite la base de donnée est la 
public class BaseDonne {
    static Scooter a = new Scooter(1, 40, "Honda", "A");
    static Scooter b = new Scooter(2, 40, "Yamaha", "A");
    static Scooter c = new Scooter(3, 40, "Honda", "A");
    static Scooter d = new Scooter(4, 40, "Yamaha", "A");
    static Scooter e = new Scooter(5, 40, "Yamaha", "A");
    static Scooter f = new Scooter(14, 40, "Honda", "A");
    static Scooter g = new Scooter(25, 40, "Honda", "A");
    static Scooter h = new Scooter(72, 40, "Yamaha", "A");
    static Scooter i = new Scooter(88, 40, "Honda", "A");

    // pour rajouter les scooters aux tab
    static void setScooterInDB(ArrayList<Scooter> tab) {
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

    // crée le tableau a partir de la bd
    static void getDB(ArrayList<Object> tab) throws FileNotFoundException {
        // File file = new File("../baseDonne/bdScooter.txt"); // si on doit le lancé
        // dans le fichier console
        tab.getClass();
        File file = new File("baseDonne/bdScooter.txt");
        Scanner sc = new Scanner(file); // il faut créer un scanner pour le fichier
        while ((sc.hasNextLine()) && !(sc.hasNext("EOF"))) {// tant qu'on est pas au marqueur la fin du fichier
            // tant qu'on est toujours dans le meme scooter
            Scooter temp = new Scooter(); // le pb c'est que tous les scooters s'appellent temp mais ils ont quand
                                          // meme
            // chacun des attributs propres a eux meme a voir si ça pose pb et si ça
            // mérite d'être corrigé
            temp.setId(sc.nextInt());
            // obligé de mettre nextLine pour passer a la prochaine ligne car pour nextInt
            // next Boolean etc il ne fait pas le \n seul
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

    static void testEOS(String s) {
        if (s.equals("EOS")) {
            System.err.println("base de donnée corrompue");
            System.exit(1);
        }
    }

    // permet de sauvegarder les scooters dans un txt
    // !0 pour Scooter 1 pour locatoin
    static void saveDB(ArrayList<Object> tab) throws IOException {
        // new File("../baseDonne/bdScooter.txt"); // si on est pas dans console
        File file = new File("baseDonne/bdScooter.txt"); // écrase les données
        // précedents, pour les garder il faut
        // mettre true après le nom du fichier
        System.out.println(file);
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);
        for (Object s : tab) {
            // écrit les attributs de chaque scooters
            pw.println(s.getId());
            pw.println(s.getKilometrage());
            pw.println(s.getMarque());
            pw.println(s.getModele());
            pw.println("EOS");
        }
        // End of File
        pw.println("EOF");
        // sans ça rien n'est écrit dans le txt
        pw.close();

    }

    static Location j = new Location(Location.stringToDate("20/10/2022"), Location.stringToDate("23/10/2022"), 1);
    static Location k = new Location(Location.stringToDate("22/5/2022"), Location.stringToDate("30/5/2022"), 2);
    static Location l = new Location(Location.stringToDate("18/01/2022"), Location.stringToDate("20/01/2022"), 4);
    static Location m = new Location(Location.stringToDate("14/9/2022"), Location.stringToDate("19/9/2022"), 5);
    static Location n = new Location(Location.stringToDate("27/12/2022"), Location.stringToDate("28/12/2022"), 72);
    static Location o = new Location(Location.stringToDate("12/3/2022"), Location.stringToDate("13/3/2022"), 88);

    static void setLocationScoot(ArrayList<Location> tab) {
        tab.add(j);
        tab.add(k);
        tab.add(l);
        tab.add(m);
        tab.add(j);
        tab.add(o);
    }

}