package assignmentPackage;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ColourChooser extends JFrame implements ChangeListener {
    JFrame ColourChooser = new JFrame();
    private JButton button;
    @Override
    public void stateChanged(ChangeEvent e) {
        button.setBackground(colourwheel.getColor());
    }

    private JColorChooser colourwheel;
    public ColourChooser(JButton button) {
        super(button.getName());
        this.button = button;
        this.setSize(500,500);
        colourwheel = new JColorChooser(button.getBackground());
        colourwheel.getSelectionModel().addChangeListener(this);
        this.add(colourwheel);
    }
}
