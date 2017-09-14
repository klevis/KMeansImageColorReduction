package ramo.klevis.ml.kmeans;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Run {

    public static void main(String[] args) throws IOException {
        // JFileChooser-Objekt erstellen
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jFrame.setSize(800, 500);
        jFrame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridBagLayout());

        ImagePanel sourceImagePanel = new ImagePanel();
        addSourceImagePanel(mainPanel, sourceImagePanel);

        JButton jButton = new JButton("Choose File");
        addChooseButton(mainPanel, jButton);

        addTransformButton(mainPanel, new JButton("Transform"));

        JSlider jslider = new JSlider(SwingConstants.VERTICAL, 16, 128, 32);
        jslider.setMajorTickSpacing(16);
        jslider.setMinorTickSpacing(4);
        jslider.setPaintTicks(true);
        jslider.setPaintLabels(true);
        jslider.setToolTipText("Reduce Number of Colors");
        addSlider(mainPanel, jslider);

        ImagePanel transformedImagedLabel = new ImagePanel();
        addTransformedImagePanel(mainPanel, transformedImagedLabel);

        jButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new File("target"));
                int i = chooser.showOpenDialog(null);
                if (i == JFileChooser.APPROVE_OPTION) {


                    try {
                        BufferedImage originalImage = ImageIO.read(chooser.getSelectedFile());
                        Image scaledInstance = originalImage.getScaledInstance(ImagePanel.DEFAULT_WIDTH, ImagePanel.DEFAULT_HEIGHT, Image.SCALE_DEFAULT);
                        int[][] imageRGB = transformImageToTwoDimensionalMatrix(originalImage);
                        sourceImagePanel.setImg(scaledInstance);
                        transformedImagedLabel.setImg(scaledInstance);
                        mainPanel.updateUI();
                    } catch (IOException e1) {
                        throw new RuntimeException(e1);
                    }
                }
            }
        });

        jFrame.add(mainPanel);
        jFrame.setVisible(true);


    }

    private static void addTransformedImagePanel(JPanel mainPanel, JLabel transformedImagedLabel) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(transformedImagedLabel, BorderLayout.CENTER);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        mainPanel.add(panel, c);
    }

    private static void addSourceImagePanel(JPanel mainPanel, JPanel imagePanel) {

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.CENTER;
        c.weightx = 1;
        c.weighty = 1;
        mainPanel.add(imagePanel, c);
        imagePanel.setBorder(new LineBorder(Color.black));
    }

    private static void addTransformedImagePanel(JPanel mainPanel, JPanel imagePanel) {

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 1;
        c.fill = GridBagConstraints.CENTER;
        c.weightx = 1;
        c.weighty = 1;
        mainPanel.add(imagePanel, c);
        imagePanel.setBorder(new LineBorder(Color.black));
    }

    private static void addChooseButton(JPanel mainPanel, JButton jButton) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 2;
        c.fill = GridBagConstraints.CENTER;
        c.weightx = 0;
        c.weighty = 0;
        mainPanel.add(jButton, c);
    }

    private static void addTransformButton(JPanel mainPanel, JButton jButton) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.fill = GridBagConstraints.CENTER;
        c.weightx = 0;
        c.weighty = 0;
        mainPanel.add(jButton, c);
    }

    private static void addSlider(JPanel mainPanel, JSlider jslider) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.CENTER;
        c.weightx = 0;
        c.weighty = 0;
        mainPanel.add(jslider, c);
    }

    private static void reCreateOriginalImageFromMatrix(BufferedImage originalImage, int[][] imageRGB) throws IOException {
        BufferedImage writeBackImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        int index = 0;
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

