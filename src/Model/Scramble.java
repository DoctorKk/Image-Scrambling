package src.Model;

import java.io.*;
import java.awt.image.*;

public interface Scramble {
    public BufferedImage scrambling(File srcImageFile, int period);
}
