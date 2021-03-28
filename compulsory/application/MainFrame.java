package compulsory.application;

import compulsory.application.panels.ConfigPanel;
import compulsory.application.panels.ControlPanel;
import compulsory.application.panels.DrawingPanel;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {
    public static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int width = screenSize.width - 400;
    public static final int height = screenSize.height - 400;

    public ConfigPanel configPanel;
    ControlPanel controlPanel;
    public DrawingPanel drawingPanel;

    /**
     * constructor
     */
    public MainFrame() {
        super("Geometric Draw");
        init();
    }

    /**
     * creates the components and arranges the components in the frame
     */
    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setMinimumSize(new Dimension(850, 500));

        this.setSize(width, height);
        this.setResizable(false);

        int x = (screenSize.width - this.getWidth()) / 2;
        int y = (screenSize.height - this.getHeight()) / 2;
        this.setLocation(x, y);

        this.configPanel = new ConfigPanel(this);
        this.drawingPanel = new DrawingPanel(this);
        this.controlPanel = new ControlPanel(this);

        add(configPanel, BorderLayout.NORTH);
        add(drawingPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        pack();
    }
}