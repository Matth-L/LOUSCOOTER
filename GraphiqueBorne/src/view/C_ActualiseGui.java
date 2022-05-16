package GraphiqueBorne.src.view;

import java.io.IOException;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.BadLocationException;

import GraphiqueBorne.src.controller.Controller;

public class C_ActualiseGui extends B_FonctionGui {

    public C_ActualiseGui() {

    }

    public C_ActualiseGui(String titre) {
        super(titre);
    }

    /*
     * toutes les fontions crée un panel et initilaliste la barre de droite et la
     * barre de status pour les message a l'utilisateur ainsi que la fonction
     * adéquate fctLouer pour louer etc etc
     */

    void louerActualise(String s) {
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.removeAll();
        contentPane.add(createRightPanel2(), BorderLayout.EAST);
        contentPane.add(creatStatusBar(s), BorderLayout.SOUTH);
        contentPane.add(fctLouer(), BorderLayout.CENTER);
        contentPane.updateUI();
    }

    void AjoutScootActualise() {
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.removeAll();
        contentPane.add(createRightPanel3(), BorderLayout.EAST);
        contentPane.add(fctAjoutScoot(), BorderLayout.CENTER);
        contentPane.updateUI();
    }

    void deleteScootActualise() {
        JPanel contentPane = (JPanel) getContentPane();
        contentPane.removeAll();
        contentPane.add(createRightPanel3(), BorderLayout.EAST);
        contentPane.add(creatStatusBar(""), BorderLayout.SOUTH);
        contentPane.add(fctSupprScoot(), BorderLayout.NORTH);
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

    void AfficheAllActualise() throws IOException, BadLocationException {
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.removeAll();
        contentPane.add(rechercheDate(), BorderLayout.NORTH);
        contentPane.add(afficheAll(new Controller().chgtDonne()), BorderLayout.CENTER);
        contentPane.add(creatStatusBar(), BorderLayout.SOUTH);
        contentPane.add(createRightPanel2(), BorderLayout.EAST);
        contentPane.updateUI();
    }

    void mettreEnReparationActualise(String s) {
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.removeAll();
        contentPane.add(createRightPanel3(), BorderLayout.EAST);
        contentPane.add(creatStatusBar(s), BorderLayout.SOUTH);
        contentPane.add(fctMettreEnReparation(), BorderLayout.CENTER);
        contentPane.updateUI();
    }

    void retirerEnReparationActualise(String s) {
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.removeAll();
        contentPane.add(createRightPanel3(), BorderLayout.EAST);
        contentPane.add(creatStatusBar(s), BorderLayout.SOUTH);
        contentPane.add(fctRetirereEtatReparation(), BorderLayout.CENTER);
        contentPane.updateUI();
    }

    void VerifAdminActualise(String s) throws IOException {
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.removeAll();
        contentPane.add(fctVerifAdmin(), BorderLayout.CENTER);
        contentPane.add(creatStatusBar(s), BorderLayout.SOUTH);
        contentPane.add(createRightPanel2(), BorderLayout.EAST);
        contentPane.updateUI();
    }

    void suppLocActualise() {
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.removeAll();
        contentPane.add(createRightPanel2(), BorderLayout.EAST);
        contentPane.add(creatStatusBar(""), BorderLayout.SOUTH);
        contentPane.add(fctRetirerUneLocation(), BorderLayout.CENTER);
        contentPane.updateUI();
    }
}
