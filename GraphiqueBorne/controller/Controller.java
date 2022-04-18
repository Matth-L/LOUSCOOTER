package GraphiqueBorne.controller;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import GraphiqueBorne.model.*;

public class Controller {
    JLabel label;
    static ArrayList<Scooter> tabScooter = new ArrayList<Scooter>();
    static ArrayList<Scooter> tabScooterDispo = new ArrayList<Scooter>();

    static JTextField zoneID;
    static JTextField zoneDB;
    static JTextField zoneDF;
    static JTextField zoneR;

    public static void initBD() throws IOException {
        ParcAuto.setAll(tabScooter);
    }

    public static void set() throws IOException {
        BaseDonne.getDB(tabScooter);
    }

    public Controller() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    // lorsque l'utilisateur appuyer sur louer pour valider les info (id scoot,
    // datedb, datefn)

    public static ArrayList<Scooter> chgtDonne() throws IOException {

        // ParcAuto.setAll(tabScooter);

        for (Scooter s : tabScooter) {
            // liste des id pour les scooter dispo
            if (s.isDispoActual()) {
                tabScooterDispo.add(s);
            }
        }
        return tabScooterDispo;
    }

    public static ArrayList<Scooter> btnMenu() {
        tabScooterDispo.clear();
        for (Scooter s : tabScooter) {
            // liste des id pour les scooter dispo
            if (s.isDispoActual()) {
                tabScooterDispo.add(s);
            }
        }
        return tabScooterDispo;
    }

    // à améliorer: faire de tel sorte qu'on garde les info rentrer dans la zone de
    // texte ap quelqu'onque action.
    public static String btnLouer(ActionEvent e) throws IOException {
        int scootID;
        try { // j'intersepte l'erreur lorsque l'utilisateur ne rentre pas un entier dans la
              // case souhaitée. Faudra faire de même pour retour
            scootID = Integer.parseInt(zoneID.getText());
            String DateDeb = zoneDB.getText();
            String DateFin = zoneDF.getText();
            int rslt = Menu3Erreur.louerDate(scootID, tabScooter, DateDeb, DateFin);
            switch (rslt) {
                case 0:
                    BaseDonne.saveDB(tabScooter);
                    return "Votre opération c'est bien déroulé";

                case 1:
                    return "L'id rentré est invalide";
                case 2:
                    return "Le véhicule demandé n'est pas disponible à la date demandé";
            }
            return "error";
        } catch (Exception s) {
            return "veuillez rentrer un id valide";
        }

    }

    public static String btnRetour(ActionEvent e) throws IOException {
        // il va falloir utiliser zone id
        int scootID = Integer.parseInt(zoneID.getText());
        try {
            if (Menu3Erreur.retourDate(scootID, tabScooter)) {
                BaseDonne.saveDB(tabScooter);
                return "opération bien effectué";
            }
            return "erreur";
        } catch (Exception s) {
            return "veuillez rentrer des informations valides ";
        }
    }

    public static Scooter btnetatScoot() {
        try {
            int scootID = Integer.parseInt(zoneID.getText());
            Scooter S = Menu1Method.getScooter(tabScooter, scootID);
            return S;
        } catch (Exception e) {
            return null;
        }
    }

    public static ArrayList<Scooter> btnafficheAllScoot() {
        return tabScooter;
    }

    public void btnparcScoot(ActionEvent e) {
        System.out.println("parcscoot");
    }

    public static void btnquit(ActionEvent e) throws IOException {
        BaseDonne.saveDB(tabScooter);
        System.exit(0);
    }

    public Controller(JTextField zt) {
        zoneID = zt;
    }

    public Controller(JTextField zt, JTextField zR) {
        zoneID = zt;
        zoneR = zR;
    }

    public Controller(JTextField zt, JTextField db, JTextField df) {
        zoneID = zt;
        zoneDB = db;
        zoneDF = df;
    }

}
