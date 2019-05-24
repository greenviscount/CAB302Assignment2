package assignmentPackage.VecCommand;

import java.awt.*;

public class VecColorCommand extends VecCommand implements IVecCommandPrintable {
    private Color color = Color.WHITE;
    private boolean off = false;

    public VecColorCommand(VecCommandType t, Color c) throws Exception {
        super(t);
        if(t!=VecCommandType.FILL || t != VecCommandType.PEN){
            throw new Exception("invalid VecCommandType");//TODO create custom exception
        }
        this.color = (c==null)?this.color:c;
    }

    public VecColorCommand(VecCommandType t, boolean b) throws Exception {
        super(t);
        if(t!=VecCommandType.FILL || t == VecCommandType.PEN){
            throw new Exception("invalid VecCommandType");//TODO create custom exception
        }
        this.off = b;
    }

    @Override
    public String PrintToFile() {
        StringBuilder str = new StringBuilder();
        str.append(GetType().toString());
        str.append(" ");
        if(off){
            str.append("OFF");
            str.append("\n");
        }else{
            str.append(String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()));
            str.append("\n");
        }


        return str.toString();
    }

    public Color GetColor(){
        return this.color;
    }
}
