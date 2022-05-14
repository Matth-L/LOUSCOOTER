package GraphiqueBorne.view;

import java.io.IOException;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;
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
    JButton SuppUneLocation = new JButton("Supp Un Location");
    JButton modeAminButton = new JButton("modeAdmin");
    JButton quit = new JButton("Quitter");

    protected JPanel createRightPanel() {
        // init panel + button
        JPanel panel = new JPanel(new GridLayout(7, 1));
        panel.add(louer);
        panel.add(retour);
        panel.add(etatScoot);
        panel.add(afficheAll);
        panel.add(SuppUneLocation);
        panel.add(modeAminButton);
        // panel.add(deleteScoot);
        panel.add(quit);

        return panel;
    }

    protected JPanel createRightPanel2() {
        // init panel + button
        JPanel panel = new JPanel(new GridLayout(8, 1));

        panel.add(louer);
        panel.add(retour);
        panel.add(etatScoot);
        panel.add(afficheAll);
        retourMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    menu();
                } catch (IOException | BadLocationException e1) {
                    e1.printStackTrace();
                }

            }
        });

        panel.add(SuppUneLocation);
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
                } catch (IOException | BadLocationException e1) {
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
    public JPanel creatStatusBar() {
        JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel lblStatus1 = new JLabel();
        statusBar.add(lblStatus1);

        return statusBar;
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

    protected void menu() throws IOException, BadLocationException {
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

    String switchBool(boolean x) {
        if (x) {
            return "Le scooter est actuellement en réparation";
        }
        return "";
    }

    void resize(JTextPane textPane) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double usageScreen = (getWidth() / screenSize.getWidth() * 100);// pourcentage d'utilisation de l'écran

        // pour gerer le plein écran et l'agrandissement
        if (usageScreen < 25) {
            textPane.setFont(new Font("Serif", Font.ITALIC, 14));
        } else if (usageScreen < 50) {
            textPane.setFont(new Font("Serif", Font.ITALIC, 18));
        } else if (usageScreen < 75) {
            textPane.setFont(new Font("Serif", Font.ITALIC, 22));
        } else {
            textPane.setFont(new Font("Serif", Font.ITALIC, 26));
        }

    }

    protected JTextPane textPane = new JTextPane();

    protected JPanel afficheAll(ArrayList<Scooter> tabScooterDispo) throws BadLocationException {

        JPanel vitrine = new JPanel();
        vitrine.setBorder(new TitledBorder(new EtchedBorder(), "Liste des scooters "));
        vitrine.setLayout(new BorderLayout());// fais en sorte que le texte ne déborde pas

        /*
         * crée le textPane et met la propriété html dedans
         */

        textPane.setContentType("text/html");

        /*
         * permet d'avoir les dimensions
         */
        resize(textPane);
        textPane.setEditable(false); // propriété du textPane

        /*
         * le stringbuilder permet de créer et modifier un texte et de rajouter de
         * l'html
         */
        StringBuilder sb = new StringBuilder();
        // le noir classique du texte
        String normalFontColor = "<FONT COLOR=\"#17202A\">";

        for (Scooter s : tabScooterDispo) {

            sb.append(normalFontColor + " id Scooter : " + s.getId() + "  Marque: " + s.getMarque() + "  Modéle "
                    + s.getModele()
                    + "  kilométrage :" + s.getKilometrage() + "<br>");

            /*
             * change la couleur en fonction de si le scooter est en réparation ou non
             */

            if (s.getEnreparation()) {
                sb.append(
                        "<FONT COLOR=\"#FF2D00\"><b>Le scooter est actuellement en maintenance </b><br><br>");
            } else {
                sb.append("<FONT COLOR=\"#55FF00\"><b> Le scooter est disponible </b> <br><br>");
            }
        }
        // c'est la que le text est mis dans le textPane
        // ajout scrollbar et propriété
        JScrollPane scroll = new JScrollPane(textPane);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        textPane.setSelectionStart(0);
        vitrine.add(scroll);
        textPane.setText(sb.toString());
        textPane.setCaretPosition(0);

        return vitrine;
    }

}
