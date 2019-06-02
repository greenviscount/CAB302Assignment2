package assignmentPackage.VecCommand;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public abstract class VecShapeCommand extends VecCommand{

    protected ArrayList<Point2D.Double> points;

    @Override
    public String PrintToFile(){
        StringBuilder str = new StringBuilder();
        str.append(GetType().toString());
        str.append(" ");
        for (Point2D point: points) {
            str.append(point.getX());
            str.append(" ");
            str.append(point.getY());
            str.append(" ");
        }
        str.append("\n");

        return str.toString();
    }
}
