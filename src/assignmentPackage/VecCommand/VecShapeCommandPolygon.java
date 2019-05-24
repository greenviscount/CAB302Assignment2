package assignmentPackage.VecCommand;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class VecShapeCommandPolygon extends VecShapeCommand implements IVecCommandExecutable {
    public VecShapeCommandPolygon(ArrayList<Point2D.Double> p) {
        this.points = p;
        this.type = VecCommandType.POLYGON;
    }

    @Override
    public void Execute() {
        //TODO: implement drawing method
    }
}
