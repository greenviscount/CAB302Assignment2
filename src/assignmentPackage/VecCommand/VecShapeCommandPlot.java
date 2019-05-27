package assignmentPackage.VecCommand;

import assignmentPackage.VecFile.VecFile;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class VecShapeCommandPlot extends VecShapeCommand implements IVecCommandExecutable {

    public VecShapeCommandPlot(ArrayList<Point2D.Double> p){
        this.points = p;
        this.type = VecCommandType.PLOT;
    }

    @Override
    public void Execute(Graphics2D  g, VecFile f) {
        g.setPaint(f.GetPenColor());
        g.draw(new Line2D.Double(points.get(0).getX(),points.get(0).getY(), points.get(0).getX(), points.get(0).getY()));
    }
}
