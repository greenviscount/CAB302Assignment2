package assignmentPackage.VecCommand;

import assignmentPackage.VecFile.VecFile;

import java.awt.*;

public class VecColorCommandFillOff extends VecColorCommand implements IVecCommandExecutable{
    String command = "OFF";
    public VecColorCommandFillOff(){
        this.type = VecCommandType.FILL;

    }


    @Override
    public void Execute(Graphics2D  g, VecFile f) {
        f.SetColor(Color.BLACK);
        f.SetFill(false);
    }
}
