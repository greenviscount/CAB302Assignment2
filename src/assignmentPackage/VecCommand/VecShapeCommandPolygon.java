package assignmentPackage.VecCommand;

import assignmentPackage.VecFile.VecFile;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class VecShapeCommandPolygon extends VecShapeCommand implements IVecCommandExecutable {
    public VecShapeCommandPolygon(ArrayList<Point2D.Double> p) {
        this.points = p;
        this.type = VecCommandType.POLYGON;
    }


    @Override
    public void Execute(Graphics2D  g, VecFile f) {

    }
}
