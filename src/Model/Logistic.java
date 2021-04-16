package src.Model;

import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

class Logistic implements Scramble {
    public Logistic(){
    }

    @override
    public BufferedImage scrambling(File srcImageFile, int period) {
        
        double x=0.1,u=4;
        for(int i=1;i<=500;i++)
            x=x*u*(1-x);
        

        BufferedImage src_image = null,res_image=null;
        try {
            src_image=(BufferedImage)ImageIO.read(srcImageFile);
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        int width = src_image.getWidth();
        int height = src_image.getHeight();
        int [] pot = new int[width*height];
       
        int k=0;
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                pot[k] = src_image.getRGB(j, i);
                k++;
            }
        }
        res_image=src_image;

        for(int it=0;it<period;it++)
        {
            int tem=0;
            for(int i=0;i<height;i++)
            {
                for(int j=0;j<width;j++)
                {
                    double x1=x*255;
                    int y=(int)x1;

                    int[] rgb=new int[3];
                    rgb[0]=(pot[tem]&0x00ffffff)>>16;
                    rgb[1]=(pot[tem]&0x0000ffff)>>8;
                    rgb[2]=(pot[tem]&0x000000ff);
                    
                    for(int ii=0;ii<=2;ii++)
                        rgb[ii]=rgb[ii]^y;
                    int d=(rgb[0]<<16)|(rgb[1]<<8)|(rgb[2]);
                    int rod=d|(pot[tem]&0xff000000);
                    res_image.setRGB(j,i,rod);
                    
                    x=x*u*(1-x);
                    pot[tem]=rod;
                    tem++;
                    
                }
            }
        }

        return res_image;
    }

}