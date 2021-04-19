package src.Model;

import java.io.*;
import java.util.Random;
import java.awt.image.*;

public class Cov implements Evaluate {
    private int N = 3000;

    @Override
    public String evaluate() {
        BufferedImage img = Model.getDesImage();

        int height = img.getHeight();
        int width = img.getWidth();

        BufferedImage R = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        BufferedImage G = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        BufferedImage B = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int temp = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                temp = img.getRGB(i, j);
                R.setRGB(i, j, temp & 0xff0000);
                G.setRGB(i, j, temp & 0xff00);
                B.setRGB(i, j, temp & 0xff);
            }
        }
        int[] row = randomArray(height);
        int[] col = randomArray(width);

        double[] Rxy = cal(R, row, col);
        double[] Gxy = cal(G, row, col);
        double[] Bxy = cal(B, row, col);

        String result = "Cov-horizion   R: " + Double.toString(Rxy[0]) + "  G: " + Double.toString(Gxy[0]) + "  B: "
                + Double.toString(Bxy[0]) + "\r\n" + "Cov-vertical   R: " + Double.toString(Rxy[1]) + "   G: "
                + Double.toString(Gxy[1]) + "   B: " + Double.toString(Bxy[1]);
        return result;
    }

    private double[] cal(BufferedImage img, int[] row, int[] col) {
        double[] nums = new double[2];

        double ex = 0, dx = 0, horizonEY = 0, horizonDY = 0, horizonCov = 0, horizonResult = 0, verticalEY = 0,
                verticalDY = 0, verticalCov = 0, verticalResult = 0;
        for (int i = 0; i < N; i++) {
            ex += img.getRGB(row[i], col[i]);
        }
        ex /= N;

        for (int i = 0; i < N; i++) {
            dx += (img.getRGB(row[i], col[i]) - ex) * (img.getRGB(row[i], col[i]) - ex);
        }
        dx /= N;

        for (int i = 0; i < N; i++) {
            horizonEY += img.getRGB(row[i], col[i] + 1);
        }
        horizonEY /= N;

        for (int i = 0; i < N; i++) {
            horizonDY += (img.getRGB(row[i], col[i] + 1) - horizonEY) * (img.getRGB(row[i], col[i] + 1) - horizonEY);
        }
        horizonDY /= N;

        for (int i = 0; i < N; i++) {
            horizonCov += (img.getRGB(row[i], col[i]) - horizonEY) * (img.getRGB(row[i], col[i] + 1) - horizonEY);
        }
        horizonCov /= N;
        horizonResult = horizonCov / (Math.sqrt(dx) * Math.sqrt(horizonDY));

        for (int i = 0; i < N; i++) {
            verticalEY += img.getRGB(row[i] + 1, col[i]);
        }
        verticalEY /= N;

        for (int i = 0; i < N; i++) {
            verticalDY += (img.getRGB(row[i] + 1, col[i]) - verticalEY) * (img.getRGB(row[i], col[i] + 1) - verticalEY);
        }
        verticalDY /= N;

        for (int i = 0; i < N; i++) {
            verticalCov += (img.getRGB(row[i], col[i]) - verticalEY) * (img.getRGB(row[i] + 1, col[i]) - verticalEY);
        }
        verticalCov /= N;
        verticalResult = verticalCov / (Math.sqrt(dx) * Math.sqrt(verticalDY));
        nums[0] = horizonResult;
        nums[1] = verticalResult;
        return nums;
    }

    private int[] randomArray(int n) {
        int[] nums = new int[N];
        Random r = new Random();
        for (int i = 0; i < N; i++) {
            nums[i] = r.nextInt(n - 2);
        }
        return nums;
    }
}
