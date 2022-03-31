package graphique.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Controller implements ActionListener {
    public Controller() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ((((JButton) e.getSource()).getText()).equals("Louer")) {
            System.out.println("louer");
        }
        if ((((JButton) e.getSource()).getText()).equals("Retour")) {
        }
        if ((((JButton) e.getSource()).getText()).equals("État d'un scooter")) {
        }
        if ((((JButton) e.getSource()).getText()).equals("Affichage de l'état du parc de scooters")) {
        }
        if ((((JButton) e.getSource()).getText()).equals("Saisie du parc des scooters")) {
        }
        if ((((JButton) e.getSource()).getText()).equals("Quitter le programme")) {
            System.exit(0);
        }
    }
}
