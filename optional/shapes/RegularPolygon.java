package optional.shapes;

import java.awt.*;

/**
 * class for polygon shape
 */
public class RegularPolygon extends Polygon {
    int xC, yC, radius;

    public RegularPolygon(int x0, int y0, int radius, int sides) {
        double alpha = 2 * Math.PI / sides;
        for (int i = 0; i < sides; i++) {
            double x = x0 + radius * Math.cos(alpha * i);
            double y = y0 + radius * Math.sin(alpha * i);
            this.addPoint((int) x, (int) y);
            this.xC += x;
            this.yC += y;
        }
        this.radius = radius;
        this.xC /= sides;
        this.yC /= sides;
    }

    public int getRadius() {
        return radius;
    }

    public int getXC() {
        return xC;
    }

    public int getYC() {
        return yC;
    }
}