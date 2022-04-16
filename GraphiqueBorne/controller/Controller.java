package GraphiqueBorne.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.ZoneId;
import java.util.ArrayList;

import javax.swing.*;
import GraphiqueBorne.model.*;
import GraphiqueBorne.view.Bouton;
import GraphiqueBorne.view.Gui;

public class Controller {
    JLabel label;
    Bouton button;
    static ArrayList<Scooter> tabScooter = new ArrayList<Scooter>();
    static ArrayList<Scooter> tabScooterDispo = new ArrayList<Scooter>();

    static JTextField zoneID;
    static JTextField zoneDB;
    static JTextField zoneDF;

    public static void set() throws IOException {
        // ParcAuto.setAll(tabScooter);
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
                    // Gui.creatStatusBar("Votre opération c'est bien déroulé");
                    return "Votre opération c'est bien déroulé";

                case 1:
                    // Gui.creatStatusBar("L'id rentré est invalide");
                    return "L'id rentré est invalide";
                case 2:
                    return "Le véhicule demandé n'est pas disponible à la date demandé";
            }
            return "error";
        } catch (Exception s) {
            // TODO: handle exception
            return "veillier rentrer un id valide";
        }

    }

    public void btnRetour(ActionEvent e) {
        System.out.println("louer");
    }

    public static Scooter btnetatScoot() {
        try {
            int scootID = Integer.parseInt(zoneID.getText());
            Scooter S = Menu1Method.getScooter(tabScooter, scootID);
            return S;
        } catch (Exception e) {
            return null;
            // TODO: handle exception
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

    public Controller(JTextField zt, JTextField db, JTextField df) {
        zoneID = zt;
        zoneDB = db;
        zoneDF = df;
    }

}
