package assignmentPackage.VecCommand;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public abstract class VecShapeCommand extends VecCommand implements IVecCommandPrintable{

    private ArrayList<Point2D> points;

    public VecShapeCommand(ArrayList<Point2D> p) {

        this.points = p;
    }


    public String GetHistoryString(){
        return this.GetType().toString();
    }

    abstract public void draw();

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
