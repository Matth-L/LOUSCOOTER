package GraphiqueBorne.view;

import java.io.File;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.text.BadLocationException;

import GraphiqueBorne.controller.Controller;

import java.awt.event.*;

public class D_Gui extends C_ActualiseGui {
    // init button

    public D_Gui() throws IOException, BadLocationException {
        super("louscooter");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        JPanel contentPane = (JPanel) this.getContentPane();
        this.setContentPane(contentPane);
        // init bd
        File bd = new File("GraphiqueBorne/model/baseDonne/bdScooter.txt");
        File loc = new File("GraphiqueBorne/model/baseDonne/location.txt");
        if (bd.isFile() && loc.isFile()) {
            new Controller().set();
        } else {
            new Controller().initBD();
        }
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

        modeAminButton.addActionListener(new ActionListener() {
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
                try {
                    menu();
                } catch (IOException | BadLocationException e1) {
                    e1.printStackTrace();
                }
            }
        });
        // à voire pour le mettre dans le controller
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
        // contentPane.add(afficheAll(new Controller().chgtDonne()),
        // BorderLayout.CENTER);
        menu();

    }

    // throw lookNell
    public static void main(String[] args) throws UnsupportedLookAndFeelException, IOException, BadLocationException {
        UIManager.setLookAndFeel(new NimbusLookAndFeel()); // On peut télécharger des Look'n feel
        new D_Gui().setVisible(true);
    }
}