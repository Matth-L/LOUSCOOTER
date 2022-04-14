package GraphiqueBorne.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
// import GraphiqueBorne.controller.ControllerLouer;

import BorneConsole.ParcAuto;

public class Gui extends JFrame {

    JButton louer = new JButton("Louer");
    JButton retour = new JButton("Retour");
    JButton etatScoot = new JButton("Etat Scooter");
    JButton afficheAll = new JButton("Affiche All");
    JButton quit = new JButton("Quitter");

    public Gui() {
        super("swing app");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        JPanel contentPane = (JPanel) this.getContentPane();
        this.setContentPane(contentPane);
        louer.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fctLouer();
            }
        }));
        retour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fctRetour();
            }
        });

        etatScoot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                fctAfficheStat();
            }
        });

        afficheAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        contentPane.add(createRightPanel(), BorderLayout.EAST);

    }

    private void fctRetour() {

        JPanel retour = new JPanel(new FlowLayout());
        JTextField txtId = new JTextField();
        JButton confirm = new JButton("confirm");
        retour.add(txtId);
        retour.add(confirm);
        retour.add(createRightPanel(), BorderLayout.EAST);
        this.setContentPane(retour);
        this.revalidate();
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

    private void fctLouer() {
        JPanel pannel = new JPanel(new FlowLayout());
        JTextField idscoot = new JTextField("id scoot");
        JTextField DateDeb = new JTextField("jj/mm/ann");
        JTextField DateFin = new JTextField("jj/mm/ann");
        JButton louer2 = new JButton("Appuyer pour louer !");
        louer2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        pannel.add(idscoot, BorderLayout.CENTER);
        pannel.add(DateDeb, BorderLayout.CENTER);
        pannel.add(DateFin, BorderLayout.CENTER);
        pannel.add(louer2, BorderLayout.CENTER);
        pannel.add(createRightPanel(), BorderLayout.EAST);
        this.setContentPane(pannel);
        this.revalidate();

    }

    private void fctAfficheStat() {

        JPanel pannel = new JPanel(new FlowLayout());
        JTextField idRentrer = new JTextField("id scoot");
        JButton chercher = new JButton("chercher !");

        pannel.add(idRentrer);
        pannel.add(chercher);
        pannel.add(createRightPanel(), BorderLayout.EAST);
        this.setContentPane(pannel);
        this.revalidate();
    }

    public static void main(String[] args) {
        new ParcAuto();
        new Gui().setVisible(true);
    }
}