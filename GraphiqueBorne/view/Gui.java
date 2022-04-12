package GraphiqueBorne.view;
//package

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import GraphiqueBorne.controller.Controller;

public class Gui extends JFrame {

    public Gui() {
        // init screen
        Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) tailleEcran.getWidth();
        int y = (int) tailleEcran.getHeight();
        Controller ctr = new Controller();
        setSize(x / 2, y / 2);// taille de l'écran de l'utilisateur par 2 (fullscreen est un peu aggressif)
        // init pannel + bouton
        JPanel panel = new JPanel(new GridLayout(6, 1));
        Bouton louer = new Bouton("Louer");
        Bouton retour = new Bouton("Retour");
        Bouton etatScoot = new Bouton("État d'un scooter");
        Bouton afficheAllScoot = new Bouton("Affichage de l'état du parc de scooters");
        Bouton parcScoot = new Bouton("Saisie du parc des scooters");
        Bouton quit = new Bouton("Quitter le programme");
        // ajout au panel des boutons au panel
        louer.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ctr.btnLouer(e);
            }

        });

        retour.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ctr.btnRetour(e);
            }

        });
        etatScoot.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ctr.btnetatScoot(e);
            }

        });
        afficheAllScoot.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ctr.btnafficheAllScoot(e);
            }

        });
        parcScoot.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ctr.btnparcScoot(e);
            }

        });
        quit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ctr.btnquit(e);
            }

        });


        panel.add(louer);
        panel.add(retour);
        panel.add(etatScoot);
        panel.add(afficheAllScoot);
        panel.add(parcScoot);
        panel.add(quit);
        // ajout du panel au conteneur
        this.getContentPane().add(BorderLayout.EAST, panel);
        // action listener juste pour tester
        // louer.addActionListener(ctr);
        // quit.addActionListener(ctr);
        // nécessaire
        setVisible(true);
        setLocationRelativeTo(null);// centre la fenetre au milieu
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Gui a = new Gui();
    }
}
