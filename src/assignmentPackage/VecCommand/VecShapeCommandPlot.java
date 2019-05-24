package assignmentPackage.VecCommand;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class VecShapeCommandPlot extends VecShapeCommand implements IVecCommandExecutable {

    public VecShapeCommandPlot(ArrayList<Point2D.Double> p){
        this.points = p;
        this.type = VecCommandType.PLOT;
    }

    @Override
    public void Execute() {
        //TODO: implement drawing method
    }
}
