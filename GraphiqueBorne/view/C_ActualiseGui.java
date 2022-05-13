package GraphiqueBorne.view;

import java.io.IOException;
import java.awt.*;
import javax.swing.*;
import GraphiqueBorne.controller.Controller;

public class C_ActualiseGui extends B_FonctionGui {

    public C_ActualiseGui() {

    }

    public C_ActualiseGui(String titre) {
        super(titre);
    }

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
        contentPane.add(createRightPanel2(), BorderLayout.EAST);
        contentPane.add(fctAjoutScoot(), BorderLayout.CENTER);
        contentPane.updateUI();
    }

    void deleteScootActualise() {
        JPanel contentPane = (JPanel) getContentPane();
        contentPane.removeAll();
        contentPane.add(createRightPanel2(), BorderLayout.EAST);
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

    void AfficheAllActualise(String s) throws IOException {
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.removeAll();
        contentPane.add(afficheAll(new Controller().btnafficheAllScoot()), BorderLayout.CENTER);
        contentPane.add(creatStatusBar(s), BorderLayout.SOUTH);
        contentPane.add(createRightPanel2(), BorderLayout.EAST);
        contentPane.updateUI();
    }

    void mettreEnReparationActualise(String s) {
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.removeAll();
        contentPane.add(createRightPanel2(), BorderLayout.EAST);
        contentPane.add(creatStatusBar(s), BorderLayout.SOUTH);
        contentPane.add(fctMettreEnReparation(), BorderLayout.CENTER);
        contentPane.updateUI();
    }

    void retirerEnReparationActualise(String s) {
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.removeAll();
        contentPane.add(createRightPanel2(), BorderLayout.EAST);
        contentPane.add(creatStatusBar(s), BorderLayout.SOUTH);
        contentPane.add(fctRetirereEtatReparation(), BorderLayout.CENTER);
        contentPane.updateUI();
    }

}
