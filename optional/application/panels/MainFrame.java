package optional.application.panels;

import javax.swing.*;
import java.awt.*;

/**
 * class for main frame
 */
public class MainFrame extends JFrame {
    public ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;

    /**
     * constructor
     */
    public MainFrame() {
        super("My Drawing Application");
        init();
    }

    /**
     * initialization method
     */
    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        canvas = new DrawingPanel(this);
        configPanel = new ConfigPanel(this);
        controlPanel = new ControlPanel(this);

        add(canvas, BorderLayout.CENTER);
        add(configPanel, BorderLayout.NORTH);
        add(controlPanel, BorderLayout.SOUTH);
        pack();
    }

    public ConfigPanel getConfigPanel() {
        return configPanel;
    }
}
