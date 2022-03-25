import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

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

    static void setScooterInDB(ArrayList<Scooter> tab) { // pour rajouter les scooters aux tab
        tab.add(a);
        tab.add(b);
        tab.add(c);
        tab.add(d);
        tab.add(e);
        tab.add(f);
        tab.add(g);
        tab.add(i);
    }

    // crée le tableau a partir de la bd
    static void getDB(ArrayList<Scooter> tab) throws FileNotFoundException {
        File file = new File("baseDonne/bdScooter.txt");
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

    static void testEOS(String s) {
        if (s.equals("EOS")) {
            System.err.println("base de donnée corrompue");
            System.exit(1);
        }
    }

    // permet de sauvegarder les scooters dans un txt
    static void saveDB(ArrayList<Scooter> tab) throws IOException {
        File file = new File("baseDonne/bdScooter.txt"); // écrase les données précedents, pour les garder il faut
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

}