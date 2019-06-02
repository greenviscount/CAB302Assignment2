package assignmentPackage.VecCommand;
import assignmentPackage.VecFile.VecFile;

import java.awt.*;
import java.util.ArrayList;
import java.util.*;


/**
 * proto class representing a Vec Command
 *
 * defines methods for getting the type of the command and
 * abstract methods to get the string the command and
 * execute the command
 */
public abstract class VecCommand {
    protected VecCommandType type;

    public VecCommandType GetType(){
        return this.type;
    }

    /**
     * @param g the graphics object that will render the object
     * @param f the parent file containing the canvas
     */
    public abstract void Execute(Graphics2D g, VecFile f);


    /**
     * @return returns the string representation of the Command
     */
    public abstract String PrintToFile();

    }
