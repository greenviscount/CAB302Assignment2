package assignmentPackage.VecCommand;

import assignmentPackage.VecFile.VecFile;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class VecShapeCommandEllipse extends VecShapeCommand implements IVecCommandExecutable  {
    public VecShapeCommandEllipse(ArrayList<Point2D.Double> p) {
        this.points = p;
        this.type = VecCommandType.ELLIPSE;
    }

    @Override
    public void Execute(Graphics2D  g, VecFile f) {
        Point2D.Double p1 = f.GetActualPoint(points.get(0));
        Point2D.Double p2 = f.GetActualPoint(points.get(1));
        Ellipse2D.Double r = new Ellipse2D.Double(Math.min(p1.getX(),p2.getX()),Math.min(p1.getY(),p2.getY()), Math.abs(p2.getX() -p1.getX()), Math.abs(p1.getY()-p2.getY()));
        if (f.GetFill()) {
            g.setPaint(f.GetFillColor());
            g.fill(r);
        }
        g.setPaint(f.GetPenColor());
        g.draw(r);
    }
}
