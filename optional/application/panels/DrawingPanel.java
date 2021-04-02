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
import java.util.Random;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final static int width = 800, height = 600;
    BufferedImage image;
    Graphics2D graphics;

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        init();
    }

    private void createOffscreenImage() {
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.graphics = image.createGraphics();
        this.graphics.setColor(Color.WHITE);
        this.graphics.fillRect(0, 0, width, height);
    }

    private void init() {
        setPreferredSize(new Dimension(width, height));
        setBorder(BorderFactory.createEtchedBorder());
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                drawOrEraser(e.getX(), e.getY());
                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (frame.getConfigPanel().erasing)
                    drawOrEraser(e.getX(), e.getY());
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (frame.getConfigPanel().erasing)
                    drawOrEraser(e.getX(), e.getY());
                repaint();
            }
        });
    }

    private void drawOrEraser(int x, int y) {
        int radius = (Integer) (frame.getConfigPanel().shapesStroke.getValue());
        if (!frame.getConfigPanel().erasing) {
            String figureType = (String) (frame.getConfigPanel().figureTypes.getSelectedItem());
            frame.getConfigPanel().sidesField.setVisible(true);
            frame.getConfigPanel().sidesLabel.setVisible(true);
            frame.getConfigPanel().colorCombo.enable();
            drawShape(figureType, x, y, radius);
        } else {
            graphics.setColor(Color.white);
            graphics.fill(new Eraser(x, y, radius));
        }
    }

    private void drawShape(String figureType, int x, int y, int radius) {
        switch (figureType) {
            case "Regular polygon": {
                int sides = (Integer) (frame.getConfigPanel().sidesField.getValue());
                setColor();
                graphics.fill(new RegularPolygon(x, y, radius, sides));
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
                break;
            }
        }
    }

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
