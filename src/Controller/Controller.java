package src.Controller;

import src.Model.*;
import src.View.MyPanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;

public class Controller {

    private Controller() {
    }

    public static void showFileOpenDialog(Component parent) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setMultiSelectionEnabled(false);

        int result = fileChooser.showOpenDialog(parent);

        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileChooser.getSelectedFile();
                if (isImage(file)) {
                    Model.setSrcImage(file);
                    ImageIcon temp = new ImageIcon(file.getCanonicalPath());
                    MyPanel.getInstance().setSrcImage(temp.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT));
                } else {
                    System.out.println(file.getAbsolutePath());
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void showFileSaveDialog(Component parent, String choice, int peroid) {
        // JFileChooser fileChooser = new JFileChooser();

        // fileChooser.setSelectedFile(new File("File"));

        // int result = fileChooser.showSaveDialog(parent);

        // if (result == JFileChooser.APPROVE_OPTION) {

        // File file = fileChooser.getSelectedFile();
        try {
            Class<?> temp = Class.forName("src.Model." + choice);
            Scramble cal = (Scramble) temp.newInstance();
            // cal.scrambling(Model.getSrcImage(), file, peroid);

            ImageIcon tempImage = new ImageIcon(cal.scrambling(Model.getSrcImage(), peroid));

            MyPanel.getInstance().setDesImage(tempImage.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT));
        } catch (Exception t) {
            t.printStackTrace();
            System.out.println("Something went wrong while saving");
        }
        // }
    }

    public static boolean isImage(File file) {
        boolean flag = false;
        try {
            BufferedImage bufreader = ImageIO.read(file);
            if (bufreader.getWidth() == 0 || bufreader.getHeight() == 0) {
                flag = false;
            } else {
                flag = true;
            }

        } catch (IOException e) {
            flag = false;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
}
