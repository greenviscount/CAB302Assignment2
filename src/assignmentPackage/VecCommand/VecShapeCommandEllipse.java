package assignmentPackage.VecCommand;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class VecShapeCommandEllipse extends VecShapeCommand {
    public VecShapeCommandEllipse(ArrayList<Point2D> p) {
        super(p);
        this.type = VecCommandType.ELLIPSE;
    }

    @Override
    public void draw() {
        //TODO: implement drawing method
    }
}
