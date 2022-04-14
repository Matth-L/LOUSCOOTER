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
        louer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctr.btnLouer(e);
            }

        });

        retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctr.btnRetour(e);
            }

        });
        etatScoot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctr.btnetatScoot(e);
            }

        });
        afficheAllScoot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctr.btnafficheAllScoot(e);
            }

        });
        parcScoot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctr.btnparcScoot(e);
            }

        });
        quit.addActionListener(new ActionListener() {
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
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        Gui a = new Gui();
    }
}

/*
 * package t;
 * 
 * import java.awt.BorderLayout;
 * import java.awt.Dimension;
 * import java.awt.EventQueue;
 * 
 * import javax.swing.JFrame;
 * import javax.swing.JPanel;
 * import javax.swing.border.EmptyBorder;
 * import javax.swing.JButton;
 * import javax.swing.JTextField;
 * import javax.swing.Box;
 * import java.awt.GridLayout;
 * import javax.swing.JLabel;
 * import javax.swing.JTextArea;
 * import javax.swing.JComboBox;
 * import java.awt.FlowLayout;
 * import java.awt.event.ActionListener;
 * import java.awt.event.ActionEvent;
 * import java.awt.GridBagLayout;
 * import java.awt.GridBagConstraints;
 * import java.awt.Insets;
 * import javax.swing.JList;
 * import javax.swing.JScrollBar;
 * import javax.swing.SwingConstants;
 * import javax.swing.DefaultComboBoxModel;
 * 
 * public class toot extends JFrame {
 * 
 * private JPanel contentPane;
 * 
 * JButton btnRetour = new JButton("Retour");
 * 
 * JButton btnLouer = new JButton("Louer");
 * 
 * JTextArea textArea = new JTextArea("nb de scooter louer :" );
 * 
 * JComboBox scootLouer = new JComboBox();
 * 
 * JComboBox ScoutDispo = new JComboBox();
 * 
 * JScrollBar scrollBar = new JScrollBar();
 * 
 * 
 * aunch the application.
 * 
 * public static void main(String[] args) {
 * EventQueue.invokeLater(new Runnable() {
 * public void run() {
 * try {
 * toot frame = new toot();
 * frame.setVisible(true);
 * } catch (Exception e) {
 * e.printStackTrace();
 * }
 * }
 * });
 * }
 * 
 * 
 * Create the frame.
 * 
 * public toot() {
 * setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 * setBounds(100, 100, 870, 640);
 * contentPane = new JPanel();
 * contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
 * setContentPane(contentPane);
 * int i=3;
 * 
 * btnRetour.addActionListener(new ActionListener() {
 * public void actionPerformed(ActionEvent e) {
 * }
 * });
 * 
 * btnLouer.addActionListener(new ActionListener() {
 * public void actionPerformed(ActionEvent e) {
 * }
 * });
 * contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
 * //contentPane.add(btnLouer);
 * //contentPane.add(btnRetour);
 * //contentPane.add(textArea);
 * 
 * 
 * scootLouer.setModel(new DefaultComboBoxModel(new String[] {"toolbar",
 * "tsdfqsdf", "tsqfqsdfqsdf", "tqsdfqdf", "t"}));
 * //contentPane.add(scootLouer);
 * 
 * JTextArea textArea_1 = new JTextArea("nb de scooter disponible :"+i);
 * //contentPane.add(textArea_1);
 * 
 * 
 * ScoutDispo.setModel(new DefaultComboBoxModel(new String[] {"toolbar",
 * "tsdfqsdf", "tsqfqsdfqsdf", "tqsdfqdf", "t"}));
 * ScoutDispo.setSelectedIndex(0);
 * ScoutDispo.setToolTipText("");
 * //contentPane.add(ScoutDispo);
 * contentPane.add(creatRightPanel(), BorderLayout.CENTER);
 * 
 * contentPane.add(scrollBar);
 * }
 * 
 * private JPanel creatRightPanel() {
 * JPanel panel = new JPanel(new GridLayout(4, 1));
 * panel.add(btnRetour);
 * panel.add(btnLouer);
 * panel.add(textArea);
 * panel.add(scootLouer);
 * 
 * 
 * return panel;
 * }
 * 
 * }
 * 
 */