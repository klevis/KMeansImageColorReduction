package ramo.klevis.ml.kmeans;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Run {

    public static void main(String[] args) throws IOException {
        BufferedImage originalImage = ImageIO.read(new File("bird_small.png"));
        int[][] imageRGB = transformImageToTwoDimensionalMatrix(originalImage);
        reCreateOriginalImageFromMatrix(originalImage, imageRGB);
    }

    private static void reCreateOriginalImageFromMatrix(BufferedImage originalImage, int[][] imageRGB) throws IOException {
        BufferedImage writeBackImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        int index=0;
        for (int i = 0; i < originalImage.getWidth(); i++) {
            for (int j = 0; j < originalImage.getHeight(); j++) {
                Color color = new Color(imageRGB[index][0], imageRGB[index][1], imageRGB[index][2]);
                writeBackImage.setRGB(i, j, color.getRGB());
                index++;
            }
        }
        File outputfile = new File("writeBack.png");
        ImageIO.write(writeBackImage, "png", outputfile);
    }

    private static int[][] transformImageToTwoDimensionalMatrix(BufferedImage img) {
        int[][] imageRGB = new int[img.getWidth() * img.getHeight()][3];
        int w = img.getWidth();
        int h = img.getHeight();
        int index = 0;
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                Color color = new Color(img.getRGB(i, j), true);
                imageRGB[index][0] = color.getRed();
                imageRGB[index][1] = color.getGreen();
                imageRGB[index][2] = color.getBlue();
                index++;

            }
        }
        return imageRGB;
    }

}

