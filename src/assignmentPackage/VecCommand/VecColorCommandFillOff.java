package assignmentPackage.VecCommand;

public class VecColorCommandFillOff extends VecColorCommand implements IVecCommandExecutable{
    String command = "OFF";
    public VecColorCommandFillOff(){
        this.type = VecCommandType.FILL;

    }

    @Override
    public void Execute() {

    }
}
