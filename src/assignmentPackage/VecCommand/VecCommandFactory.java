package assignmentPackage.VecCommand;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public abstract class VecCommandFactory {

    public VecCommand ParseCommandString(String s){
        String[] tokens = s.split("\\s");
        ArrayList<Point2D> points = new ArrayList<Point2D>();
        VecCommand vcom;

        switch(tokens[0]){
            case "FILL":
                try{
                    if(tokens[1].equals("OFF")){
                        vcom  = new VecColorCommand(VecCommandType.FILL,true);

                    }else{
                        vcom  = new VecColorCommand(VecCommandType.FILL,Color.decode(tokens[1]));
                    }
                }catch(Exception e){
                    return null;
                }
                return vcom;
            case "PEN":
                try{
                    vcom  = new VecColorCommand(VecCommandType.PEN,Color.decode(tokens[1]));
                }catch(Exception e){
                    return null;
                }
                return vcom;

            case "PLOT":
                for(int i = 1; i<tokens.length; i+=2){
                    points.add(new Point2D.Double(Double.parseDouble(tokens[i]), Double.parseDouble(tokens[i])));
                }
                return new VecShapeCommandPlot(points);
            case "LINE":
                for(int i = 1; i<tokens.length; i+=2){
                    points.add(new Point2D.Double(Double.parseDouble(tokens[i]), Double.parseDouble(tokens[i])));
                }
                return new VecShapeCommandLine(points);
            case "RECTANGLE":
                for(int i = 1; i<tokens.length; i+=2){
                    points.add(new Point2D.Double(Double.parseDouble(tokens[i]), Double.parseDouble(tokens[i])));
                }
                return new VecShapeCommandRectangle(points);
            case "ELLIPSE":
                for(int i = 1; i<tokens.length; i+=2){
                    points.add(new Point2D.Double(Double.parseDouble(tokens[i]), Double.parseDouble(tokens[i])));
                }
                return new VecShapeCommandEllipse(points);
            case "POLYGON":
                for(int i = 1; i<tokens.length; i+=2){
                    points.add(new Point2D.Double(Double.parseDouble(tokens[i]), Double.parseDouble(tokens[i])));
                }
                return new VecShapeCommandPolygon(points);
            default:return null;
        }
    }

    public VecCommand GetShapeCommand(VecCommandType t, ArrayList<Point2D> p, Color c){
        switch(t) {
            case FILL:
                try{
                    if(c != null)return new VecColorCommand(t,c); else return new VecColorCommand(t,true);
                }catch(Exception e){
                    return null;
                }
            case PEN:
                try{
                    return new VecColorCommand(t,c);
                }catch(Exception e){
                    return null;
                }
            case PLOT: return new VecShapeCommandPlot(p);
            case LINE: return new VecShapeCommandLine(p);
            case RECTANGLE: return new VecShapeCommandRectangle(p);
            case ELLIPSE: return new VecShapeCommandEllipse(p);
            case POLYGON: return new VecShapeCommandPolygon(p);
            default: return null;
        }
    }
}
