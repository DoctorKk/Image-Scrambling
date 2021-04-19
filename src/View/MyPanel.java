package src.View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import src.Controller.*;

public class MyPanel extends JPanel {
    private static MyPanel instance;
    private JLabel srcLable, desLabel, evaluationLabel;

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
                String period = JOptionPane.showInputDialog(null, "迭代次数：");
                Controller.imageScrambling(null, "Arnold", Integer.valueOf(period));
            }
        });

        final JButton btnLogistic = new JButton();
        btnLogistic.setText("Logistic");
        btnLogistic.setBorderPainted(true);
        btnLogistic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String period = JOptionPane.showInputDialog(null, "迭代次数：");
                Controller.imageScrambling(null, "Logistic", Integer.valueOf(period));
            }
        });

        final JButton btnMagic = new JButton();
        btnMagic.setText("Magic");
        btnMagic.setBorderPainted(true);
        btnMagic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String period = JOptionPane.showInputDialog(null, "迭代次数：");
                Controller.imageScrambling(null, "Magic", Integer.valueOf(period));
            }
        });

        final JButton btnSave = new JButton();
        btnSave.setText("保存图片");
        btnSave.setBorderPainted(true);
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.imageSaveFile(null);
            }
        });

        final JButton btnInformationEntropy = new JButton();
        btnInformationEntropy.setText("InformationEntropy");
        btnInformationEntropy.setBorderPainted(true);
        btnInformationEntropy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.imageEvaluation(null, "InformationEntropy");
            }
        });

        final JButton btnCov = new JButton();
        btnCov.setText("Cov");
        btnCov.setBorderPainted(true);
        btnCov.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.imageEvaluation(null, "Cov");
            }
        });

        Box vBox = Box.createVerticalBox();
        vBox.setPreferredSize(new Dimension(100, 180));
        vBox.add(btnChoose);
        vBox.add(btnArnold);
        vBox.add(btnLogistic);
        vBox.add(btnMagic);
        vBox.add(btnInformationEntropy);
        vBox.add(btnCov);
        vBox.add(btnSave);
        add(vBox, BorderLayout.WEST);
    }

    private void initDrawBoard() {
        ImageIcon srcImage = new ImageIcon("sample.jpg");
        srcImage = new ImageIcon(srcImage.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT));
        srcLable = new JLabel();
        desLabel = new JLabel();
        evaluationLabel = new JLabel();
        srcLable.setIcon(srcImage);
        // Box vBox = Box.createHorizontalBox();
        // vBox.add(srcLable);
        // vBox.add(desLabel);
        add(srcLable, BorderLayout.CENTER);
        add(desLabel, BorderLayout.EAST);
        // add(evaluationLabel, BorderLayout.SOUTH);
    }

    public void setEvaluation(String t) {
        // JLabel temp = new JLabel();
        Box vBox = Box.createVerticalBox();
        String[] temp = t.split("\r\n");
        for (String i : temp) {
            JLabel T = new JLabel(i);
            T.setFont(new Font("宋体", Font.PLAIN, 20));
            vBox.add(T);
        }
        add(vBox, BorderLayout.SOUTH);
        // evaluationLabel.setText(t);
        // evaluationLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        // add(temp, BorderLayout.SOUTH);
        View.getInstance().pack();
    }

    public void setSrcImage(Image srcImage) {
        srcLable.setIcon(new ImageIcon(srcImage));
        srcLable.setText("SRCImage");
        srcLable.setVerticalTextPosition(JLabel.BOTTOM);
        srcLable.setHorizontalTextPosition(JLabel.CENTER);
    }

    public void setDesImage(Image desImage, String name) {
        desLabel.setIcon(new ImageIcon(desImage));
        desLabel.setText(name);
        desLabel.setVerticalTextPosition(JLabel.BOTTOM);
        desLabel.setHorizontalTextPosition(JLabel.CENTER);
    }

}
