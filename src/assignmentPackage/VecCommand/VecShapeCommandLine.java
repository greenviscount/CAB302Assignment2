package assignmentPackage.VecCommand;

import assignmentPackage.VecFile.VecFile;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class VecShapeCommandLine extends VecShapeCommand implements IVecCommandExecutable {

    public VecShapeCommandLine(ArrayList<Point2D.Double> p) {
        this.points = p;
        this.type = VecCommandType.LINE;
    }

    @Override
    public void Execute(Graphics2D  g, VecFile f) {
        g.setPaint(f.GetColor());
        g.draw(new Line2D.Double(points.get(0).getX(),points.get(0).getY(), points.get(1).getX(), points.get(1).getY()));
    }
}
