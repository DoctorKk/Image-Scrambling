package src.Model;

import java.awt.image.*;
import java.util.HashMap;

public class InformationEntropy implements Evaluate {
    public InformationEntropy() {
    }

    @Override
    public String evaluate() {
        BufferedImage img = Model.getDesImage();

        int height = img.getHeight();
        int width = img.getWidth();
        HashMap<Integer, Integer> R = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> G = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> B = new HashMap<Integer, Integer>();
        int temp, tempR, tempG, tempB;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                temp = img.getRGB(i, j);
                tempR = temp & 0xff0000;
                tempG = temp & 0xff00;
                tempB = temp & 0xff;
                if (R.containsKey(tempR)) {
                    R.put(tempR, R.get(tempR) + 1);
                } else {
                    R.put(tempR, 1);
                }

                if (G.containsKey(tempG)) {
                    G.put(tempG, G.get(tempG) + 1);
                } else {
                    G.put(tempG, 1);
                }

                if (B.containsKey(tempB)) {
                    B.put(tempB, B.get(tempB) + 1);
                } else {
                    B.put(tempB, 1);
                }

            }
        }
        double resultR = 0.0, resultG = 0.0, resultB = 0.0;
        double t;
        for (int i : R.keySet()) {
            t = (double) R.get(i) / (width * height);
            resultR -= t * Math.log(t);
        }
        resultR = resultR / Math.log(2);
        for (int i : G.keySet()) {
            t = (double) G.get(i) / (width * height);
            resultG -= t * Math.log(t);
        }
        resultG = resultG / Math.log(2);
        for (int i : B.keySet()) {
            t = (double) B.get(i) / (width * height);
            resultB -= t * Math.log(t);
        }
        resultB = resultB / Math.log(2);

        String result = "InfoEntropy   R: " + Double.toString(resultR) + "     " + "G: " + Double.toString(resultG)
                + "     " + "B: " + Double.toString(resultB);

        return result;

    }
}
