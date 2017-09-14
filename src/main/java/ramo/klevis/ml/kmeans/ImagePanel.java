package ramo.klevis.ml.kmeans;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class ImagePanel extends JPanel {

    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 300;
    private Image img;


    public void setImg(Image img) {
        this.img = img;
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
        repaint();
        updateUI();
    }

    public ImagePanel() throws IOException {
        BufferedImage originalImage = ImageIO.read(Paths.get("placeholder.gif").toFile());
        Image scaledInstance = originalImage.getScaledInstance(DEFAULT_WIDTH, DEFAULT_HEIGHT, Image.SCALE_DEFAULT);
        setImg(scaledInstance);
    }

    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

}