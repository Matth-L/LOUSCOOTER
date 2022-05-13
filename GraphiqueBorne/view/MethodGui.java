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
import javax.swing.JFrame;

public class MethodGui extends JFrame {
    JButton louer = new JButton("Louer");
    JButton retour = new JButton("Retour");
    JButton etatScoot = new JButton("Etat Scooter");
    JButton afficheAll = new JButton("Affiche All");
    JButton retourMenu = new JButton("Menu");
    JButton ajoutScoot = new JButton("Ajouter un scooter");
    JButton quit = new JButton("Quitter");

    protected void menu() throws IOException {
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.removeAll();
        contentPane.add(new JLabel(), BorderLayout.NORTH);
        contentPane.add(afficheAll(Controller.btnMenu()), BorderLayout.CENTER);
        contentPane.add(creatStatusBar(""), BorderLayout.SOUTH);
        contentPane.add(createRightPanel(), BorderLayout.EAST);
        contentPane.updateUI();
    }

    void louerActualise(String s) {
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.removeAll();
        contentPane.add(createRightPanel2(), BorderLayout.EAST);
        contentPane.add(creatStatusBar(s), BorderLayout.SOUTH);
        contentPane.add(fctLouer(), BorderLayout.CENTER);
        contentPane.updateUI();
    }

    void AjoutScootActualise(String s) {
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.removeAll();
        contentPane.add(createRightPanel2(), BorderLayout.EAST);
        contentPane.add(creatStatusBar(s), BorderLayout.SOUTH);
        contentPane.add(fctAjoutScoot(), BorderLayout.CENTER);
        contentPane.updateUI();
    }

    protected void ActionActualise(String s, Color c) {
        JPanel contentPane = (JPanel) this.getContentPane();
        BorderLayout layout = (BorderLayout) contentPane.getLayout();
        contentPane.remove(layout.getLayoutComponent(BorderLayout.SOUTH));
        contentPane.add(creatStatusBar(s, c), BorderLayout.SOUTH);
        contentPane.updateUI();
    }

    void retourActualise(String s) {
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.removeAll();
        contentPane.add(createRightPanel2(), BorderLayout.EAST);
        contentPane.add(creatStatusBar(s), BorderLayout.SOUTH);
        contentPane.add(fctRetour(), BorderLayout.CENTER);
        contentPane.updateUI();
    }

    void EtatActualise(String s) {
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.removeAll();
        contentPane.add(createRightPanel2(), BorderLayout.EAST);
        contentPane.add(creatStatusBar(s), BorderLayout.SOUTH);
        contentPane.add(fctAfficheStat(), BorderLayout.NORTH);
        contentPane.updateUI();
    }

    protected void EtatActualiseEtDonne(Scooter s) {
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.removeAll();
        contentPane.add(createRightPanel2(), BorderLayout.EAST);
        contentPane.add(fctAfficheStat(), BorderLayout.NORTH);
        contentPane.add(AfficheDonne(s), BorderLayout.CENTER);
        contentPane.updateUI();
    }

    void AfficheAllActualise(String s) throws IOException {
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.removeAll();
        contentPane.add(afficheAll(Controller.btnafficheAllScoot()), BorderLayout.CENTER);
        contentPane.add(creatStatusBar(s), BorderLayout.SOUTH);
        contentPane.add(createRightPanel2(), BorderLayout.EAST);
        contentPane.updateUI();
    }

    protected JPanel createRightPanel() {
        // init panel + button
        JPanel panel = new JPanel(new GridLayout(6, 1));
        panel.add(louer);
        panel.add(retour);
        panel.add(etatScoot);
        panel.add(afficheAll);
        panel.add(ajoutScoot);
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
        panel.add(ajoutScoot);
        panel.add(quit);

        return panel;
    }

    // le classique
    public static JPanel creatStatusBar(String S) {
        JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel lblStatus1 = new JLabel(S);
        statusBar.add(lblStatus1);

        return statusBar;
    }

    // avec l'ajout de couleur
    public static JPanel creatStatusBar(String S, Color c) {
        JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel lblStatus1 = new JLabel(S);
        lblStatus1.setForeground(c);
        statusBar.add(lblStatus1);

        return statusBar;
    }

    protected JPanel fctLouer() {
        JPanel pannel = new JPanel(new GridLayout(4, 1, 5, 5));
        JTextField idscoot = new JTextField("id Scoot");
        JTextField dateDeb = new JTextField("jj/mm/ann");
        JTextField dateFin = new JTextField("jj/mm/ann");

        Controller.ghostText(idscoot);
        Controller.ghostText(dateDeb);
        Controller.ghostText(dateFin);

        new Controller(idscoot, dateDeb, dateFin);
        JButton louer2 = new JButton("Appuyez pour louer !");

        louer2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    messageText(Controller.btnLouer(e));

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        pannel.add(idscoot);
        pannel.add(dateDeb);
        pannel.add(dateFin);
        pannel.add(louer2);

        return pannel;
    }

    protected JPanel fctAjoutScoot() {
        JPanel pannel = new JPanel(new GridLayout(5, 1, 5, 5));
        JTextField kmge = new JTextField("kilométrage");
        JTextField marque = new JTextField("marque");
        JTextField mod = new JTextField("modèle");
        JTextField nb = new JTextField("nombre de scooter à ajouter");

        Controller.ghostText(kmge);
        Controller.ghostText(marque);
        Controller.ghostText(mod);
        Controller.ghostText(nb);

        new Controller(kmge, marque, mod, nb);
        JButton valider = new JButton("Appuyez valider");

        valider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    messageText(Controller.btnAjouter(e));

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        pannel.add(kmge);
        pannel.add(marque);
        pannel.add(mod);
        pannel.add(nb);
        pannel.add(valider);

        return pannel;
    }

    protected JPanel fctRetour() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));

        JTextField idScoot = new JTextField("id Scooter");
        Controller.ghostText(idScoot);

        new Controller(idScoot);

        JButton retour = new JButton("Appuyez pour rendre votre location !");
        retour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    messageText(Controller.btnRetour(e));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        panel.add(idScoot);
        panel.add(retour);

        return panel;
    }

    protected JPanel fctAfficheStat() {

        JPanel pannel = new JPanel(new GridLayout(2, 1));
        JTextField idRentrer = new JTextField("id scoot");
        Controller.ghostText(idRentrer);
        JButton chercher = new JButton("chercher !");
        new Controller(idRentrer);
        chercher.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Scooter rslt = Controller.btnetatScoot();
                if (rslt != null) {
                    EtatActualiseEtDonne(rslt);
                } else {
                    EtatActualise("Id invalide");
                }
            }
        });

        pannel.add(idRentrer);
        pannel.add(chercher);

        return pannel;
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
        JTextArea textArea;
        int n = tabScooterDispo.size();
        // permet d'avoir les dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double usageScreen = (this.getWidth() / screenSize.getWidth() * 100);// pourcentage d'utilisation de l'écran
        // pour gerer le plein écran et l'agrandissement
        if (usageScreen < 25) {
            textArea = new JTextArea(n, 22);
            textArea.setFont(textArea.getFont().deriveFont(8f));
        } else if (usageScreen < 50) {
            textArea = new JTextArea(n, 35);
            textArea.setFont(textArea.getFont().deriveFont(12f));
        } else if (usageScreen < 75) {
            textArea = new JTextArea(n, 50);
            textArea.setFont(textArea.getFont().deriveFont(18f));
        } else {
            textArea = new JTextArea(n, 80);
            textArea.setFont(textArea.getFont().deriveFont(20f));
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

    void messageText(int valueTest) {
        switch (valueTest) {
            case 0:
                ActionActualise("L'opération s'est bien effectué", Color.GREEN);
                break;
            case 1:
                ActionActualise("L'id rentré est invalide", Color.RED);
                break;
            case 2:
                ActionActualise("Le scooter n'est pas disponible a la date demandé", Color.RED);
                break;
            default:
                ActionActualise("Erreur", Color.RED);
                break;

        }
    }

    // il faut redefinir le constructeur par défaut car il sera hérité par la suite
    public MethodGui(String titre) {
        super(titre);
    }

}
