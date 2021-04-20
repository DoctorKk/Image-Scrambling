package src.Model;

import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

public class GVD implements Evaluate {
    @Override
    public String evaluate() {
        File srcImageFile = Model.getSrcImage();
        BufferedImage srcImage = null;
        BufferedImage desImage = Model.getDesImage();
        try {
            srcImage = ImageIO.read(srcImageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int width = srcImage.getWidth();
        int height = srcImage.getHeight();
        BufferedImage R1 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        BufferedImage G1 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        BufferedImage B1 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        BufferedImage R2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        BufferedImage G2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        BufferedImage B2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int temp = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                temp = srcImage.getRGB(i, j);
                R1.setRGB(i, j, (temp & 0xff0000) >> 16);
                G1.setRGB(i, j, (temp & 0xff00) >> 8);
                B1.setRGB(i, j, temp & 0xff);
                temp = desImage.getRGB(i, j);
                R2.setRGB(i, j, (temp & 0xff0000) >> 16);
                G2.setRGB(i, j, (temp & 0xff00) >> 8);
                B2.setRGB(i, j, temp & 0xff);
            }
        }
        double Rxy = gvd(R1, R2), Gxy = gvd(G1, G2), Bxy = gvd(B1, B2);
        String result = "GVD    R: " + Double.toString(Rxy) + "   G: " + Double.toString(Gxy) + "    B: "
                + Double.toString(Bxy);
        return result;
    }

    private double gvd(BufferedImage c1, BufferedImage c2) {
        double a0 = 0, a1 = 0;
        int n = c1.getWidth();
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                int temp = 0;
                temp += (c1.getRGB(i, j) - c1.getRGB(i - 1, j)) * (c1.getRGB(i, j) - c1.getRGB(i - 1, j));
                temp += (c1.getRGB(i, j) - c1.getRGB(i + 1, j)) * (c1.getRGB(i, j) - c1.getRGB(i + 1, j));
                temp += (c1.getRGB(i, j) - c1.getRGB(i, j - 1)) * (c1.getRGB(i, j) - c1.getRGB(i, j - 1));
                temp += (c1.getRGB(i, j) - c1.getRGB(i, j + 1)) * (c1.getRGB(i, j) - c1.getRGB(i, j + 1));

                temp /= 4;
                a0 += temp;
                temp = 0;

                temp += (c2.getRGB(i, j) - c2.getRGB(i - 1, j)) * (c2.getRGB(i, j) - c2.getRGB(i - 1, j));
                temp += (c2.getRGB(i, j) - c2.getRGB(i + 1, j)) * (c2.getRGB(i, j) - c2.getRGB(i + 1, j));
                temp += (c2.getRGB(i, j) - c2.getRGB(i, j - 1)) * (c2.getRGB(i, j) - c2.getRGB(i, j - 1));
                temp += (c2.getRGB(i, j) - c2.getRGB(i, j + 1)) * (c2.getRGB(i, j) - c2.getRGB(i, j + 1));
                temp /= 4;
                a1 += temp;
            }
        }
        a0 /= (double) ((n - 2) * (n - 2));
        a1 /= (double) ((n - 2) * (n - 2));
        return (a1 - a0) / (a1 + a0);
    }
}
