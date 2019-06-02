package assignmentPackage.VecCommand;

import assignmentPackage.VecFile.VecFile;

import java.awt.*;

/**
 * a Vec Fill Command
 */
public class VecColorCommandFill extends VecColorCommand {

    /**
     * @param c constructor
     */
    public VecColorCommandFill( Color c){
        this.type = VecCommandType.FILL;
        this.color = c;
    }


    /**
     * @param g the graphics object that will render the object
     * @param f the parent file containing the canvas
     */
    @Override
    public void Execute(Graphics2D  g, VecFile f) {
        f.SetFillColor(this.color);
        f.SetFill(true);
    }
}
