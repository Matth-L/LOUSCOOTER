package BorneConsole;

import java.io.*; // permet de traiter les exceptions ainsi que le fichier txt 
import java.util.ArrayList;
import java.util.Scanner;

//programme principal 
public class ParcAuto extends BaseDonne {
    static Scanner scan = new Scanner(System.in);

    static Menu4 affiche = new Menu4();

    static void setAll(ArrayList<Scooter> tabScooter) throws IOException {
        // * ajout des élements dans le tab
        setScooterInDB(tabScooter);
        for (int i = 0; i < tabScooter.size(); i++) {
            setLocationScoot(tabScooter.get(i).tabLocation);
        }
        saveDB(tabScooter);
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Scooter> tabScooter = new ArrayList<Scooter>();
        // setAll(tabScooter);
        getDB(tabScooter); // va chercher les informations a partir du fichier txt
        // *rempli le tableau de location de chaque scooter
        affiche.flushS();
        affiche.choixMenu(tabScooter);
        affiche.flushS();
    }

}