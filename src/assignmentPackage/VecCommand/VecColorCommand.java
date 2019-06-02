package assignmentPackage.VecCommand;

import java.awt.*;

/**
 * representation of a VecColorCommand
 *
 * defines methods for accessing the color value of the command
 * and getting the string representation
 */
public abstract class VecColorCommand extends VecCommand{
    protected Color color = Color.BLACK;

    /**
     * @return the string representation of this command
     */
    public String PrintToFile() {
        StringBuilder str = new StringBuilder();
        str.append(GetType().toString());
        str.append(" ");
        str.append(String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()));
        str.append("\n");
        return str.toString();
    }

    /**
     * @return Color this commands color value
     */
    public Color GetColor(){
        return this.color;
    }


    /**
     * @param c sets the color of this command
     */
    public void SetColor(Color c){
        this.color = c;
    }
}
