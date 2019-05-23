package assignmentPackage.VecCommand;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class VecShapeCommandPlot extends VecShapeCommand{

    public VecShapeCommandPlot(ArrayList<Point2D
            > p){
        super(p);
        this.type = VecCommandType.PLOT;
    }

    @Override
    public void draw() {
        //TODO: implement drawing method
    }
}
