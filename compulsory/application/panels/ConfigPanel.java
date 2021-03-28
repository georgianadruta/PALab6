package compulsory.application.panels;

import compulsory.application.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    public int size = 0;
    public int sides = 0;
    JComboBox<String> shapeColor;
    JLabel sidesLabel;
    JLabel typeLabel;
    JLabel colorLabel;
    JLabel sizeLabel;
    JSpinner shapeSize;
    JSpinner sidesNumber;
    JComboBox<String> shapeType;
    Color color;

    /**
     * constructor
     *
     * @param frame the main frame of the application
     */
    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
        addButtons();
        addComponents();
    }

    /**
     * creates and adds all the configuration panel's components
     * type, size, color, shape labels
     */
    private void init() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));
        this.setBorder(BorderFactory.createLineBorder(Color.black));

        this.sizeLabel = new JLabel("Size:  ");

        this.shapeSize = new JSpinner(new SpinnerNumberModel(20, 5, 100, 5));
        this.shapeSize.setValue(20);

        this.sidesLabel = new JLabel("Number of sides:  ");
        this.sidesNumber = new JSpinner(new SpinnerNumberModel(4, 3, 20, 1));
        this.sidesNumber.setValue(3);

        this.typeLabel = new JLabel("Type: ");
        String[] types = {"Polygon", "Circle"};
        this.shapeType = new JComboBox<>(types);
        this.shapeType.setSelectedIndex(0);

        this.colorLabel = new JLabel("Color of the shape: ");
        String[] colors = {"Random", "Black", "Red", "Blue", "Yellow", "Green"};
        this.shapeColor = new JComboBox<>(colors);
        this.shapeColor.setSelectedIndex(0);
    }

    /**
     * add buttons
     */
    private void addButtons() {
        JButton saveBtn = new JButton("Start Drawing");
        saveBtn.addActionListener(e -> {
            if (Pattern.matches("[1-9][0-9]?[0-9]?", sizeLabel.getText()))
                frame.configPanel.size = Integer.parseInt(sizeLabel.getText());
            else {
                frame.configPanel.size = 0;
            }

            frame.configPanel.sides = (int) sidesNumber.getValue();

            Object[] colors1 = shapeColor.getSelectedObjects();

            if (colors1[0].equals("Black")) {
                frame.configPanel.color = Color.black;
            } else {
                frame.configPanel.color = null;
            }
        });
    }

    /**
     * add size, sides, shape and color
     */
    private void addComponents() {
        add(sizeLabel);
        add(shapeSize);
        add(sidesLabel);
        add(sidesNumber);
        add(typeLabel);
        add(shapeType);
        add(colorLabel);
        add(shapeColor);
    }
}
