package assignmentPackage.VecCommand;

import assignmentPackage.VecFile.VecFile;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
/**
 * VecCommand describing an line
 */
public class VecShapeCommandLine extends VecShapeCommand {

    /**
     * constructor
     * @param p arrayList of vertices
     */
    public VecShapeCommandLine(ArrayList<Point2D.Double> p) {
        this.points = p;
        this.type = VecCommandType.LINE;
    }

    /**
     * renders the shape
     * @param g the graphics object that will render the object
     * @param f the parent file containing the canvas
     */
    @Override
    public void Execute(Graphics2D  g, VecFile f) {
        g.setPaint(f.GetPenColor());
        Point2D.Double p1 = f.GetActualPoint(points.get(0));
        Point2D.Double p2 = f.GetActualPoint(points.get(1));

        g.draw(new Line2D.Double(p1.getX(),p1.getY(), p2.getX(), p2.getY()));
    }
}
