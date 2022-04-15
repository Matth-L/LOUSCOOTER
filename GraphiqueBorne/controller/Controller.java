package GraphiqueBorne.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZoneId;

import javax.swing.*;

import GraphiqueBorne.view.Bouton;

public class Controller {
    JLabel label;
    Bouton button;
    JTextField zoneID;
    JTextField zoneDB;
    JTextField zoneDF;

    public Controller() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void btnLouer(ActionEvent e) {

        // créer un nv panel ?
    }

    public void btnRetour(ActionEvent e) {
        System.out.println("louer");
    }

    public void btnetatScoot(ActionEvent e) {
        System.out.println("etatScoot");
    }

    public void btnafficheAllScoot(ActionEvent e) {
        System.out.println("affiche all");
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
