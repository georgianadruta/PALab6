package optional.shapes;

import java.awt.*;

/**
 * class for flower shape
 */
public class Flower extends Polygon {

    public Flower(int x0, int y0, int radius, Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setStroke(new BasicStroke(50, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        g2.setColor(new Color(255, 123, 0));
        g2.fillOval(x0-radius/4, y0-radius/4, radius*3/2, radius*3/2);

        g2.setColor(Color.yellow);
        g2.fillOval(x0-radius, y0, radius, radius);

        g2.setColor(new Color(252,194,1));
        g2.fillOval(x0-radius+radius/3, y0-radius+radius/3, radius, radius);

        g2.setColor(Color.yellow);
        g2.fillOval(x0, y0-radius, radius, radius);

        g2.setColor(new Color(252,194,1));
        g2.fillOval(x0+radius-radius/3, y0-radius+radius/3, radius, radius);

        g2.setColor(Color.yellow);
        g2.fillOval(x0+radius, y0, radius, radius);

        g2.setColor(new Color(252,194,1));
        g2.fillOval(x0+radius-radius/3, y0+radius-radius/3, radius, radius);

        g2.setColor(Color.yellow);
        g2.fillOval(x0, y0+radius, radius, radius);

        g2.setColor(new Color(252,194,1));
        g2.fillOval(x0-radius+radius/3, y0+radius-radius/3, radius, radius);
    }
}
