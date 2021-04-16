package src.Model;

import java.io.File;
import java.awt.image.*;

public class Model {
    private static File srcImage;
    private static BufferedImage desImage;

    static {
        srcImage = null;
        desImage = null;
    }

    public static File getSrcImage() {
        return srcImage;
    }

    public static BufferedImage getDesImage() {
        return desImage;
    }

    public static void setSrcImage(File file) {
        srcImage = file;
    }

    public static void setDesImage(BufferedImage file) {
        desImage = file;
    }

}
