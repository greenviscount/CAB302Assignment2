package assignmentPackage.VecCommand;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class VecShapeCommandEllipse extends VecShapeCommand implements IVecCommandExecutable  {
    public VecShapeCommandEllipse(ArrayList<Point2D.Double> p) {
        this.points = p;
        this.type = VecCommandType.ELLIPSE;
    }

    @Override
    public void Execute() {
        //TODO: implement drawing method
    }
}
