package mvc;

import java.awt.*;
import javax.swing.*;

public class MyFrame extends JFrame {
    MyFrame() {
        setTitle("louscoot");// set title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// exit
        setResizable(false);// prevent from resizing
        setSize(420, 420);// set the x and y dimension
        setVisible(true);
        ImageIcon image = new ImageIcon("mvc/icon.jpg"); // create an image icon
        setIconImage(image.getImage()); // change icon

    }
}