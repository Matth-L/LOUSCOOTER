package GraphiqueBorne.controller;

import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import GraphiqueBorne.model.*;

public class Controller {

    static ArrayList<Scooter> tabScooter = new ArrayList<Scooter>();
    static ArrayList<Scooter> tabScooterDispo = new ArrayList<Scooter>();

    JTextField zoneID;
    JTextField zoneDB;
    JTextField zoneDF;
    JTextField zoneR;

    public Controller() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

    public Controller(JTextField zt, JTextField db, JTextField df, JTextField dj) {
        zoneID = zt;
        zoneDB = db;
        zoneDF = df;
        zoneR = dj;
    }

    // permet d'initialiser les bd
    public void initBD() throws IOException {
        BaseDonne.setAll(tabScooter);
    }

    public void set() throws IOException {
        new BaseDonne().getDB(tabScooter);
    }

    // lorsque l'utilisateur appuyer sur louer pour valider les info (id scoot,
    // datedb, datefn)
    public ArrayList<Scooter> chgtDonne() throws IOException {
        for (Scooter s : tabScooter) {
            // liste des id pour les scooter dispo
            if (s.isDispoActual()) {
                tabScooterDispo.add(s);
            }
        }
        return tabScooterDispo;
    }

    public ArrayList<Scooter> btnMenu() {
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
    public int btnLouer(ActionEvent e) throws IOException {
        int scootID;
        /*
         * erreur intercepter
         * lorsque l'utilisateur ne rentre pas un entier
         */
        try {
            // on donne les valeurs de l'utilisateur aux Controller
            scootID = Integer.parseInt(zoneID.getText());
            String dateDeb = zoneDB.getText();
            String dateFin = zoneDF.getText();
            return Menu.louerDate(scootID, tabScooter, dateDeb, dateFin);
        } catch (Exception s) {
            return 1;
        }
    }

    public int btnAjouter(ActionEvent e) throws IOException {
        int scootID;

        /*
         * erreur intercepter
         * lorsque l'utilisateur ne rentre pas un entier
         */
        try {
            // on donne les valeurs de l'utilisateur aux Controller
            scootID = tabScooter.size() + 1;
            int km = Integer.parseInt(zoneID.getText());
            String ma = zoneDB.getText();
            String mod = zoneDF.getText();
            int nb = Integer.parseInt(zoneR.getText());
            for (int i = 0; i < nb; i++) {
                tabScooter.add(new Scooter(scootID, km, ma, mod));
                while (verifIdTab(scootID)) {
                    scootID++;
                }
                scootID = tabScooter.size() + 1;
            }
            return 0;

        } catch (Exception s) {
            return 3;
        }
    }

    boolean verifIdTab(int id) {
        for (Scooter s : tabScooter) {
            if (s.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public int btnRetour(ActionEvent e) throws IOException {
        try {
            // retour date rend un boolean en fct succes
            if (Menu.retourDate(Integer.parseInt(zoneID.getText()), tabScooter)) {
                return 0;
            }
            return 2;
        } catch (Exception s) {
            return 1;
        }
    }

    public Scooter btnetatScoot() {
        /*
         * rend un scooter en prenant le tableau de scooter et l'id rentré par
         * l'utilisateur dans zoneID
         */
        // Scooter s = Menu.getScooter(tabScooter, Integer.parseInt(zoneID.getText()));
        // if (s != null) {
        // EtatActualiseEtDonne(s);
        // } else {

        // new ActualiseGui_3().EtatActualise("id invalide");
        // }
        try {
        Scooter s = Menu.getScooter(tabScooter, Integer.parseInt(zoneID.getText()));
        return s;
            
        } catch (Exception e) {
            return null;
        }
        

    }

    public ArrayList<Scooter> btnafficheAllScoot() {
        return tabScooter;
    }

    public void btnquit() throws IOException {
        BaseDonne.saveDB(tabScooter);
        System.exit(0);
    }

    public void ghostText(JTextField a) {
        a.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                a.setText("");

            }

            @Override
            public void focusLost(FocusEvent e) {
            }

        });
    }

    public int MettreEnReparation() {
        try {
            /*
             * rend un scooter en prenant le tableau de scooter et l'id rentré par
             * l'utilisateur dans zoneID
             */
            Scooter s = Menu.getScooter(tabScooter, Integer.parseInt(zoneID.getText()));
            if (s.getEnreparation()) {
                return 3;
            } else {
                s.setEnreparation(true);
                return 0;
            }
        } catch (Exception e) {
            return 3;
        }

    }

    public int retirerReparation() {
        try {
            /*
             * rend un scooter en prenant le tableau de scooter et l'id rentré par
             * l'utilisateur dans zoneID
             */
            Scooter s = Menu.getScooter(tabScooter, Integer.parseInt(zoneID.getText()));
            if (!s.getEnreparation()) {
                return 3;
            } else {
                s.setEnreparation(false);
                return 0;
            }
        } catch (Exception e) {
            return 3;
        }

    }

}
