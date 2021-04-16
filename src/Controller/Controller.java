package src.Controller;

import src.Model.*;
import src.View.MyPanel;
import src.View.View;

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

    public static void imageScrambling(Component parent, String choice, int peroid) {

        try {
            Class<?> temp = Class.forName("src.Model." + choice);
            Scramble cal = (Scramble) temp.newInstance();
            // cal.scrambling(Model.getSrcImage(), file, peroid);
            Model.setDesImage(cal.scrambling(Model.getSrcImage(), peroid));
            ImageIcon tempImage = new ImageIcon(Model.getDesImage());

            MyPanel.getInstance().setDesImage(tempImage.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT));
            View.getInstance().pack();
        } catch (Exception t) {
            t.printStackTrace();
            System.out.println("Something went wrong while saving");
        }
        // }
    }

    public static void imageSaveFile(Component parent) {
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setSelectedFile(new File("File"));

        int result = fileChooser.showSaveDialog(parent);

        if (result == JFileChooser.APPROVE_OPTION) {

            File file = fileChooser.getSelectedFile();
            BufferedImage desImage = Model.getDesImage();
            try {
                ImageIO.write(desImage, "jpg", file);
                // Model.setDesImage(file);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
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
