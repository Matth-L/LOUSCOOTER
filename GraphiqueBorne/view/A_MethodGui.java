package GraphiqueBorne.view;

import java.io.IOException;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import GraphiqueBorne.controller.Controller;
import GraphiqueBorne.model.Scooter;
import java.awt.event.*;

public class A_MethodGui extends JFrame {
    public A_MethodGui() {
    }

    // il faut redefinir le constructeur par défaut car il sera hérité par la suite
    public A_MethodGui(String titre) {
        super(titre);
    }

    JButton louer = new JButton("Louer");
    JButton retour = new JButton("Retour");
    JButton etatScoot = new JButton("Etat Scooter");
    JButton afficheAll = new JButton("Affiche All");
    JButton retourMenu = new JButton("Menu");
    JButton ajoutScoot = new JButton("Ajouter un scooter");
    JButton deleteScoot = new JButton("Supprimer un scooter");
    JButton MettreEnReparation = new JButton("Mettre En Reparatoion");
    JButton RetirerDeMaRep = new JButton("Mettre Disponible");

    JButton modeAminButton = new JButton("modeAdmin");
    JButton quit = new JButton("Quitter");

    protected JPanel createRightPanel() {
        // init panel + button
        JPanel panel = new JPanel(new GridLayout(6, 1));
        panel.add(louer);
        panel.add(retour);
        panel.add(etatScoot);
        panel.add(afficheAll);
        panel.add(modeAminButton);
        // panel.add(deleteScoot);
        panel.add(quit);

        return panel;
    }

    protected JPanel createRightPanel2() {
        // init panel + button
        JPanel panel = new JPanel(new GridLayout(7, 1));

        panel.add(louer);
        panel.add(retour);
        panel.add(etatScoot);
        panel.add(afficheAll);
        retourMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    menu();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });

        panel.add(retourMenu);
        panel.add(modeAminButton);
        panel.add(quit);

        return panel;
    }

    public JPanel createRightPanel3() {
        // init panel + button
        JPanel panel = new JPanel(new GridLayout(6, 1));

        panel.add(ajoutScoot);
        panel.add(deleteScoot);
        retourMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    menu();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });
        panel.add(MettreEnReparation);
        panel.add(RetirerDeMaRep);
        panel.add(retourMenu);
        panel.add(quit);

        return panel;
    }

    // le classique
    public JPanel creatStatusBar(String S) {
        JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel lblStatus1 = new JLabel(S);
        statusBar.add(lblStatus1);

        return statusBar;
    }

    // avec l'ajout de couleur
    public JPanel creatStatusBar(String S, Color c) {
        JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel lblStatus1 = new JLabel(S);
        lblStatus1.setForeground(c);
        statusBar.add(lblStatus1);

        return statusBar;
    }

    protected void menu() throws IOException {
        JPanel contentPane = (JPanel) getContentPane();
        contentPane.removeAll();
        contentPane.add(new JLabel(), BorderLayout.NORTH);
        contentPane.add(afficheAll(new Controller().btnMenu()), BorderLayout.CENTER);
        contentPane.add(creatStatusBar(""), BorderLayout.SOUTH);
        contentPane.add(createRightPanel(), BorderLayout.EAST);
        contentPane.updateUI();
    }

    protected JPanel AfficheDonne(Scooter s) {
        JPanel pannel = new JPanel(new GridLayout(5, 1));
        pannel.add(new JLabel("id Scooter : " + s.getId()));
        pannel.add(new JLabel("Marque: " + s.getMarque()));
        pannel.add(new JLabel("Modéle " + s.getModele()));
        pannel.add(new JLabel("kilométrage :" + s.getKilometrage()));

        if (s.isDispoActual()) {
            pannel.add(new JLabel("Ce scooter est actuellement disponible"));
        } else {
            pannel.add(new JLabel("Ce scooter est actuellement indisponible"));
        }

        return pannel;
    }

    // *j'ai pas trouvé de flex ou autre donc pour la taille si ça dépasse la moitié
    // * de la largeur de l'écran je rend le text Area plus grand
    protected JPanel afficheAll(ArrayList<Scooter> tabScooterDispo) {

        JPanel vitrine = new JPanel();
        vitrine.setBorder(new TitledBorder(new EtchedBorder(), "Scooter disponible"));
        vitrine.setLayout(new BorderLayout());// fais en sorte que le texte ne déborde pas
        // crée le textArea
        JTextArea textArea; // premier paramètre ligne , deuxième colonne
        int n = tabScooterDispo.size();
        // permet d'avoir les dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double usageScreen = (getWidth() / screenSize.getWidth() * 100);// pourcentage d'utilisation de l'écran
        // pour gerer le plein écran et l'agrandissement
        if (usageScreen < 25) {
            textArea = new JTextArea(n, 22);
            textArea.setFont(textArea.getFont().deriveFont(12f));
        } else if (usageScreen < 50) {
            textArea = new JTextArea(n, 35);
            textArea.setFont(textArea.getFont().deriveFont(15f));
        } else if (usageScreen < 75) {
            textArea = new JTextArea(n, 50);
            textArea.setFont(textArea.getFont().deriveFont(20f));
        } else {
            textArea = new JTextArea(n, 80);
            textArea.setFont(textArea.getFont().deriveFont(22f));
        }
        // propriété du textArea
        textArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        for (Scooter s : tabScooterDispo) {
            // il faut mettre append si on met setText on perd le text d'avant
            textArea.append("id Scooter : " + s.getId() + "  Marque: " + s.getMarque() + "  Modéle " + s.getModele()
                    + "  kilométrage :" + s.getKilometrage());
            textArea.append("\n");
        }

        vitrine.add(scroll);
        return vitrine;
    }

}
