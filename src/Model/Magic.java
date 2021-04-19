package src.Model;

import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.*;

public class Magic implements Scramble {
    public Magic() {
    }

    @Override
    public BufferedImage scrambling(File srcImageFile, int period) {

        BufferedImage srcImage = null, desImage = null;

        try {
            srcImage = ImageIO.read(srcImageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int width = srcImage.getWidth();
        int height = srcImage.getHeight();
        if (width != height) {
            System.out.println("Image size must be N x N!");
        } else {
            int[][] imageMatrix = new int[height][width];

            int N = width;
            int[][] magic = new int[N][N];
            int mx = 0, my = N / 2, mtotal = N * N;
            for (int mi = 1; mi < mtotal; mi++) {
                magic[mx][my] = mi;
                int mm = (mx - 1 + N) % N;
                int mn = (my + 1) % N;
                if (magic[mm][mn] > 0) {
                    mx = (mx + 1) % N;
                } else {
                    mx = mm;
                    my = mn;
                }
            }

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    imageMatrix[i][j] = srcImage.getRGB(i, j);
                }
            }
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
                        int col = 0, row = 0;
                        if (magic[i][j] != 0) {
                            if (magic[i][j] % N == 0) {
                                col = N - 1;
                                row = magic[i][j] / N - 1;
                            } else {
                                col = magic[i][j] % N - 1;
                                row = magic[i][j] / N;
                            }
                        }

                        desMatrix[i][j] = srcMatrix[row][col];
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

    }
}