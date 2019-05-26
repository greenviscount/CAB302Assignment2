package assignmentPackage.VecCommand;

import assignmentPackage.VecFile.VecFile;

import java.awt.*;

public class VecColorCommandFillOff extends VecColorCommand implements IVecCommandExecutable, IVecCommandPrintable{
    private String command = "OFF";
    public VecColorCommandFillOff(){
        this.type = VecCommandType.FILL;

    }

    @Override
    public String PrintToFile() {
        StringBuilder str = new StringBuilder();
        str.append(GetType().toString());
        str.append(" ");
        str.append(this.command);
        str.append("\n");
        return str.toString();
    }

    @Override
    public void Execute(Graphics2D  g, VecFile f) {
        f.SetFillColor(Color.WHITE);
        f.SetFill(false);
    }
}
