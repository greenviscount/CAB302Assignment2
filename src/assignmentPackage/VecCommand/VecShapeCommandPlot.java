package assignmentPackage.VecCommand;

import assignmentPackage.VecFile.VecFile;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
/**
 * VecCommand describing an point
 */
public class VecShapeCommandPlot extends VecShapeCommand {

    /**
     * constructor
     * @param p arrayList of vertices
     */
    public VecShapeCommandPlot(ArrayList<Point2D.Double> p){
        this.points = p;
        this.type = VecCommandType.PLOT;
    }

    /**
     * renders the shape
     * @param g the graphics object that will render the object
     * @param f the parent file containing the canvas
     */
    @Override
    public void Execute(Graphics2D  g, VecFile f) {
        g.setPaint(f.GetPenColor());
        Point2D.Double p = f.GetActualPoint(points.get(0));
        g.draw(new Line2D.Double(p.getX(),p.getY(), p.getX(), p.getY()));
    }
}
