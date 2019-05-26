package assignmentPackage.VecCommand;
import java.util.ArrayList;
import java.awt.Point;
import java.awt.Color;
import java.util.*;


public abstract class VecCommand {
    protected VecCommandType type;

    public VecCommandType GetType(){
        return this.type;
    }

}
