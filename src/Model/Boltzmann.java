package src.Model;

import java.awt.image.*;
import java.util.Arrays;

public class Boltzmann implements Evaluate {
    private int N = 2;

    @Override
    public String evaluate() {
        BufferedImage img = Model.getDesImage();

        int n = img.getWidth();

        int entropy = 0;
        int[] enMode = new int[8];

        for (int i = 0; i <= n - N; i++) {
            for (int j = 0; j <= n - N; j++) {
                int[] temp = new int[4];
                int count = 4;
                temp[0] = img.getRGB(i, j);
                temp[1] = img.getRGB(i + 1, j);
                temp[2] = img.getRGB(i, j + 1);
                temp[3] = img.getRGB(i + 1, j + 1);
                Arrays.sort(temp);
                if (temp[0] == temp[1])
                    count--;
                if (temp[1] == temp[2])
                    count--;
                if (temp[2] == temp[3])
                    count--;
                if (count == 4)
                    entropy += 24;
                else if (count == 3)
                    entropy += 8;
                else if (count == 1)
                    entropy += 1;
                else
                    entropy += 6;
                temp[0] = (int) (0.3 * ((temp[0] >> 16) & 0xff) + 0.59 * ((temp[0] >> 8) & 0xff)
                        + 0.11 * ((temp[0]) & 0xff));

                temp[1] = (int) (0.3 * ((temp[1] >> 16) & 0xff) + 0.59 * ((temp[1] >> 8) & 0xff)
                        + 0.11 * ((temp[1]) & 0xff));

                temp[2] = (int) (0.3 * ((temp[2] >> 16) & 0xff) + 0.59 * ((temp[2] >> 8) & 0xff)
                        + 0.11 * ((temp[2]) & 0xff));

                temp[3] = (int) (0.3 * ((temp[3] >> 16) & 0xff) + 0.59 * ((temp[3] >> 8) & 0xff)
                        + 0.11 * ((temp[3]) & 0xff));
                int tt = 1;
                for (int t = 0; t < 8; t++) {
                    count = 0;
                    count += temp[0] & tt;
                    count += temp[1] & tt;
                    count += temp[2] & tt;
                    count += temp[3] & tt;
                    if (count == 0 || count == 4)
                        enMode[t]++;
                    else if (count == 2)
                        enMode[t] += 6;
                    else
                        enMode[t] += 8;
                }
            }
        }

        double r = (double) entropy / (n * n);
        double t = 0;
        for (int i : enMode) {
            t += (double) i / (n * n);
        }
        return "Boltzmann: " + Double.toString(r) + "     BitMap Mode: " + Double.toString(t);
    }
}