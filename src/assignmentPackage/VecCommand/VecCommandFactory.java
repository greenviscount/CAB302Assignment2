package assignmentPackage.VecCommand;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Factory for creating VecCommands
 */
public abstract class VecCommandFactory {

    /**
     * used to parse the lines of a .vec file into Vec commands
     *
     * @param s the string representation of a VecCommand
     * @return VecCommand object
     */
    public static VecCommand ParseCommandString(String s){
        String[] tokens = s.split("\\s");
        ArrayList<Point2D.Double> points = new ArrayList<Point2D.Double>();

        switch(tokens[0]){
            case "FILL":
                if(tokens[1].equals("OFF")){
                    return new VecColorCommandFillOff();
                }else{
                    return new VecColorCommandFill(Color.decode(tokens[1]));
                }
            case "PEN": return new VecColorCommandPen(Color.decode(tokens[1]));
            case "PLOT":
                for(int i = 1; i<tokens.length; i++){
                    points.add(new Point2D.Double(Double.parseDouble(tokens[i]), Double.parseDouble(tokens[++i])));
                }
                return new VecShapeCommandPlot(points);
            case "LINE":
                for(int i = 1; i<tokens.length; i++){
                    points.add(new Point2D.Double(Double.parseDouble(tokens[i]), Double.parseDouble(tokens[++i])));
                }
                return new VecShapeCommandLine(points);
            case "RECTANGLE":
                for(int i = 1; i<tokens.length; i++){
                    points.add(new Point2D.Double(Double.parseDouble(tokens[i]), Double.parseDouble(tokens[++i])));
                }
                return new VecShapeCommandRectangle(points);
            case "ELLIPSE":
                for(int i = 1; i<tokens.length; i++){
                    points.add(new Point2D.Double(Double.parseDouble(tokens[i]), Double.parseDouble(tokens[++i])));
                }
                return new VecShapeCommandEllipse(points);
            case "POLYGON":
                for(int i = 1; i<tokens.length; i++){
                    points.add(new Point2D.Double(Double.parseDouble(tokens[i]), Double.parseDouble(tokens[++i])));
                }
                return new VecShapeCommandPolygon(points);
            default:return null;
        }
    }

    /**
     * method used to make a color command (OFF)
     * @param t VecCommandType the type of object to build
     * @param s an ignored param
     * @return VecCommand Object
     */
    public static VecCommand GetColorCommand(VecCommandType t, String s) {
        return GetShapeCommand(t,null,null);
    }


    /**
     * method to make a color command (FILL, PEN)
     * @param t VecCommandType the type of object to build
     * @param c the color of the color command
     * @return VecCommand Object
     */
    public static VecCommand GetColorCommand(VecCommandType t, Color c) {
        return GetShapeCommand(t,null,c);
    }


    /**
     * @param t VecCommandType the type of object to build
     * @param p an arrayList of points(verticies) representing a shape
     * @return VecCommand Object
     */
    public static VecCommand GetShapeCommand(VecCommandType t, ArrayList<Point2D.Double> p){
        return GetShapeCommand(t,p,null);
    }


    /**
     * General method for creating VecCommand Objects
     * @param t VecCommandType the type of object to build
     * @param p an arrayList of points(verticies) representing a shape
     * @param c the color of the color command
     * @return VecCommand Object
     */
    private static VecCommand GetShapeCommand(VecCommandType t, ArrayList<Point2D.Double> p, Color c){
        switch(t) {
            case FILL:
                if(c==null){
                    return new VecColorCommandFillOff();
                }else{
                    return new VecColorCommandFill(c);
                }
            case PEN: return new VecColorCommandPen(c);
            case PLOT: return new VecShapeCommandPlot(p);
            case LINE: return new VecShapeCommandLine(p);
            case RECTANGLE: return new VecShapeCommandRectangle(p);
            case ELLIPSE: return new VecShapeCommandEllipse(p);
            case POLYGON: return new VecShapeCommandPolygon(p);
            default: return null;
        }
    }
}
