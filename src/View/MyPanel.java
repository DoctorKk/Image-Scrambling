package src.View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import src.Controller.*;

public class MyPanel extends JPanel {
    private static MyPanel instance;
    private JLabel srcLable, desLabel;
    private Box evaluation;

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
        btnChoose.setPreferredSize(new Dimension(250, 150));
        btnChoose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.showFileOpenDialog(null);
            }
        });

        final JButton btnArnold = new JButton();
        btnArnold.setText("Arnold");
        btnArnold.setBorderPainted(true);
        btnArnold.setPreferredSize(new Dimension(250, 150));
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
        btnLogistic.setPreferredSize(new Dimension(250, 150));
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
        btnMagic.setPreferredSize(new Dimension(250, 150));
        btnMagic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String period = JOptionPane.showInputDialog(null, "迭代次数：");
                Controller.imageScrambling(null, "Magic", Integer.valueOf(period));
            }
        });

        final JButton btnMix = new JButton();
        btnMix.setText("Mix");
        btnMix.setBorderPainted(true);
        btnMix.setPreferredSize(new Dimension(250, 150));
        btnMix.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String period = JOptionPane.showInputDialog(null, "迭代次数：");
                Controller.imageScrambling(null, "Mix", Integer.valueOf(period));
            }
        });

        final JButton btnSave = new JButton();
        btnSave.setText("保存图片");
        btnSave.setBorderPainted(true);
        btnSave.setPreferredSize(new Dimension(250, 150));
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.imageSaveFile(null);
            }
        });

        final JButton btnInformationEntropy = new JButton();
        btnInformationEntropy.setText("Entropy");
        btnInformationEntropy.setBorderPainted(true);
        btnInformationEntropy.setPreferredSize(new Dimension(250, 150));
        btnInformationEntropy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.imageEvaluation(null, "InformationEntropy");
            }
        });

        final JButton btnCov = new JButton();
        btnCov.setText("Cov");
        btnCov.setBorderPainted(true);
        btnCov.setPreferredSize(new Dimension(250, 150));
        btnCov.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.imageEvaluation(null, "Cov");
            }
        });

        final JButton btnMass = new JButton();
        btnMass.setText("Mass");
        btnMass.setBorderPainted(true);
        btnMass.setPreferredSize(new Dimension(250, 150));
        btnMass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.imageEvaluation(null, "Mass");
            }
        });

        final JButton btnGVD = new JButton();
        btnGVD.setText("GVD");
        btnGVD.setBorderPainted(true);
        btnGVD.setPreferredSize(new Dimension(250, 150));
        btnGVD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.imageEvaluation(null, "GVD");
            }
        });

        final JButton btnBoltzmann = new JButton();
        btnBoltzmann.setText("Boltzmann");
        btnBoltzmann.setBorderPainted(true);
        btnBoltzmann.setPreferredSize(new Dimension(250, 150));
        btnBoltzmann.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.imageEvaluation(null, "Boltzmann");
            }
        });

        Box vBox = Box.createVerticalBox();
        vBox.setPreferredSize(new Dimension(100, 350));
        vBox.add(btnChoose);
        vBox.add(btnArnold);
        vBox.add(btnLogistic);
        vBox.add(btnMagic);
        vBox.add(btnMix);
        vBox.add(btnInformationEntropy);
        vBox.add(btnCov);
        vBox.add(btnMass);
        vBox.add(btnGVD);
        vBox.add(btnBoltzmann);
        vBox.add(btnSave);
        add(vBox, BorderLayout.WEST);
    }

    private void initDrawBoard() {
        ImageIcon srcImage = new ImageIcon("sample.jpg");
        srcImage = new ImageIcon(srcImage.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT));
        srcLable = new JLabel();
        desLabel = new JLabel();
        evaluation = Box.createVerticalBox();
        // evaluationLabel = new JLabel();
        srcLable.setIcon(srcImage);
        // Box vBox = Box.createHorizontalBox();
        // vBox.add(srcLable);
        // vBox.add(desLabel);
        add(srcLable, BorderLayout.CENTER);
        add(desLabel, BorderLayout.EAST);
        add(evaluation, BorderLayout.SOUTH);
        // add(evaluationLabel, BorderLayout.SOUTH);
    }

    public void setEvaluation(String t) {
        // JLabel temp = new JLabel();
        evaluation.removeAll();
        String[] temp = t.split("\r\n");
        for (String i : temp) {
            JLabel T = new JLabel(i);
            T.setFont(new Font("宋体", Font.PLAIN, 30));
            evaluation.add(T);
        }
        evaluation.repaint();
        // add(evaluation, BorderLayout.SOUTH);
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
