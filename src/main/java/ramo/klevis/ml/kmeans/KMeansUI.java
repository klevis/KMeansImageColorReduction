package ramo.klevis.ml.kmeans;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;

/**
 * Created by klevis.ramo on 9/18/2017.
 */
public class KMeansUI {

    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 600;

    private JPanel mainPanel;
    private JProgressBar progressBar;
    private JSlider colorReductionSlider;
    private ImagePanel sourceImagePanel;
    private ImagePanel transformedImagePanel;
    private JButton transformButton;
    private JButton chooseButton;

    public KMeansUI() throws IOException {
        initUI();
        addListeners();
    }

    private void addListeners() {
    }

    private void initUI() throws IOException {
        JFrame mainFrame = createMainFrame();

        addSignature(mainFrame);

        addMainPanel(mainFrame);

        progressBar = createProgressBar(mainFrame);

        mainFrame.setVisible(true);
    }

    private JProgressBar createProgressBar(JFrame mainFrame) {
        JProgressBar jProgressBar = new JProgressBar(JProgressBar.HORIZONTAL);
        jProgressBar.setString("Please wait it may take one or two minutes");
        jProgressBar.setStringPainted(true);
        jProgressBar.setIndeterminate(true);
        mainFrame.add(jProgressBar, BorderLayout.NORTH);
        return jProgressBar;
    }

    private JFrame createMainFrame() {
        JFrame mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mainFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        mainFrame.setLocationRelativeTo(null);
        return mainFrame;
    }

    private void addMainPanel(JFrame mainFrame) throws IOException {
        mainPanel = new JPanel(new GridBagLayout());

        addSourceImagePanel();

        addTransformedImagePanel();

        addTransformedButton();

        addChooseFileButton();

        addColorReductionSlider();

        mainFrame.add(mainPanel, BorderLayout.CENTER);

    }

    private void addChooseFileButton() {
        chooseButton = new JButton("Choose File");
        addComponentToMainPanel(chooseButton,1,2,0,0);
    }

    private void addColorReductionSlider() {
        colorReductionSlider = createColorReductionSlider();
        addComponentToMainPanel(colorReductionSlider, 1, 1, 0, 0);
    }

    private void addTransformedButton() {
        transformButton = new JButton("Transform");
        addComponentToMainPanel(transformButton, 1, 0, 0, 0);
    }

    private void addTransformedImagePanel() throws IOException {
        transformedImagePanel = new ImagePanel(false);
        addComponentToMainPanel(transformedImagePanel, 2, 1, 1, 1);
    }

    private void addSourceImagePanel() throws IOException {
        sourceImagePanel = new ImagePanel(true);
        addComponentToMainPanel(sourceImagePanel, 0, 1, 1, 1);
    }

    private JSlider createColorReductionSlider() {
        final JSlider jslider = new JSlider(SwingConstants.VERTICAL, 4, 32, 8);
        jslider.setMajorTickSpacing(4);
        jslider.setMinorTickSpacing(1);
        jslider.setPaintTicks(true);
        jslider.setPaintLabels(true);
        jslider.setToolTipText("Reduce Number of Colors");
        return jslider;
    }


    private void addComponentToMainPanel(JComponent jComponent, int x, int y, double wX, double wY) {

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = x;
        c.gridy = y;
        c.fill = GridBagConstraints.CENTER;
        c.weightx = wX;
        c.weighty = wY;
        mainPanel.add(jComponent, c);
        jComponent.setBorder(new LineBorder(Color.black));
    }

    private void addSignature(JFrame mainFrame) {
        JLabel signature = new JLabel("ramok.tech", JLabel.HORIZONTAL);
        signature.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 16));
        mainFrame.add(signature, BorderLayout.SOUTH);
    }
}
