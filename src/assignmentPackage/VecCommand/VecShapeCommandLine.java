package assignmentPackage.VecCommand;

import assignmentPackage.VecFile.VecFile;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class VecShapeCommandLine extends VecShapeCommand {

    public VecShapeCommandLine(ArrayList<Point2D.Double> p) {
        this.points = p;
        this.type = VecCommandType.LINE;
    }

    @Override
    public void Execute(Graphics2D  g, VecFile f) {
        g.setPaint(f.GetPenColor());
        Point2D.Double p1 = f.GetActualPoint(points.get(0));
        Point2D.Double p2 = f.GetActualPoint(points.get(1));

        g.draw(new Line2D.Double(p1.getX(),p1.getY(), p2.getX(), p2.getY()));
    }
}
