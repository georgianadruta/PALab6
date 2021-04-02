package optional.application.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * configuration panel
 */
public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel sidesLabel;
    JLabel strokeLabel;
    JLabel colorLabel;
    JLabel typeLabel;
    JSpinner shapesStroke;
    JSpinner sidesField;

    JComboBox colorCombo;
    JComboBox figureTypes;

    JButton eraserButton;
    boolean erasing = false;

    /**
     * constructor
     *
     * @param frame main frame
     */
    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    /**
     * initialization method
     * figure type, number of sides, shape color, stroke size, eraser on-off button
     */
    private void init() {
        typeLabel = new JLabel("Figure type:");
        String[] types = {"Regular polygon", "Flower", "Cloud", "Circle", "Line"};
        figureTypes = new JComboBox(types);

        sidesLabel = new JLabel("Number of sides:");
        sidesField = new JSpinner(new SpinnerNumberModel(3, 3, 100, 1));
        sidesField.setValue(3);

        String[] colors = {"Random", "Black", "Red", "Blue", "Yellow", "Green"};
        colorLabel = new JLabel("Choose shape color:");
        colorCombo = new JComboBox(colors);

        strokeLabel = new JLabel("Stroke size:");
        shapesStroke = new JSpinner(new SpinnerNumberModel(0, 0, 200, 1));
        shapesStroke.setValue(30);

        createEraserButton();
        addButtons();
    }

    private void createEraserButton() {
        eraserButton = new JButton("Eraser: off");
        eraserButton.setBackground(new Color(127, 200, 167));

        eraserButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (erasing) {
                    eraserButton.setText("Eraser: off");
                    erasing = false;
                } else {
                    eraserButton.setText("Eraser: on");
                    erasing = true;
                }
            }
        });
    }

    private void addButtons() {
        add(typeLabel);
        add(figureTypes);
        add(sidesLabel);
        add(sidesField);
        add(colorLabel);
        add(colorCombo);
        add(strokeLabel);
        add(shapesStroke);
        add(eraserButton);
    }
}
