package optional.application.panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * control panel
 */
public class ControlPanel extends JPanel {
    private static final java.util.logging.Logger logger = Logger.getLogger(String.valueOf(ControlPanel.class));

    final MainFrame frame;
    JButton saveBtn = new JButton("Save");
    JButton loadBtn = new JButton("Load");
    JButton resetBtn = new JButton("Reset");
    JButton exitBtn = new JButton("Exit");

    /**
     * constructor
     * @param frame main frame
     */
    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    /**
     * initialization method
     * save button, load button, reset button, exit button
     */
    private void init() {
        setLayout(new GridLayout(1, 4));
        add(saveBtn);
        add(loadBtn);
        add(resetBtn);
        add(exitBtn);
        saveBtn.addActionListener(this::save);
        loadBtn.addActionListener(this::load);
        resetBtn.addActionListener(this::reset);
        exitBtn.addActionListener(this::exit);
    }

    /**
     * helpful method for save button
     * @param e event
     */
    private void save(ActionEvent e) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose a directory");
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.showOpenDialog(this);

        String fileName = JOptionPane.showInputDialog("Enter the name of the file:");
        File location = new File(fileChooser.getSelectedFile().getAbsolutePath() + "\\" + fileName + ".png");
        try {
            ImageIO.write(frame.canvas.image, "PNG", location);
        } catch (IOException exception) {
            logger.info("IOException in save method");
        }
    }

    /**
     * helpful method for load button
     * @param e event
     */
    private void load(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose a png file:");
        fileChooser.setCurrentDirectory(new File("."));
        //only files
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.showOpenDialog(this);
        try {
            frame.canvas.setImage(ImageIO.read(new File(fileChooser.getSelectedFile().getAbsolutePath())));
            frame.canvas.repaint();
        } catch (IOException exception) {
            logger.info("IOException in load method");
        }
    }

    /**
     * helpful method for reset button
     * @param e event
     */
    private void reset(ActionEvent e) {
        frame.canvas.clear();
    }

    /**
     * helpful method for exit button
     * @param e event
     */
    private void exit(ActionEvent e) {
        System.exit(0);
    }

}