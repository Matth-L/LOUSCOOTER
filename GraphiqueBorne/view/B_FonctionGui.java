package GraphiqueBorne.view;

import java.io.IOException;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.BadLocationException;

import GraphiqueBorne.controller.Controller;
import GraphiqueBorne.model.Scooter;

import java.awt.event.*;

public class B_FonctionGui extends A_MethodGui {

    public static final Color darkGreen = new Color(0, 153, 0);

    // pour ne pas perdre le super()
    public B_FonctionGui() {

    }

    // afin de garder le cpd de swing
    public B_FonctionGui(String titre) {
        super(titre);
    }

    // actualise la status Bar
    protected void ActionActualise(String s, Color c) {
        try {
            JPanel contentPane = (JPanel) this.getContentPane();
            BorderLayout layout = (BorderLayout) contentPane.getLayout();
            contentPane.remove(layout.getLayoutComponent(BorderLayout.SOUTH));
            contentPane.add(creatStatusBar(s, c), BorderLayout.SOUTH);
            contentPane.updateUI();
        } catch (NullPointerException e1) {
        }
    }

    // visible uniquement lors de la connexion
    void ModeAdminActualise(String s) throws IOException, BadLocationException {
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.removeAll();
        contentPane.add(afficheAll(new Controller().chgtDonne()), BorderLayout.CENTER);
        contentPane.add(creatStatusBar(s), BorderLayout.SOUTH);
        contentPane.add(createRightPanel3(), BorderLayout.EAST);
        contentPane.updateUI();
    }

    /*
     * Le controller rend des nombres en fonction de ses valeurs
     * on match les nombres avec des phrases que le client verra dans la status bar
     */
    void messageText(int valueTest) {
        switch (valueTest) {
            case 0:
                ActionActualise("L'opération s'est bien effectué", darkGreen);
                break;
            case 1:
                ActionActualise("L'id rentré est invalide", Color.RED);
                break;
            case 2:
                ActionActualise("Le scooter n'est pas disponible a la date demandé", Color.RED);
                break;
            case 3:
                ActionActualise("Le scooter est en maintenance", Color.RED);
                break;
            default:
                ActionActualise("Erreur", Color.RED);
                break;

        }
    }

    protected JPanel fctLouer() {
        // initialisation pannel
        JPanel pannel = new JPanel(new GridLayout(4, 1, 5, 5));
        JTextField idscoot = new JTextField("id Scoot");
        JTextField dateDeb = new JTextField("jj/mm/ann");
        JTextField dateFin = new JTextField("jj/mm/ann");
        // initialisation controller et texte Fantome
        Controller c = new Controller(idscoot, dateDeb, dateFin);
        c.ghostText(idscoot, "id Scoot");
        c.ghostText(dateDeb, "jj/mm/ann");
        c.ghostText(dateFin, "jj/mm/ann");
        /*
         * création du bouton louer + action listener
         */
        JButton louer2 = new JButton("Appuyez pour louer !");
        louer2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    /*
                     * btnLouer rend un chiffre en fonction du résultat qui est matché avec
                     * messageTexte
                     */
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

    protected JPanel fctRetour() {
        // init
        JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));
        JTextField idScoot = new JTextField("id Scooter");
        // init controller + ghostText
        Controller c = new Controller(idScoot);
        c.ghostText(idScoot, "id Scooter");
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
        c.ghostText(idRentrer, "id scoot");

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

    protected JPanel rechercheDate() { // Fonction dans l'affiche all (No Scooters)
        JPanel vitrine = new JPanel(new GridLayout(1, 3));
        JTextField dateDeb = new JTextField("jj/mm/ann");
        JTextField dateFin = new JTextField("jj/mm/ann");
        JButton rechercher = new JButton("Rechercher");
        Controller c = new Controller(dateDeb, dateFin);
        c.ghostText(dateDeb, "jj/mm/ann");
        c.ghostText(dateFin, "jj/mm/ann");
        rechercher.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // messageText(c.MettreEnReparation());
                try {
                    ArrayList<Scooter> res = c.btnRechercheDate(e);
                    if (res == null) {
                        messageText(3);
                    } else {
                        AfficheAllActualise(res);
                    }
                } catch (IOException | BadLocationException e1) {
                    e1.printStackTrace();
                }

            }
        });
        vitrine.add(dateDeb);
        vitrine.add(dateFin);
        vitrine.add(rechercher);

        return vitrine;
    }

    protected JPanel fctRetirerUneLocation() {
        JPanel pannel = new JPanel(new GridLayout(4, 1, 5, 5));

        JTextField idscoot = new JTextField("id Scoot");
        JTextField dateDeb = new JTextField("jj/mm/ann");
        JTextField dateFin = new JTextField("jj/mm/ann");

        Controller c = new Controller(idscoot, dateDeb, dateFin);
        c.ghostText(idscoot, "id Scoot");
        c.ghostText(dateDeb, "jj/mm/ann");
        c.ghostText(dateFin, "jj/mm/ann");

        JButton supprimerloc = new JButton("Appuyez pour Retirer la location.");
        supprimerloc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                messageText(c.btnsuppLoc());
            }
        });

        pannel.add(idscoot);
        pannel.add(dateDeb);
        pannel.add(dateFin);
        pannel.add(supprimerloc);

        return pannel;
    }

    /*
     * Mode Admimn
     */

    void messageTextAdmin(int valueTest) throws IOException, BadLocationException {
        switch (valueTest) {
            case 0:
                ModeAdminActualise("");
                break;
            case 1:
                ActionActualise("Mauvais mot de Passe", Color.RED);
                break;
            case 2:
                ActionActualise("Nom d'utilisateur invalide", Color.RED);
                break;
            default:
                ActionActualise("Erreur", Color.RED);
                break;

        }
    }

    protected JPanel fctVerifAdmin() {
        // création bouton mode admin
        JButton loginButtons = new JButton("Se connecter");
        JButton resetButtons = new JButton("Réinitialiser la saisie");
        // création textField pour vérif
        JTextField userIDield = new JTextField();
        JPasswordField userPasswordField = new JPasswordField();

        JLabel userIDlabel = new JLabel("Id utilisateur:");
        JLabel userPassordlabel = new JLabel("Mot de passe:");
        JLabel messageLabel = new JLabel("");

        JPanel panel = new JPanel(new GridLayout(4, 2));
        /*
         * action listener pour réinitialiser le tout
         */
        Controller c = new Controller(userIDield, userPasswordField);
        resetButtons.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userIDield.setText("");
                userPasswordField.setText("");
            }
        });
        /*
         * vérification avec le bouton login
         */
        loginButtons.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    messageTextAdmin(c.testIDandP());
                } catch (IOException | BadLocationException e1) {
                    e1.printStackTrace();
                }

            }
        }));

        // ajout aux panel
        userIDlabel.setFocusable(true);
        panel.add(userIDlabel);
        panel.add(userIDield);
        panel.add(userPassordlabel);
        panel.add(userPasswordField);
        panel.add(loginButtons);
        panel.add(resetButtons);
        panel.add(messageLabel);

        return panel;
    }

    protected JPanel fctAjoutScoot() {
        // init
        JPanel pannel = new JPanel(new GridLayout(5, 1, 5, 5));
        JTextField kmge = new JTextField("kilométrage");
        JTextField marque = new JTextField("marque");
        JTextField mod = new JTextField("modèle");
        JTextField nb = new JTextField("nombre de scooter à ajouter");
        // création controller et textFantome
        Controller c = new Controller(kmge, marque, mod, nb);
        c.ghostText(kmge, "kilométrage");
        c.ghostText(marque, "marque");
        c.ghostText(mod, "modèle");
        c.ghostText(nb, "nombre de scooter à ajouter");

        /*
         * bouton valider et action Listener qui retourne une phrase en fonction du
         * nombre donné par btnAjouter
         */
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
        JPanel panel = new JPanel(new GridLayout(2, 1));

        JTextField idScoot = new JTextField("id Scooter");
        Controller c = new Controller(idScoot);

        c.ghostText(idScoot, "id Scooter");

        JButton chercher = new JButton("Chercher");
        chercher.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c.btnSuppr();// la fonction suppr fait un .remove de l'élément de l'arraylist
                messageText(0);
            }

        });

        panel.add(idScoot);
        panel.add(chercher);

        return panel;
    }

    protected JPanel fctMettreEnReparation() {

        JPanel pannel = new JPanel(new GridLayout(2, 1));
        JTextField idRentrer = new JTextField("id scoot");

        Controller c = new Controller(idRentrer);
        c.ghostText(idRentrer, "id scoot");

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
        JPanel panel = new JPanel(new GridLayout(2, 1));
        JTextField idRentrer = new JTextField("id scoot");

        Controller c = new Controller(idRentrer);
        c.ghostText(idRentrer, "id scoot");

        JButton rep = new JButton("Mettre le scooter en disponible");
        rep.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                messageText(c.retirerReparation());

            }
        });

        panel.add(idRentrer);
        panel.add(rep);

        return panel;
    }

    /*
     * toutes les fontions crée un panel et initilaliste la barre de droite et la
     * barre de status pour les message a l'utilisateur
     */
    protected void EtatActualise(String s) {
        JPanel contentPane = (JPanel) getContentPane();
        contentPane.removeAll();
        contentPane.add(createRightPanel2(), BorderLayout.EAST);
        contentPane.add(creatStatusBar(s), BorderLayout.SOUTH);
        contentPane.add(fctEtat(), BorderLayout.NORTH);
        contentPane.updateUI();
    }

    protected void EtatActualiseEtDonne(Scooter s) {
        JPanel contentPane = (JPanel) getContentPane();
        contentPane.removeAll();
        contentPane.add(createRightPanel2(), BorderLayout.EAST);
        contentPane.add(fctEtat(), BorderLayout.NORTH);
        contentPane.add(AfficheDonne(s), BorderLayout.CENTER);
        contentPane.updateUI();
    }

    void AfficheAllActualise(ArrayList<Scooter> s) throws IOException, BadLocationException {
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.removeAll();
        contentPane.add(rechercheDate(), BorderLayout.NORTH);
        contentPane.add(afficheAll(s), BorderLayout.CENTER);
        contentPane.add(creatStatusBar(), BorderLayout.SOUTH);
        contentPane.add(createRightPanel2(), BorderLayout.EAST);
        contentPane.updateUI();
    }

}
