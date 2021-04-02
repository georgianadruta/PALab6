package optional.application.panels;

import optional.application.Eraser;
import optional.shapes.Circle;
import optional.shapes.Cloud;
import optional.shapes.Flower;
import optional.shapes.RegularPolygon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Logger;

/**
 * a very helpful class to draw circles, polygons, clouds, flowers and lines
 */
public class DrawingPanel extends JPanel {
    private static final Logger logger = Logger.getLogger(String.valueOf(compulsory.application.panels.DrawingPanel.class));

    int xS, yS, xF, yF;
    final MainFrame frame;
    final static int width = 800, height = 600;
    BufferedImage image;
    Graphics2D graphics;
    LinkedList<Circle> listCircle = new LinkedList<>();
    LinkedList<RegularPolygon> listPolygon = new LinkedList<>();

    /**
     * constructor
     *
     * @param frame main frame
     */
    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        init();
    }

    /**
     * fill the background with white
     */
    private void createOffscreenImage() {
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.graphics = image.createGraphics();
        this.graphics.setColor(Color.WHITE);
        this.graphics.fillRect(0, 0, width, height);
    }

    /**
     * initialization method
     */
    private void init() {
        setPreferredSize(new Dimension(width, height));
        setBorder(BorderFactory.createEtchedBorder());
        executeEvent();
    }

    /**
     * right click recognizes geometric shapes like circle and polygon
     * left click draw the chosen shape
     */
    private void executeEvent() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xS = e.getPoint().x;
                yS = e.getPoint().y;
                if (e.getButton() == MouseEvent.BUTTON1) {
                    drawOrEraser(e.getX(), e.getY());
                    repaint();
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    checkCircleOrPolygon(xS, yS);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                xF = e.getPoint().x;
                yF = e.getPoint().y;
                repaint();
            }
        });
    }

    /**
     * checks if the chosen shape is a polygon or a circle
     * @param x coordinate
     * @param y coordinate
     */
    private void checkCircleOrPolygon(int x, int y) {
        for (Circle circle : listCircle) {
            if (Math.abs(circle.getCenterX() - x) <= circle.getRadius() && Math.abs(circle.getCenterY() - y) <= circle.getRadius()) {
                logger.info("It's a circle");
                return;
            }
        }
        for (RegularPolygon polygon : listPolygon) {
            if (Math.abs(polygon.getXC() - x) < polygon.getRadius() && Math.abs(polygon.getYC() - y) < polygon.getRadius()) {
                logger.info("It's a polygon");
                return;
            }
        }
    }

    /**
     * draw the shape or erase
     * @param x coordinate
     * @param y coordinate
     */
    private void drawOrEraser(int x, int y) {
        int radius = (Integer) (frame.getConfigPanel().shapesStroke.getValue());
        if (!frame.getConfigPanel().erasing) {
            String figureType = (String) (frame.getConfigPanel().figureTypes.getSelectedItem());
            frame.getConfigPanel().sidesField.setVisible(true);
            frame.getConfigPanel().sidesLabel.setVisible(true);
            frame.getConfigPanel().colorCombo.enable();
            assert figureType != null;
            drawShape(figureType, x, y, radius);
        } else {
            graphics.setColor(Color.white);
            graphics.fill(new Eraser(x, y, radius));
        }
    }

    /**
     * draw the chosen shape: line, polygon, circle, flower, cloud
     * @param figureType shape type
     * @param x coordinate
     * @param y coordinate
     * @param radius calculated radius
     */
    private void drawShape(String figureType, int x, int y, int radius) {
        switch (figureType) {
            case "Regular polygon": {
                int sides = (Integer) (frame.getConfigPanel().sidesField.getValue());
                setColor();
                graphics.fill(new RegularPolygon(x, y, radius, sides));
                listPolygon.add(new RegularPolygon(x, y, radius, sides));
                break;
            }
            case "Flower": {
                new Flower(x, y, radius, graphics);
                break;
            }
            case "Cloud": {
                new Cloud(x, y, radius, graphics);
                break;
            }
            case "Circle": {
                setColor();
                graphics.fill(new Circle(x, y, radius));
                listCircle.add(new Circle(x, y, radius));
                break;
            }
            case "Line": {
                setColor();
                graphics.drawLine(this.xS, this.yS, this.xF, this.yF);
                break;
            }
        }
    }

    /**
     * set the chosen color: random, black, red, yellow, blue, green
     */
    private void setColor() {
        Color color;
        String col = String.valueOf(frame.configPanel.colorCombo.getSelectedItem());
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
                color = new Color(rand.nextInt(0xFFFFFF));
        }
        graphics.setColor(color);
    }

    public void clear() {
        graphics.setPaint(Color.white);
        graphics.fillRect(0, 0, getSize().width, getSize().height);
        graphics.setPaint(Color.black);
        repaint();
    }

    @Override
    public void update(Graphics g) {
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        graphics = image.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }
}
