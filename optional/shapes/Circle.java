package optional.shapes;

import java.awt.geom.Ellipse2D;

/**
 * class for circle shape
 */
public class Circle extends Ellipse2D.Double {
    double radius;

    public Circle(double x0, double y0, double radius) {
        super(x0 - radius / 2, y0 - radius / 2, radius, radius);
        this.radius = radius;
    }

    public double getRadius() {
        return this.radius;
    }
}

