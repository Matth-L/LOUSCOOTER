package GraphiqueBorne.controller;

import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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

    // permet d'initialiser les bd
    public static void initBD() throws IOException {
        BaseDonne.setAll(tabScooter);
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
        /*
         * erreur intercepter
         * lorsque l'utilisateur ne rentre pas un entier
         */
        try {
            // on donne les valeurs de l'utilisateur aux Controller
            scootID = Integer.parseInt(zoneID.getText());
            String DateDeb = zoneDB.getText();
            String DateFin = zoneDF.getText();
            // fct qui rend
            int rslt = Menu2Option.louerDate(scootID, tabScooter, DateDeb, DateFin);
            switch (rslt) {
                case 0:
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
        try {
            // retour date rend un boolean en fct succes
            if (Menu2Option.retourDate(Integer.parseInt(zoneID.getText()), tabScooter)) {
                return "opération bien effectué";
            }
            return "erreur";
        } catch (Exception s) {
            return "veuillez rentrer des informations valides ";
        }
    }

    public static Scooter btnetatScoot() {
        try {
            /*
             * rend un scooter en prenant le tableau de scooter et l'id rentré par
             * l'utilisateur dans zoneID
             */
            return Menu1Method.getScooter(tabScooter, Integer.parseInt(zoneID.getText()));
        } catch (Exception e) {
            return null;
        }
    }

    public static ArrayList<Scooter> btnafficheAllScoot() {
        return tabScooter;
    }

    public static void btnquit(ActionEvent e) throws IOException {
        BaseDonne.saveDB(tabScooter);
        System.exit(0);
    }

    public static void ghostText(JTextField a) {
        a.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                // TODO Auto-generated method stub
                a.setText("");

            }

            @Override
            public void focusLost(FocusEvent e) {
            }

        });
    }

}
