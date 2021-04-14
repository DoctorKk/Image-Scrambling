package src.Model;

import java.io.File;

public class Model {
    private static File srcImage;
    private static File desImage;

    static {
        srcImage = null;
        desImage = null;
    }

    public static File getSrcImage() {
        return srcImage;
    }

    public static File getDesImage() {
        return desImage;
    }

    public static void setSrcImage(File file) {
        srcImage = file;
    }

    public static void setDesImage(File file) {
        desImage = file;
    }

}
