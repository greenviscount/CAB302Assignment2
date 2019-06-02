package assignmentPackage.VecCommand;

import java.awt.*;

public abstract class VecColorCommand extends VecCommand{
    protected Color color = Color.BLACK;

    VecColorCommand() {
    }

    public String PrintToFile() {
        StringBuilder str = new StringBuilder();
        str.append(GetType().toString());
        str.append(" ");
        str.append(String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()));
        str.append("\n");
        return str.toString();
    }

    public Color GetColor(){
        return this.color;
    }

    public void SetColor(Color c){
        this.color = c;
    }
}
