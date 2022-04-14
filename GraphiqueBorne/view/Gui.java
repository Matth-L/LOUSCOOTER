package GraphiqueBorne.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Gui extends JFrame {

    public Gui() {
        super("swing app");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);

        JPanel contentPane = (JPanel) this.getContentPane();

        contentPane.add(createRightPanel(), BorderLayout.EAST);
    }

    private JPanel createRightPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1));

        panel.add(new JButton("button 1"));
        panel.add(new JButton("button 2"));
        panel.add(new JButton("button 3"));
        panel.add(new JButton("button 4"));
        panel.add(new JButton("button 5"));

        return panel;
    }

    public static void main(String[] args) {
        new Gui().setVisible(true);
    }
}