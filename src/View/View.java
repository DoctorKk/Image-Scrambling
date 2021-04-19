package src.View;

import javax.swing.*;

//View单例
public class View extends JFrame {

    private final String title = "Image Scrambling";
    private final int height = 900;
    private final int width = 1600;
    private static View instance;

    public static View getInstance() {
        if (instance == null)
            instance = new View();
        return instance;
    }

    private View() {
        super();
        init();
    }

    private void init() {
        this.setTitle(title);
        this.setSize(width, height);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(MyPanel.getInstance());
        pack();
        this.setVisible(true);
    }

}
