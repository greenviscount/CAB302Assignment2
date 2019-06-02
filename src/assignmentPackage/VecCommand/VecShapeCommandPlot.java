package assignmentPackage.VecCommand;

import assignmentPackage.VecFile.VecFile;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class VecShapeCommandPlot extends VecShapeCommand {

    public VecShapeCommandPlot(ArrayList<Point2D.Double> p){
        this.points = p;
        this.type = VecCommandType.PLOT;
    }

    @Override
    public void Execute(Graphics2D  g, VecFile f) {
        g.setPaint(f.GetPenColor());
        Point2D.Double p = f.GetActualPoint(points.get(0));
        g.draw(new Line2D.Double(p.getX(),p.getY(), p.getX(), p.getY()));
    }
}
