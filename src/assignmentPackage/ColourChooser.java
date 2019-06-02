package assignmentPackage;

import assignmentPackage.VecCommand.VecCommandType;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
/**
*
 */
public class ColourChooser extends JFrame implements ChangeListener {
    JFrame ColourChooser = new JFrame();
    private JButton colour, button;
    /**
    * change colour when colour is changed
     * @param e the colour change event
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        colour.setBackground(colourwheel.getColor());
        if (colour.getName() == "Fill Colour") {
            button.doClick();
            button.doClick();
        }
    }

    private JColorChooser colourwheel;

    /**
     * constuct colourChooser frame
     * @param colour
     * @param button
     */
    public ColourChooser(JButton colour, JButton button) {
        super(colour.getName());
        this.colour = colour;
        this.button = button;
        this.setSize(500,500);
        colourwheel = new JColorChooser(colour.getBackground());
        colourwheel.getSelectionModel().addChangeListener(this);
        this.add(colourwheel);
    }
}
