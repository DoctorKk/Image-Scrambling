package src.View;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import src.Controller.*;
import src.Model.*;

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
        this.setContentPane(MyPanel.getInstance());
        pack();
        this.setVisible(true);
    }

}
