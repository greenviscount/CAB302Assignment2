package assignmentPackage.VecCommand;

import assignmentPackage.VecFile.VecFile;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class VecShapeCommandRectangle extends VecShapeCommand implements IVecCommandExecutable {

    public VecShapeCommandRectangle(ArrayList<Point2D.Double> p) {
        this.points = p;
        this.type = VecCommandType.RECTANGLE;
    }

    @Override
    public void Execute(Graphics2D  g, VecFile f) {
        Rectangle2D r = new Rectangle2D.Double(Math.min(points.get(0).getX(),points.get(1).getX()),Math.min(points.get(0).getY(),points.get(1).getY()), Math.abs(points.get(1).getX() -points.get(0).getX()), Math.abs(points.get(0).getY()-points.get(1).getY()));
        if (f.GetFill()) {
            g.setPaint(f.GetFillColor());
            g.fill(r);
        }
        g.setPaint(f.GetPenColor());
        g.draw(r);
    }
}
