package assignmentPackage.VecCommand;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class VecShapeCommandRectangle extends VecShapeCommand implements IVecCommandExecutable {

    public VecShapeCommandRectangle(ArrayList<Point2D.Double> p) {
        this.points = p;
        this.type = VecCommandType.RECTANGLE;
    }

    @Override
    public void Execute() {
        //TODO: implement drawing method
    }
}
