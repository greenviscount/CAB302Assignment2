package assignmentPackage.VecCommand;

import assignmentPackage.VecFile.VecFile;

import java.awt.*;

public class VecColorCommandPen extends VecColorCommand {

    /**
     * constructor
     */
    public VecColorCommandPen(Color c){
        this.type = VecCommandType.PEN;
        this.color = c;
    }

    /**
     * @param g the graphics object that will render the object
     * @param f the parent file containing the canvas
     */
    @Override
    public void Execute(Graphics2D  g, VecFile f) {
        f.SetPenColor(this.color);
    }
}
