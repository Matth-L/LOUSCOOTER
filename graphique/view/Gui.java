package graphique.view;
//package

import java.awt.*;

import javax.swing.*;

import graphique.controller.Controller;

public class Gui extends JFrame {

    public Gui() {
        // init screen
        Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) tailleEcran.getWidth();
        int y = (int) tailleEcran.getHeight();
        setSize(x / 2, y / 2);// taille de l'écran de l'utilisateur par 2 (fullscreen est un peu aggressif)
        // init pannel + bouton
        JPanel panel = new JPanel(new GridLayout(1, 6));
        Bouton louer = new Bouton("Louer");
        Bouton retour = new Bouton("Retour");
        Bouton etatScoot = new Bouton("État d'un scooter");
        Bouton afficheAllScoot = new Bouton("Affichage de l'état du parc de scooters");
        Bouton parcScoot = new Bouton("Saisie du parc des scooters");
        Bouton quit = new Bouton("Quitter le programme");
        // ajout au panel
        panel.add(louer);
        panel.add(retour);
        panel.add(etatScoot);
        panel.add(afficheAllScoot);
        panel.add(parcScoot);
        panel.add(quit);
        // ajout du panel au conteneur
        this.getContentPane().add(BorderLayout.CENTER, panel);
        // action listener juste pour tester
        Controller ctr = new Controller();
        louer.addActionListener(ctr);
        quit.addActionListener(ctr);
        // nécessaire
        setVisible(true);
        setLocationRelativeTo(null);// centre la fenetre au milieu
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Gui a = new Gui();
    }
}
