package ramo.klevis.ml.kmeans;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class ImagePanel extends JPanel {

    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 400;
    private Image img;
    private Path path;


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
        String first = "placeholder.gif";
        setImage(first);
    }

    public Path getPath() {
        return path;
    }

    public void setImage(String first) throws IOException {
        path = Paths.get(first);
        BufferedImage originalImage = ImageIO.read(path.toFile());
        Image scaledInstance = originalImage.getScaledInstance(DEFAULT_WIDTH, DEFAULT_HEIGHT, Image.SCALE_DEFAULT);
        setImg(scaledInstance);
    }

    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

}