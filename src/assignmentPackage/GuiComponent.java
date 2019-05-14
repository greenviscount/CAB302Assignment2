package assignmentPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiComponent extends JFrame implements ActionListener, Runnable {


    //listen for actions
    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src==Load) {
            JButton btn = ((JButton) src);
            display.setText(btn.getText().trim());
        }
    }
    //run gui

    @Override
    public void run() {
        createGUI();
    }

    //initial size of frame
    public static final int WIDTH = 300;
    public static final int HEIGHT = 200;

    //each panel of the frame
    private JPanel top;
    private JPanel right;
    private JPanel canvasPnl;
    private JPanel bottom;
    private JPanel left;

    //each different button
    private JButton Load;
    private JButton Unload;
    private JButton Find;
    private JButton Switch;

    //initial display (replaced with canvas)
    private JTextArea display;

    //create and add panels to frame
    private void createGUI() {
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        top = createPanel(Color.GRAY);
        right = createPanel(Color.GRAY);
        canvasPnl = createPanel(Color.WHITE);
        bottom = createPanel(Color.GRAY);
        left = createPanel(Color.GRAY);

        Load = createButton("Load");
        Unload = createButton("Unload");
        Find = createButton("Find");
        Switch = createButton("Switch");
        layoutButtonPanel();

        createDisplay();
        canvasPnl.setLayout(new BorderLayout());
        canvasPnl.add(display, BorderLayout.CENTER);

        getContentPane().add(canvasPnl,BorderLayout.CENTER);
        getContentPane().add(right,BorderLayout.EAST);
        getContentPane().add(left,BorderLayout.WEST);
        getContentPane().add(top,BorderLayout.NORTH);
        getContentPane().add(bottom,BorderLayout.SOUTH);
        repaint();
        setVisible(true);
    }

    //initialise display (replaced with canvas)
    private void createDisplay() {
        display = new JTextArea();
        display.setEditable(false);
        display.setLineWrap(true);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setBorder(BorderFactory.createEtchedBorder());
    }

    //create new panel with colour c
    private JPanel createPanel(Color c) {
        JPanel temp = new JPanel();
        temp.setBackground(c);
        return temp;
    }

    //create new button with text str
    private JButton createButton(String str) {
        JButton button = new JButton(str);
        button.addActionListener(this);
        return button;

    }

    //define layout of buttons in bottom panel
    private void layoutButtonPanel() {
        GridBagLayout layout = new GridBagLayout();
        bottom.setLayout(layout);

        //add components to grid
        GridBagConstraints constraints = new GridBagConstraints();
        //Defaults
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 100;
        constraints.weighty = 100;

        addToPanel(bottom, Load, constraints,0,0,2,1);
        addToPanel(bottom, Unload, constraints,3,0,2,1);
        addToPanel(bottom, Find, constraints,0,2,2,1);
        addToPanel(bottom, Switch, constraints,3,2,2,1);
    }

    //add component c to panel jp in position x, y with size w by h
    private void addToPanel(JPanel jp,Component c, GridBagConstraints
            constraints,int x, int y, int w, int h) {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = w;
        constraints.gridheight = h;
        jp.add(c, constraints);
    }

    //main class
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new GuiComponent());
    }
}

