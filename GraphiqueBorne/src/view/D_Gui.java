package GraphiqueBorne.src.view;

import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.text.BadLocationException;

import GraphiqueBorne.src.controller.Controller;

import java.awt.event.*;

public class D_Gui extends C_ActualiseGui {
    public D_Gui() throws IOException, BadLocationException {

        super("louscooter"); // donne le titre de l'application

        /*
         * donne la taille du panel , sa position et comment l'application se comporte
         * quand on appuie sur la croix rouge
         */
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JPanel contentPane = (JPanel) this.getContentPane();
        this.setContentPane(contentPane);

        /*
         * charge la base de donnée si les fichiers existe, sinon elle fait appel a base
         * de donnée pour crée les fichier et les initialiser
         */
        File bd = new File("../src/model/baseDonne/bdScooter.txt");
        File loc = new File("../src/model/baseDonne/location.txt");
        if (bd.isFile() && loc.isFile()) {
            new Controller().set();
        } else {
            new Controller().initBD();
        }

        // donne une icone a l'application
        ImageIcon icon = new ImageIcon("GraphiqueBorne/pictures/scoot.png");
        setIconImage(icon.getImage());

        /*
         * on attribue a chaque bouton un actionListener qui va faire appel au fichier C
         */
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
                    AfficheAllActualise();
                } catch (IOException | BadLocationException e1) {
                    e1.printStackTrace();
                }
            }
        });
        ajoutScoot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AjoutScootActualise();
            }
        });
        deleteScoot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteScootActualise();
            }
        });

        MettreEnReparation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mettreEnReparationActualise("");
            }
        });
        RetirerDeMaRep.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                retirerEnReparationActualise("");
            }
        });

        SuppUneLocation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                suppLocActualise();
            }
        });

        modeAdminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    VerifAdminActualise("");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new Controller().btnquit();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // resize automatique
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                resize(textPane);
            }
        });
        // message de confirmation lorsque l'utilisateur décide de fermer la page
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                int clickButton = JOptionPane.showConfirmDialog(
                        D_Gui.this,
                        "Êtes-vous sur de vouloir quitter ?", "Title",
                        JOptionPane.YES_NO_OPTION);
                if (clickButton == JOptionPane.YES_OPTION) {
                    try {
                        new Controller().btnquit();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    dispose();
                }
            }
        });

        // appel le menu
        menu();
    }

    // on jette tous ce qui peut causer une erreur non prévu
    public static void main(String[] args) throws UnsupportedLookAndFeelException, IOException, BadLocationException {
        // On peut télécharger des Look'n feel
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        new D_Gui().setVisible(true);
    }
}