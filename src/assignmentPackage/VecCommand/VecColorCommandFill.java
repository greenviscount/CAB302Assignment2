package assignmentPackage.VecCommand;

import java.awt.*;

public class VecColorCommandFill extends VecColorCommand implements IVecCommandExecutable {

    public VecColorCommandFill( Color c){
        this.type = VecCommandType.FILL;
        this.color = c;
    }

    @Override
    public void Execute() {

    }
}
