package graphique.view;

import java.awt.Color;

import javax.swing.JButton;

public class Bouton extends JButton {
    Bouton(String text) {
        super(text);
        setFocusable(false); // enleve la bordure autour du mot
        setForeground(Color.CYAN);
    }
}
