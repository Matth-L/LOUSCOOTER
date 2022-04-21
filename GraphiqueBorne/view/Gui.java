package GraphiqueBorne.view;

import java.awt.FlowLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import GraphiqueBorne.controller.Controller;
import GraphiqueBorne.model.Scooter;
import java.awt.event.*;

public class Gui extends JFrame {
    // JScrollPane scrollpane = new JScrollPane();
    // JScrollBar scrollbar = new JScrollBar(JScrollBar.VERTICAL);

    // init button
    JButton louer = new JButton("Louer");
    JButton retour = new JButton("Retour");
    JButton etatScoot = new JButton("Etat Scooter");
    JButton afficheAll = new JButton("Affiche All");
    JButton retourMenu = new JButton("Menu");
    JButton quit = new JButton("Quitter");

    public Gui() throws IOException {
        super("louscooter");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        JPanel contentPane = (JPanel) this.getContentPane();
        this.setContentPane(contentPane);
        // ! init bd
        // Controller.initBD();
        Controller.set();
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
                    Controller.btnquit(e);
                } catch (IOException e1) {
                    e1.printStackTrace();
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

    public static JPanel creatStatusBar(String S) {
        JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel lblStatus1 = new JLabel(S);
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
                    louerActualise(Controller.btnLouer(e));

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
                    retourActualise(Controller.btnRetour(e));
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

    private JPanel afficheAll(ArrayList<Scooter> tabScooterDispo) {
        int n = tabScooterDispo.size();
        JPanel affiche = new JPanel(new GridLayout(n, 4));

        for (Scooter s : tabScooterDispo) {
            affiche.add(new JLabel("id Scooter : " + s.getId()));
            affiche.add(new JLabel("Marque: " + s.getMarque()));
            affiche.add(new JLabel("Modéle " + s.getModele()));
            affiche.add(new JLabel("kilométrage :" + s.getKilometrage()));
        }

        return affiche;
    }

    // throw lookNell
    public static void main(String[] args) throws UnsupportedLookAndFeelException, IOException {
        UIManager.setLookAndFeel(new NimbusLookAndFeel()); // On peut télécharger des Look'n feel
        new Gui().setVisible(true);
    }
}