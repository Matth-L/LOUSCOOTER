package GraphiqueBorne.model;

import java.io.*; // permet de traiter les exceptions ainsi que le fichier txt 
import java.util.ArrayList;
import java.util.Scanner;

//programme principal 
public class ParcAuto extends BaseDonne {
    static Scanner scan = new Scanner(System.in);

    static Menu4 affiche = new Menu4();
    ArrayList<Scooter> tabScooter = new ArrayList<Scooter>();

    public static void setAll(ArrayList<Scooter> tabScooter) throws IOException {
        // * ajout des élements dans le tab
        setScooterInDB(tabScooter);
        for (int i = 0; i < tabScooter.size(); i++) {
            setLocationScoot(tabScooter.get(i).tabLocation);
        }
        saveDB(tabScooter);
    }

}