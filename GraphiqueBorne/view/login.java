package GraphiqueBorne.view;

import java.util.HashMap;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import GraphiqueBorne.model.IDandPassord;

public class login extends JFrame {
    JButton loginButtons = new JButton("login");
    JButton resetButtons = new JButton("Reset");
    JTextField userIDield = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JLabel userIDlabel = new JLabel("UserID:");
    JLabel userPassordlabel = new JLabel("Passord:");
    JLabel messageLabel = new JLabel("");
    HashMap<String, String> logininfo = new HashMap<String, String>();

    login(HashMap<String, String> loginfoOriginal) {
        super("log");
        logininfo = loginfoOriginal;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);

        JPanel contentPane = (JPanel) this.getContentPane();

        contentPane.add(creatPanel());

    }

    private JPanel creatPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2));

        resetButtons.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userIDield.setText("");
                userPasswordField.setText("");
            }
        });
        loginButtons.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    testIDandP();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        }));
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

    // public static void main(String[] args) throws Exception {
    // // Apply a look'n feel

    // // On peut télécharger des

    // // Sart a new Window
    // IDandPassord idandPassord = new IDandPassord();
    // login MyWindow = new login(idandPassord.getLogininfo());
    // MyWindow.setVisible(true);
    // }

    public void testIDandP() throws IOException {
        String userID = userIDield.getText();
        String passord = String.valueOf(userPasswordField.getPassword());

        if (logininfo.containsKey(userID)) {
            if (logininfo.get(userID).equals(IDandPassord.encrypt(passord))) {
                // messageLabel.setForeground(Color.GREEN);
                messageLabel.setText("Login Successful");
                dispose();
            } else {
                messageLabel.setText("Wrong Passord");
            }
        } else {
            messageLabel.setText("Username not found");
        }
    }

}
