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

    // couleur utilisé pour l'html du JtextPane
    private final String black = "<FONT COLOR=\"#17202A\">";
    private final String red = "<FONT COLOR=\"#e60000\">";
    private final String green = "<FONT COLOR=\"#29a329\">";
    private final String yellow = "<FONT COLOR=\"#F1C40F\">";

    // bouton utilisé dans l'application
    JButton louer = new JButton("Louer");
    JButton retour = new JButton("Retour");
    JButton etatScoot = new JButton("Etat du Scooter");
    JButton afficheAll = new JButton("Nos Scooters");
    JButton retourMenu = new JButton("Accueil");
    JButton ajoutScoot = new JButton("Ajouter un scooter");
    JButton deleteScoot = new JButton("Supprimer un scooter");
    JButton MettreEnReparation = new JButton("Mettre en maintenace");
    JButton RetirerDeMaRep = new JButton("Retirer de la maintenance");
    JButton SuppUneLocation = new JButton("Supprimer une Location");
    JButton modeAminButton = new JButton("Mode Admin");
    JButton quit = new JButton("Quitter");

    protected JTextPane textPane = new JTextPane(); // crée l'espace texte

    protected JPanel createRightPanel() {
        /*
         * initialisation du panel + bouton
         */
        JPanel panel = new JPanel(new GridLayout(7, 1));
        panel.add(louer);
        panel.add(retour);
        panel.add(etatScoot);
        panel.add(afficheAll);
        panel.add(SuppUneLocation);
        panel.add(modeAminButton);
        panel.add(quit);

        return panel;
    }

    protected JPanel createRightPanel2() {
        JPanel panel = new JPanel(new GridLayout(8, 1));

        panel.add(louer);
        panel.add(retour);
        panel.add(etatScoot);
        panel.add(afficheAll);
        panel.add(SuppUneLocation);
        panel.add(retourMenu);
        panel.add(modeAminButton);
        panel.add(quit);

        /*
         * ajout d'un action Listener permettant de revenir au menu
         */
        retourMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    menu();
                } catch (IOException | BadLocationException e1) {
                    e1.printStackTrace();
                }

            }
        });

        return panel;
    }

    protected JPanel createRightPanel3() {
        JPanel panel = new JPanel(new GridLayout(6, 1));

        panel.add(ajoutScoot);
        panel.add(deleteScoot);
        panel.add(MettreEnReparation);
        panel.add(RetirerDeMaRep);
        panel.add(retourMenu);
        panel.add(quit);
        retourMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    menu();
                } catch (IOException | BadLocationException e1) {
                    e1.printStackTrace();
                }

            }
        });

        return panel;
    }

    /*
     * la zone de texte en bas est pour la confirmation les erreurs, etc
     */
    protected JPanel creatStatusBar() {
        JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel lblStatus1 = new JLabel();
        statusBar.add(lblStatus1);

        return statusBar;
    }

    // permet de rentrer du texte
    protected JPanel creatStatusBar(String S) {
        JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel lblStatus1 = new JLabel(S);
        statusBar.add(lblStatus1);

        return statusBar;
    }

    // avec l'ajout de couleur
    protected JPanel creatStatusBar(String S, Color c) {
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
        /*
         * Affiche menu prend en paramètre 2 arraylist , l'une est tous les scooters et
         * l'autre les scooters dispo
         */
        contentPane.add(afficheMenu(new Controller().btnafficheAllScoot(), new Controller().btnMenu()),
                BorderLayout.CENTER);
        contentPane.add(creatStatusBar(""), BorderLayout.SOUTH);
        contentPane.add(createRightPanel(), BorderLayout.EAST);
        contentPane.updateUI();
    }

    // permet d'afficher les données d'un scooter j
    protected JPanel AfficheDonne(Scooter s) {
        JPanel pannel = new JPanel(new GridLayout(5, 1));
        pannel.add(new JLabel("id Scooter : " + s.getId()));
        pannel.add(new JLabel("Marque: " + s.getMarque()));
        pannel.add(new JLabel("Modéle " + s.getModele()));
        pannel.add(new JLabel("kilométrage :" + s.getKilometrage()));

        if (s.getEnreparation()) {
            pannel.add(new JLabel("Ce scooter est actuellement en maintenance"));
        } else if (!s.isDispoActual()) {
            pannel.add(new JLabel("Ce scooter est actuellement indisponible"));
        } else {
            pannel.add(new JLabel("Ce scooter est actuellement disponible"));
        }

        return pannel;
    }

    protected JPanel afficheAll(ArrayList<Scooter> tabScooter) throws BadLocationException {
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
        sb.append("<b>Nombre de scooter disponible : </b>" + tabScooter.size() + "<br><br>");

        for (Scooter s : tabScooter) {

            sb.append(black + " id Scooter : " + s.getId() + "  Marque: " + s.getMarque() + "  Modéle "
                    + s.getModele()
                    + "  kilométrage :" + s.getKilometrage() + "<br>");

            /*
             * change la couleur en fonction de si le scooter est en réparation ou non
             */

            if (s.getEnreparation()) {
                sb.append(red + "<b>Le scooter est actuellement en maintenance </b><br><br>");
            } else {
                sb.append(green + "<b> Le scooter est disponible </b> <br><br>");
            }
        }
        /*
         * c'est la que le text est mis dans le textPane
         * ajout scrollbar et propriété
         */
        JScrollPane scroll = new JScrollPane(textPane);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        textPane.setSelectionStart(0);
        vitrine.add(scroll);
        // met les informations du stringbuilder en texte
        textPane.setText(sb.toString());
        textPane.setCaretPosition(0);

        return vitrine;
    }

    protected JPanel afficheMenu(ArrayList<Scooter> tabScoot, ArrayList<Scooter> tabScootDispo)
            throws BadLocationException {
        JPanel vitrine = new JPanel();
        vitrine.setBorder(new TitledBorder(new EtchedBorder(), "Informations sur le parc de scooter :  "));
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
        sb.append("<b>Nombre de scooter dans le parc : " + tabScoot.size() + "</b><br><br>");
        sb.append("<b>Nombre de scooter disponible dans le parc : " + tabScootDispo.size() + "</b><br><br>");
        /*
         * Pour avoir le kilométrage moyen et l'id des scooters du parc
         */
        int kmMoyen = 0;
        /*
         * donne le km moyen et le nombre de scooter disponible
         */
        sb.append("Kilométrage moyen : ");
        for (Scooter s : tabScoot) {
            kmMoyen += s.getKilometrage();
        }
        sb.append(kmMoyen / tabScoot.size() + "<br><br>");
        /*
         * Donne l'id des scooter dispo
         */
        sb.append(green + "Scooter disponible : <br>");
        sb.append(black);
        for (Scooter s : tabScootDispo) {
            if (s.getEnreparation()) {
                sb.append(red);

            }
            sb.append(s.getId() + "<br>");
            sb.append(black);
        }
        sb.append("<br><br>");
        /*
         * Donne l'id des scooter non dispo
         */
        sb.append(yellow + "Scooter en location : <br>");
        sb.append(black);
        for (Scooter s : tabScoot) {
            if (!s.isDispoActual()) {
                sb.append(s.getId() + "<br>");
            }
        }
        sb.append("<br><br>");
        /*
         * Donne l'id des scooter en maintenance
         */
        sb.append(red + "Scooter en maintenance : <br>");
        sb.append(black);
        for (Scooter s : tabScoot) {
            if (s.getEnreparation()) {
                sb.append(s.getId() + "<br>");
            }
        }
        sb.append("<br><br><br><br><br><i><small> un id rouge signifie qu'il est en maintenance ");
        /*
         * Création scrollbar + propriété
         */
        JScrollPane scroll = new JScrollPane(textPane);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        textPane.setSelectionStart(0);
        /*
         * ajout scrollbar a la vitrine
         */
        vitrine.add(scroll);
        /*
         * met les informations du stringbuilder en texte
         */
        textPane.setText(sb.toString());
        textPane.setCaretPosition(0);

        return vitrine;
    }

    protected void resize(JTextPane textPane) {
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
}
