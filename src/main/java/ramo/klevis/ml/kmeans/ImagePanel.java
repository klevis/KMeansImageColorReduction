package ramo.klevis.ml.kmeans;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


public class ImagePanel extends JPanel {

    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 400;
    private Image img;
    private BufferedImage originalImage;


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

    public ImagePanel(boolean source) throws IOException {
        String showDefaultImage;
        if (!source) {
            showDefaultImage = "/placeholder.gif";
        } else {
            showDefaultImage = "/autumn-forest.jpg";
        }
        setImage(getClass().getResourceAsStream(showDefaultImage));
    }

    public BufferedImage getBufferedImage() {
        return originalImage;
    }

    public void setImage(InputStream imageStream) throws IOException {
        originalImage = ImageIO.read(imageStream);
        Image scaledInstance = originalImage.getScaledInstance(DEFAULT_WIDTH, DEFAULT_HEIGHT, Image.SCALE_DEFAULT);
        setImg(scaledInstance);
    }

    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

}