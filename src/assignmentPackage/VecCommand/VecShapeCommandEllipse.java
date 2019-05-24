package assignmentPackage.VecCommand;

import assignmentPackage.VecFile.VecFile;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class VecShapeCommandEllipse extends VecShapeCommand implements IVecCommandExecutable  {
    public VecShapeCommandEllipse(ArrayList<Point2D.Double> p) {
        this.points = p;
        this.type = VecCommandType.ELLIPSE;
    }

    @Override
    public void Execute(Graphics2D  g, VecFile f) {
        //TODO: implement drawing method
    }
}
