package GraphiqueBorne.view;

import java.awt.Color;

import javax.swing.JButton;

public class Bouton extends JButton {
    public Bouton(String text) {
        super(text);
        setFocusable(false); // enleve la bordure autour du mot
        setForeground(Color.CYAN);
    }
}
