package src.View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import src.Controller.*;

public class MyPanel extends JPanel {
    private static MyPanel instance;
    private JLabel srcLable, desLabel;

    public static MyPanel getInstance() {
        if (instance == null)
            instance = new MyPanel();
        return instance;
    }

    private MyPanel() {
        super(new BorderLayout());
        init();
    }

    private void init() {
        initButtons();

        initDrawBoard();
    }

    private void initButtons() {
        final JButton btnChoose = new JButton();
        btnChoose.setText("选择图片");
        btnChoose.setBorderPainted(true);
        btnChoose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.showFileOpenDialog(null);
            }
        });

        final JButton btnArnold = new JButton();
        btnArnold.setText("Arnold");
        btnArnold.setBorderPainted(true);
        btnArnold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.showFileSaveDialog(null, "Arnold", 50);
            }
        });

        Box vBox = Box.createHorizontalBox();
        vBox.add(btnChoose);
        vBox.add(btnArnold);
        add(vBox, BorderLayout.WEST);
    }

    private void initDrawBoard() {
        ImageIcon srcImage = new ImageIcon("sample.jpg");
        srcImage = new ImageIcon(srcImage.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT));
        srcLable = new JLabel();
        desLabel = new JLabel();
        srcLable.setIcon(srcImage);
        // Box vBox = Box.createHorizontalBox();
        // vBox.add(srcLable);
        // vBox.add(desLabel);
        add(srcLable, BorderLayout.CENTER);
        add(desLabel, BorderLayout.EAST);
    }

    public void setSrcImage(Image srcImage) {
        srcLable.setIcon(new ImageIcon(srcImage));
    }

    public void setDesImage(Image desImage) {
        desLabel.setIcon(new ImageIcon(desImage));
    }

}
