package assignmentPackage.VecCommand;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class VecShapeCommandRectangle extends VecShapeCommand {
    public VecShapeCommandRectangle(ArrayList<Point2D> p) {
        super(p);
        this.type = VecCommandType.RECTANGLE;
    }

    @Override
    public void draw() {
        //TODO: implement drawing method
    }
}
