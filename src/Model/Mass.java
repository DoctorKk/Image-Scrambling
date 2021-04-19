package src.Model;

import java.io.*;
import java.util.Arrays;
import java.awt.image.*;
import javax.imageio.ImageIO;

public class Mass implements Evaluate {
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
        int REQ = 0, GEQ = 0, BEQ = 0;
        int width = srcImage.getWidth();
        int height = srcImage.getHeight();
        int[] R1H = new int[256];
        int[] R2H = new int[256];
        int[] G1H = new int[256];
        int[] G2H = new int[256];
        int[] B1H = new int[256];
        int[] B2H = new int[256];
        Arrays.fill(R1H, 0);
        Arrays.fill(R2H, 0);
        Arrays.fill(G1H, 0);
        Arrays.fill(G2H, 0);
        Arrays.fill(B1H, 0);
        Arrays.fill(B2H, 0);

        int temp = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                temp = srcImage.getRGB(i, j);
                R1H[(temp & 0xff0000) >> 16]++;
                G1H[(temp & 0xff00) >> 8]++;
                B1H[temp & 0xff]++;

                temp = desImage.getRGB(i, j);
                R2H[(temp & 0xff0000) >> 16]++;
                G2H[(temp & 0xff00) >> 8]++;
                B2H[temp & 0xff]++;
            }
        }
        for (int i = 0; i < 256; i++) {
            REQ += Math.abs(R1H[i] - R2H[i]);
            GEQ += Math.abs(G1H[i] - G2H[i]);
            BEQ += Math.abs(B1H[i] - B2H[i]);
        }
        REQ /= 256;
        GEQ /= 256;
        BEQ /= 256;
        String result = "EQ-Mass    R: " + Integer.toString(REQ) + "    G: " + Integer.toString(GEQ) + "    B: "
                + Integer.toString(BEQ);
        return result;
    }
}
