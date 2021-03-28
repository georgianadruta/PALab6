package compulsory.application.panels;

import compulsory.application.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Logger;

public class ControlPanel extends JPanel {
    private static final Logger logger = Logger.getLogger(String.valueOf(ControlPanel.class));

    final MainFrame frame;
    JFileChooser fileChooser;

    JButton saveBtn = new JButton("Save");
    JButton loadBtn = new JButton("Load");
    JButton resetBtn = new JButton("Reset");
    JButton exitBtn = new JButton("Exit");

    /**
     * constructor
     * @param frame the main frame of the application
     */
    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
        saveBtn.addActionListener(this::save);
        loadBtn.addActionListener(this::load);
        resetBtn.addActionListener(this::reset);
        exitBtn.addActionListener(this::exit);
        add(saveBtn);
        add(loadBtn);
        add(resetBtn);
        add(exitBtn);
    }

    /**
     *  add where to save file chooser
     */
    private void init() {
        setLayout(new GridLayout(2, 2));
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("PNG images:   *.png", "png"));
    }

    /**
     * helpful method for save button
     * @param e a semantic event which indicates that a component-defined action occurred
     */
    private void save(ActionEvent e) {
        try {
            fileChooser.setDialogTitle("Specify where to save and the name: ");
            int userSelection = fileChooser.showSaveDialog(frame);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                ImageIO.write(frame.drawingPanel.image, "PNG", fileChooser.getSelectedFile());
            }
        } catch (IOException exception) {
            logger.info("IOException");
        }
    }

    /**
     * helpful method for load button
     * @param e A semantic event which indicates that a component-defined action occurred
     */
    private void load(ActionEvent e) {
        try {
            fileChooser.setDialogTitle("Specify the file you want to load: ");
            int userSelection = fileChooser.showSaveDialog(frame);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                frame.drawingPanel.image = ImageIO.read(fileChooser.getSelectedFile());
                frame.drawingPanel.repaint();
            }
        } catch (IOException exception) {
            logger.info("IOException");
        }
    }

    /**
     * helpful method for reset button
     * @param e A semantic event which indicates that a component-defined action occurred
     */
    private void reset(ActionEvent e) {
        frame.drawingPanel.createOffscreenImage();
    }

    /**
     * helpful method for exit button
     * @param e A semantic event which indicates that a component-defined action occurred
     */
    private void exit(ActionEvent e) {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
}
