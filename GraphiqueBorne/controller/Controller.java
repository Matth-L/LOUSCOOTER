package GraphiqueBorne.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public Controller() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    // lorsque l'utilisateur appuyer sur louer pour valider les info (id scoot,
    // datedb, datefn)

    public static ArrayList<Scooter> chgtDonne() throws IOException {

        ParcAuto.setAll(tabScooter);

        for (Scooter s : tabScooter) {
            // liste des id pour les scooter dispo
            if (s.isDispoActual()) {
                tabScooterDispo.add(s);
            }
        }
        return tabScooterDispo;
    }

    public static String btnLouer(ActionEvent e) throws IOException {

        int scootID = Integer.parseInt(zoneID.getText());
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

    }

    public void btnRetour(ActionEvent e) {
        System.out.println("louer");
    }

    public void btnetatScoot(ActionEvent e) {
        System.out.println("etatScoot");
    }

    public static ArrayList<Scooter> btnafficheAllScoot() {
        tabScooterDispo.clear();
        for (Scooter s : tabScooter) {
            // liste des id pour les scooter dispo
            if (s.isDispoActual()) {
                tabScooterDispo.add(s);
            }
        }
        return tabScooterDispo;
    }

    public void btnparcScoot(ActionEvent e) {
        System.out.println("parcscoot");
    }

    public void btnquit(ActionEvent e) {
        System.out.println("quit");
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
