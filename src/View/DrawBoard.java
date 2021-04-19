package src.View;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import src.Model.Model;

import java.awt.Panel;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Graphics;

public class DrawBoard extends Panel {
    private static DrawBoard instance;

    private final Image screen = new BufferedImage(1079, 1079, 2);
    private final Graphics2D screenGraphic = (Graphics2D) screen.getGraphics();

    private Image backGroundImage;

    private JLabel image;

    private DrawBoard() {
        init();
    }

    public static DrawBoard getInstance() {
        if (instance == null)
            instance = new DrawBoard();
        return instance;
    }

    private void init() {
        image = new JLabel();
        add(image);
        loadDefaultImage();
    }

    private void loadDefaultImage() {
        image.setIcon(new ImageIcon("sample.jpg"));
    }

    private void loadImage() {
        ImageIcon tempImage = null;
        try {
            tempImage = new ImageIcon(Model.getSrcImage().toURL());
        } catch (Exception e) {
            System.out.println(e);
        }
        if (tempImage != null) {
            backGroundImage = tempImage.getImage();
            drawImage();
        } else {
            System.out.println("Error");
        }
    }

    private void drawImage() {
        screenGraphic.drawImage(backGroundImage, 0, 0, null);
    }

    public void paint(Graphics g) {
        g.drawImage(screen, 0, 0, null);
    }

}