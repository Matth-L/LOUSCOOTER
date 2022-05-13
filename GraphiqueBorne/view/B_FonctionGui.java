package GraphiqueBorne.view;

import java.io.IOException;
import java.awt.*;
import javax.swing.*;
import GraphiqueBorne.controller.Controller;
import GraphiqueBorne.model.Scooter;

import java.awt.event.*;

public class B_FonctionGui extends A_MethodGui {

    public B_FonctionGui() {

    }

    public B_FonctionGui(String titre) {
        super(titre);
    }

    protected void ActionActualise(String s, Color c) {
        JPanel contentPane = (JPanel) this.getContentPane();
        BorderLayout layout = (BorderLayout) contentPane.getLayout();
        contentPane.remove(layout.getLayoutComponent(BorderLayout.SOUTH));
        contentPane.add(creatStatusBar(s, c), BorderLayout.SOUTH);
        contentPane.updateUI();
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

    protected JPanel fctLouer() {
        JPanel pannel = new JPanel(new GridLayout(4, 1, 5, 5));
        JTextField idscoot = new JTextField("id Scoot");
        JTextField dateDeb = new JTextField("jj/mm/ann");
        JTextField dateFin = new JTextField("jj/mm/ann");
        Controller c = new Controller(idscoot, dateDeb, dateFin);
        c.ghostText(idscoot);
        c.ghostText(dateDeb);
        c.ghostText(dateFin);

        JButton louer2 = new JButton("Appuyez pour louer !");

        louer2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    messageText(c.btnLouer(e));

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
        Controller c = new Controller(kmge, marque, mod, nb);
        c.ghostText(kmge);
        c.ghostText(marque);
        c.ghostText(mod);
        c.ghostText(nb);

        JButton valider = new JButton("Appuyez valider");

        valider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    messageText(c.btnAjouter(e));

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

    protected JPanel fctSupprScoot() {
        JPanel panel = new JPanel(new GridLayout(1, 1));
        JTextField idScoot = new JTextField("id Scooter");
        new Controller(idScoot).ghostText(idScoot);
        panel.add(idScoot);
        return panel;
    }

    protected JPanel fctRetour() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));

        JTextField idScoot = new JTextField("id Scooter");

        Controller c = new Controller(idScoot);
        c.ghostText(idScoot);

        JButton retour = new JButton("Appuyez pour rendre votre location !");
        retour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    messageText(c.btnRetour(e));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        panel.add(idScoot);
        panel.add(retour);

        return panel;
    }

    protected JPanel fctEtat() {

        JPanel pannel = new JPanel(new GridLayout(2, 1));
        JTextField idRentrer = new JTextField("id scoot");
        Controller c = new Controller(idRentrer);
        c.ghostText(idRentrer);
        JButton chercher = new JButton("chercher !");
        chercher.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Scooter s = c.btnetatScoot();
                if (s == null) {
                    EtatActualise("id invalide");
                } else {
                    EtatActualiseEtDonne(s);
                }
            }
        });

        pannel.add(idRentrer);
        pannel.add(chercher);

        return pannel;
    }

    protected JPanel fctRetirereEtat() {
        JPanel pannel = new JPanel(new GridLayout(2, 1));
        JTextField idRentrer = new JTextField("id scoot");
        Controller c = new Controller(idRentrer);
        c.ghostText(idRentrer);
        JButton rep = new JButton("Mettre le scooter en disponible");
        rep.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                messageText(c.retirerReparation());

            }
        });
        pannel.add(idRentrer);
        pannel.add(rep);
        return pannel;
    }

    protected JPanel fctMettreEnReparation() {
        JPanel pannel = new JPanel(new GridLayout(2, 1));
        JTextField idRentrer = new JTextField("id scoot");
        Controller c = new Controller(idRentrer);
        c.ghostText(idRentrer);
        JButton rep = new JButton("Le mettre en réparation.");
        rep.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                messageText(c.MettreEnReparation());

            }
        });
        pannel.add(idRentrer);
        pannel.add(rep);
        return pannel;
    }

    protected JPanel fctRetirereEtatReparation() {
        JPanel pannel = new JPanel(new GridLayout(2, 1));
        JTextField idRentrer = new JTextField("id scoot");
        Controller c = new Controller(idRentrer);
        c.ghostText(idRentrer);
        JButton rep = new JButton("Mettre le scooter en disponible");
        rep.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                messageText(c.retirerReparation());

            }
        });
        pannel.add(idRentrer);
        pannel.add(rep);
        return pannel;
    }

    public void EtatActualise(String s) {
        JPanel contentPane = (JPanel) getContentPane();
        contentPane.removeAll();
        contentPane.add(createRightPanel2(), BorderLayout.EAST);
        contentPane.add(creatStatusBar(s), BorderLayout.SOUTH);
        contentPane.add(fctEtat(), BorderLayout.NORTH);
        contentPane.updateUI();
    }

    public void EtatActualiseEtDonne(Scooter s) {
        JPanel contentPane = (JPanel) getContentPane();
        contentPane.removeAll();
        contentPane.add(createRightPanel2(), BorderLayout.EAST);
        contentPane.add(fctEtat(), BorderLayout.NORTH);
        contentPane.add(AfficheDonne(s), BorderLayout.CENTER);
        contentPane.updateUI();
    }

}
