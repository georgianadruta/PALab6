package compulsory.application.panels;

import compulsory.application.MainFrame;
import compulsory.shapes.Circle;
import optional.shapes.RegularPolygon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;


public class DrawingPanel extends JPanel {
    private final MainFrame frame;
    private final static int width=800;
    private final static int height=600;
    public BufferedImage image;
    private Graphics2D graphics;

    /**
     * constructor
     *
     * @param frame the main frame of the application
     */
    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        init();
    }

    /**
     * fill the image with white
     */
    public void createOffscreenImage() {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);
    }

    /**
     *
     */
    private void init() {
        setPreferredSize(new Dimension(width, height));
        setBorder(BorderFactory.createEtchedBorder());
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                drawShape(e.getX(), e.getY());
                repaint();
            }
        });
    }

    /**
     * draw a geometric figure depending on the color and size chosen by the user
     *
     * @param x
     * @param y
     */
    private void drawShape(int x, int y) {
        int radius = (Integer) frame.configPanel.shapeSize.getValue();
        setColor();
        String type = (String) (frame.configPanel.shapeType.getSelectedItem());
        if (type.equals("Polygon")) {
            frame.configPanel.sidesNumber.setVisible(true);
            frame.configPanel.sidesLabel.setVisible(true);
            frame.configPanel.colorLabel.enable();
            int sides = (Integer) (frame.configPanel.sidesNumber.getValue()); //get the value from UI (in Drawing.ConfigPanel)
            graphics.fill(new RegularPolygon(x, y, radius, sides));
        } else {
            graphics.fill(new Circle(x, y, radius));
        }
    }

    private void setColor(){
        Color color;
        String col = (String) (frame.configPanel.shapeColor.getSelectedItem());
        switch (col) {
            case "Black":
                color = new Color(0, 0, 0);
                break;
            case "Red":
                color = new Color(243, 0, 0);
                break;
            case "Blue":
                color = new Color(0, 29, 220);
                break;
            case "Yellow":
                color = new Color(255, 221, 0);
                break;
            case "Green":
                color = new Color(8, 243, 0);
                break;
            default:
                Random rand = new Random();
                int R = rand.nextInt(255);
                int G = rand.nextInt(255);
                int B = rand.nextInt(255);
                color = new Color(R, G, B);
        }
        graphics.setColor(color);
    }
    @Override
    public void update(Graphics g) {
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
        repaint();
    }
}
