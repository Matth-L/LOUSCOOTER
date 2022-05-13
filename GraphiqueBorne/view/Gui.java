package GraphiqueBorne.view;

import java.io.IOException;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import GraphiqueBorne.controller.Controller;
import GraphiqueBorne.model.Scooter;
import java.awt.event.*;

public class Gui extends JFrame {
    // init button
    JButton louer = new JButton("Louer");
    JButton retour = new JButton("Retour");
    JButton etatScoot = new JButton("Etat Scooter");
    JButton afficheAll = new JButton("Affiche All");
    JButton retourMenu = new JButton("Menu");
    JButton quit = new JButton("Quitter");

    public Gui() throws IOException {
        super("louscooter");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        JPanel contentPane = (JPanel) this.getContentPane();
        this.setContentPane(contentPane);
        // ! init bd
        // Controller.initBD();
        Controller.set();
        // icon
        ImageIcon icon = new ImageIcon("GraphiqueBorne/pictures/scoot.png");
        setIconImage(icon.getImage());
        // on attribue a chaque bouton un actionListener
        louer.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                louerActualise("");
            }
        }));
        retour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                retourActualise("");

            }
        });
        etatScoot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EtatActualise("");
            }
        });
        afficheAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    AfficheAllActualise("");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Controller.btnquit();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        // à voire pour le mettre dans le controller
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                int clickButton = JOptionPane.showConfirmDialog(
                        Gui.this,
                        "Êtes-vous sur de vouloir quitter ?", "Title",
                        JOptionPane.YES_NO_OPTION);
                if (clickButton == JOptionPane.YES_OPTION) {
                    try {
                        Controller.btnquit();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    dispose();
                }
            }
        });
        contentPane.add(afficheAll(Controller.chgtDonne()), BorderLayout.CENTER);
        menu();
    }

    private void menu() throws IOException {
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.removeAll();
        contentPane.add(new JLabel("Scooter dispo actuellement :"), BorderLayout.NORTH);
        contentPane.add(afficheAll(Controller.btnMenu()), BorderLayout.CENTER);
        contentPane.add(creatStatusBar(""), BorderLayout.SOUTH);
        contentPane.add(createRightPanel(), BorderLayout.EAST);
        contentPane.updateUI();
    }

    private void louerActualise(String s) {
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.removeAll();
        contentPane.add(createRightPanel2(), BorderLayout.EAST);
        contentPane.add(creatStatusBar(s), BorderLayout.SOUTH);
        contentPane.add(fctLouer(), BorderLayout.CENTER);
        contentPane.updateUI();
    }

    private void ActionActualise(String s, Color c) {
        JPanel contentPane = (JPanel) this.getContentPane();
        BorderLayout layout = (BorderLayout) contentPane.getLayout();
        contentPane.remove(layout.getLayoutComponent(BorderLayout.SOUTH));
        contentPane.add(creatStatusBar(s, c), BorderLayout.SOUTH);
        contentPane.updateUI();
    }

    private void retourActualise(String s) {
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.removeAll();
        contentPane.add(createRightPanel2(), BorderLayout.EAST);
        contentPane.add(creatStatusBar(s), BorderLayout.SOUTH);
        contentPane.add(fctRetour(), BorderLayout.CENTER);
        contentPane.updateUI();
    }

    private void EtatActualise(String s) {
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.removeAll();
        contentPane.add(createRightPanel2(), BorderLayout.EAST);
        contentPane.add(creatStatusBar(s), BorderLayout.SOUTH);
        contentPane.add(fctAfficheStat(), BorderLayout.NORTH);
        contentPane.updateUI();
    }

    private void EtatActualiseEtDonne(Scooter s) {
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.removeAll();
        contentPane.add(createRightPanel2(), BorderLayout.EAST);
        contentPane.add(fctAfficheStat(), BorderLayout.NORTH);
        contentPane.add(AfficheDonne(s), BorderLayout.CENTER);
        contentPane.updateUI();
    }

    private void AfficheAllActualise(String s) throws IOException {
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.removeAll();
        contentPane.add(afficheAll(Controller.btnafficheAllScoot()), BorderLayout.CENTER);
        contentPane.add(creatStatusBar(s), BorderLayout.SOUTH);
        contentPane.add(createRightPanel2(), BorderLayout.EAST);
        contentPane.updateUI();
    }

    private JPanel createRightPanel() {
        // init panel + button
        JPanel panel = new JPanel(new GridLayout(5, 1));
        panel.add(louer);
        panel.add(retour);
        panel.add(etatScoot);
        panel.add(afficheAll);
        panel.add(quit);

        return panel;
    }

    private JPanel createRightPanel2() {
        // init panel + button
        JPanel panel = new JPanel(new GridLayout(6, 1));

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

    private JPanel fctLouer() {
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

    private JPanel fctRetour() {
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

    private JPanel fctAfficheStat() {

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

    private JPanel AfficheDonne(Scooter s) {
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
    private JPanel afficheAll(ArrayList<Scooter> tabScooterDispo) {
        JPanel affiche = new JPanel();
        int n = tabScooterDispo.size();
        JTextArea textArea;
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
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        for (Scooter s : tabScooterDispo) {
            // il faut mettre append si on met setText on perd le text d'avant
            textArea.append("id Scooter : " + s.getId() + " Marque: " + s.getMarque() + "Modéle " + s.getModele()
                    + " kilométrage :" + s.getKilometrage());
            textArea.append("\n");
        }

        JScrollPane scroll = new JScrollPane(textArea);
        affiche.add(scroll);
        return affiche;
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

    // throw lookNell
    public static void main(String[] args) throws UnsupportedLookAndFeelException, IOException {
        UIManager.setLookAndFeel(new NimbusLookAndFeel()); // On peut télécharger des Look'n feel
        new Gui().setVisible(true);
    }
}