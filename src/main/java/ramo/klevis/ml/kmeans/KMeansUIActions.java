package ramo.klevis.ml.kmeans;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;


/**
 * Created by klevis.ramo on 9/18/2017.
 */
public class KMeansUIActions {

    private KMeansAlgorithm kMeansAlgorithm = new KMeansAlgorithm();
    private JavaSparkContext javaSparkContext;

    public void chooseFileAction(ImagePanel sourceImagePanel, JLabel sourceSizeLabel) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("target"));
        int action = chooser.showOpenDialog(null);
        if (action == JFileChooser.APPROVE_OPTION) {
            try {
                updateSizeLabel(sourceSizeLabel, chooser.getSelectedFile().length(), "File Size Before Color Reduction");
                showSelectedImageOnPanel(new FileInputStream(chooser.getSelectedFile()), sourceImagePanel);

            } catch (IOException e1) {
                throw new RuntimeException(e1);
            }
        }
    }

    public void transformAction(int colorToReduce, JLabel sizeLabelTransformed, BufferedImage sourceImage, ImagePanel transformedImagePanel) throws IOException {
        long startBegin = System.currentTimeMillis();

        int[][] imageToTwoDimensionalMatrix = kMeansAlgorithm.transformImageToTwoDimensionalMatrix(sourceImage);
        int[][] transformedImageMatrix = kMeansAlgorithm.runKMeans(getSparkContext(), imageToTwoDimensionalMatrix, colorToReduce);
        InputStream inputStream = kMeansAlgorithm.reCreateOriginalImageFromMatrix(sourceImage, transformedImageMatrix);
        updateSizeLabel(sizeLabelTransformed, new File("writeBack.png").length(), "File Size After Color Reduction");
        showSelectedImageOnPanel(inputStream, transformedImagePanel);

        System.out.println((System.currentTimeMillis()) - startBegin);
    }

    private JavaSparkContext getSparkContext() {
        if (javaSparkContext == null) {
            SparkConf conf = new SparkConf().setAppName("Finance Fraud Detection").setMaster("local[*]");
            javaSparkContext = new JavaSparkContext(conf);
        }
        return javaSparkContext;
    }


    private void showSelectedImageOnPanel(InputStream selectedFile, ImagePanel imagePanel) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(selectedFile);
        Image scaledInstance = scaleImage(bufferedImage);
        imagePanel.setBufferedImage(bufferedImage);
        imagePanel.setImage(scaledInstance);
    }

    private Image scaleImage(BufferedImage bufferedImage) {
        return bufferedImage.getScaledInstance(ImagePanel.DEFAULT_WIDTH, ImagePanel.DEFAULT_HEIGHT, Image.SCALE_DEFAULT);
    }

    public void updateSizeLabel(JLabel sizeLabel, long fileSize, String text) {
        sizeLabel.setText(text + " : " + BigDecimal.valueOf(fileSize / (1024d * 1024d)).setScale(2, RoundingMode.HALF_UP).doubleValue() + " MB ");
        sizeLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        sizeLabel.setForeground(Color.RED);

    }
}
