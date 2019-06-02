package assignmentPackage.VecCommand;
import assignmentPackage.VecFile.VecFile;

import java.awt.*;
import java.util.ArrayList;
import java.util.*;


public abstract class VecCommand {
    protected VecCommandType type;

    public VecCommandType GetType(){
        return this.type;
    }

    public abstract void Execute(Graphics2D g, VecFile f);

    public abstract String PrintToFile();

    }
