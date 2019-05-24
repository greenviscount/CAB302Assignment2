package assignmentPackage.VecCommand;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class VecShapeCommandLine extends VecShapeCommand implements IVecCommandExecutable {

    public VecShapeCommandLine(ArrayList<Point2D.Double> p) {
        this.points = p;
        this.type = VecCommandType.LINE;
    }

    @Override
    public void Execute() {
        //TODO: implement drawing method
    }
}
