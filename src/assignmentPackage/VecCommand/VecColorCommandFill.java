package assignmentPackage.VecCommand;

import assignmentPackage.VecFile.VecFile;

import java.awt.*;

public class VecColorCommandFill extends VecColorCommand implements IVecCommandExecutable {

    public VecColorCommandFill( Color c){
        this.type = VecCommandType.FILL;
        this.color = c;
    }


    @Override
    public void Execute(Graphics2D  g, VecFile f) {
        f.SetFillColor(this.color);
        f.SetFill(true);
    }
}
