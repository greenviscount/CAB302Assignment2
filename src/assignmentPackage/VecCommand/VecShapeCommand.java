package assignmentPackage.VecCommand;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * general implementation of a VecShapeCommand
 */
public abstract class VecShapeCommand extends VecCommand{

    /**
     * arrayList ov vertices
     */
    protected ArrayList<Point2D.Double> points;

    /**
     * @return the string representation of the VecCommand
     */
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
