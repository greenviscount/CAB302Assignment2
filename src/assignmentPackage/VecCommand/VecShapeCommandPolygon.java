package assignmentPackage.VecCommand;

import assignmentPackage.VecFile.VecFile;

import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class VecShapeCommandPolygon extends VecShapeCommand implements IVecCommandExecutable {
    public VecShapeCommandPolygon(ArrayList<Point2D.Double> p) {
        this.points = p;
        this.type = VecCommandType.POLYGON;
    }


    @Override
    public void Execute(Graphics2D  g, VecFile f) {
        Path2D.Double r = new Path2D.Double();
        r.moveTo(points.get(0).getX(), points.get(0).getY());
        for (Point2D.Double point:this.points) {
            r.lineTo(point.getX(),point.getY());
        }
        if (f.GetFill()) {
            g.setPaint(f.GetFillColor());
            g.fill(r);
        }
        r.closePath();
        g.setPaint(f.GetPenColor());
        g.draw(r);
    }
}
