package assignmentPackage.VecCommand;

import assignmentPackage.VecFile.VecFile;

import java.awt.*;

public class VecColorCommandFillOff extends VecColorCommand{
    private String command = "OFF";

    /**
     * constructor
     */
    public VecColorCommandFillOff(){
        this.type = VecCommandType.FILL;

    }

    /**
     * @return the string representation of the command
     */
    @Override
    public String PrintToFile() {
        StringBuilder str = new StringBuilder();
        str.append(GetType().toString());
        str.append(" ");
        str.append(this.command);
        str.append("\n");
        return str.toString();
    }

    /**
     * @param g the graphics object that will render the object
     * @param f the parent file containing the canvas
     */
    @Override
    public void Execute(Graphics2D  g, VecFile f) {
        //f.SetFillColor(Color.WHITE);
        f.SetFill(false);
    }
}
