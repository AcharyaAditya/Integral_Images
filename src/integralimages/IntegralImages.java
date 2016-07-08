/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integralimages;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Aditya Acharya
 */
public class IntegralImages {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        /*
        REQUIRED PARAMETERS DECLARATION
        */
        BufferedImage img = null;
        img = ImageIO.read(new File("images//test.jpg"));
        int height = img.getHeight();
        int width = img.getWidth();

        int[][] values = new int[width][height];
        int[][] intValue = new int[width][height];
        Rbg2gray rbg2gray = new Rbg2gray();

        //int[][] values = {{5, 2, 5, 2}, {3, 6, 3, 6}, {5, 2, 5, 2}, {3, 6, 3, 6}};
        //int[][] intValue = new int[4][4];
        System.out.println("height = " + height + " Width = " + width);

        /*
        CONVERT TO GRAY AND GET THE PIXEL VALUES
        */
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = new Color(img.getRGB(i, j));
                Color newColor = rbg2gray.SettingColor(c);                      //Get new color values from Rbg2gray.java    
                img.setRGB(i, j, newColor.getRGB());
            }
        }

        int numBands = img.getRaster().getNumBands();
        int[] iArray = new int[numBands];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                values[i][j] = img.getRaster().getPixel(i, j, iArray)[0];
                System.out.print(values[i][j]);
                System.out.print(" ");

            }
            System.out.println();
        }
        System.out.println("INTEGRAL VALUES ");

        /**
         * Integral Values Calculation
        *
         */
        for (int i = 0; i < width; i++) {                                        //WIDTH
            int sum = 0;
            for (int j = 0; j < height; j++) {                                   //HEIGHT
                sum += values[i][j];                                            //Add the current location value
                if (i == 0) {
                    intValue[i][j] = sum;                                       //if the location is left corner then just the sum of values on top of it
                } else {
                    intValue[i][j] = intValue[i - 1][j] + sum;                  //else add itself, the sum accumulated and the pixel left of the current position.
                }
            }
        }

        /*
        PRINT OUT THE INTEGRAL VALUES
        */
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                System.out.print(intValue[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

}
