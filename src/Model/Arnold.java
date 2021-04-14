package src.Model;

import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.*;

public class Arnold implements Scramble {
    public Arnold() {
    }

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

        BufferedImage desImage = null;
        if (width != height) {
            System.out.println("Image size must be N x N!");
        } else {
            int[][] imageMatrix = new int[height][width];

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    imageMatrix[i][j] = srcImage.getRGB(i, j);
                }
            }
            int N = width;
            int[][] srcMatrix = imageMatrix;
            int[][] desMatrix = new int[N][N];
            desImage = new BufferedImage(width, height, srcImage.getType());
            for (int n = 0; n < period; n++) {
                if (n != 0) {
                    srcMatrix = desMatrix;
                    desMatrix = new int[N][N];
                }
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        desMatrix[(i + j) % N][(i + 2 * j) % N] = srcMatrix[i][j];
                    }
                }
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    desImage.setRGB(i, j, desMatrix[i][j]);
                }
            }
        }
        return desImage;
        /*
         * try { ImageIO.write(desImage, "jpg", desImageFile);
         * Model.setDesImage(desImageFile); } catch (IOException e) {
         * e.printStackTrace(); }
         */
    }
}
