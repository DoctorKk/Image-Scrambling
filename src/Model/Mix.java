package src.Model;

import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

public class Mix implements Scramble {

    @Override
    public BufferedImage scrambling(File srcImageFile, int period) {
        BufferedImage srcImage = null;
        try {
            srcImage = ImageIO.read(srcImageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int width = srcImage.getWidth();
        int height = srcImage.getHeight();
        BufferedImage desImage = new BufferedImage(width, height, srcImage.getType());

        BufferedImage R = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        BufferedImage G = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        BufferedImage B = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int temp = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                temp = srcImage.getRGB(i, j);
                R.setRGB(i, j, temp & 0xff0000);
                G.setRGB(i, j, temp & 0xff00);
                B.setRGB(i, j, temp & 0xff);
            }
        }
        R = Arnold.scrambling(R, period);
        G = Arnold.scrambling(G, period + 10);
        B = Arnold.scrambling(B, period + 25);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                temp = ((R.getRGB(i, j) >> 16) & 0xff) | (G.getRGB(i, j) & 0xff00) | ((B.getRGB(i, j) & 0xff) << 16);

                desImage.setRGB(i, j, temp);
            }
        }
        desImage = Logistic.scrambling(desImage, period);
        return desImage;
    }
}
