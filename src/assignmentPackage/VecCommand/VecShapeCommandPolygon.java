package assignmentPackage.VecCommand;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class VecShapeCommandPolygon extends VecShapeCommand {
    public VecShapeCommandPolygon(ArrayList<Point2D> p) {
        super(p);
        this.type = VecCommandType.POLYGON;
    }

    @Override
    public void draw() {
        //TODO: implement drawing method
    }
}
