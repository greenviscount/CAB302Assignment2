package assignmentPackage.VecCommand;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class VecShapeCommandLine extends VecShapeCommand {

    public VecShapeCommandLine(ArrayList<Point2D> p) {
        super(p);
        this.type = VecCommandType.LINE;
    }

    @Override
    public void draw() {
        //TODO: implement drawing method
    }
}
