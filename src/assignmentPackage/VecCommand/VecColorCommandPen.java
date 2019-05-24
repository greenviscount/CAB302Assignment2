package assignmentPackage.VecCommand;

import java.awt.*;

public class VecColorCommandPen extends VecColorCommand implements IVecCommandExecutable {
    public VecColorCommandPen(Color c){
        this.type = VecCommandType.PEN;
        this.color = c;
    }

    @Override
    public void Execute() {

    }
}
