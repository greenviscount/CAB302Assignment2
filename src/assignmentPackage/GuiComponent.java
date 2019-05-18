package assignmentPackage;

import javax.imageio.ImageIO;
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

        Load = createButton("images/draw_icon.png");
        //Unload = createButton("U");
        //Find = createButton("F");
        //Switch = createButton("S");
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
    private JButton createButton(String src) {
        JButton button = new JButton();
        try {
            Image img = ImageIO.read(getClass().getResource(src)).getScaledInstance(25,25, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        button.addActionListener(this);
        return button;

    }

    //define layout of buttons in bottom panel
    private void layoutButtonPanel() {
        GridBagLayout layout = new GridBagLayout();
        left.setLayout(layout);

        //add components to grid
        GridBagConstraints constraints = new GridBagConstraints();
        //Defaults
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.weightx = 50;
        constraints.weighty = 50;

        addToPanel(left, Load, constraints,0,0,1,1);
        //addToPanel(left, Unload, constraints,0,1,1,1);
        //addToPanel(left, Find, constraints,0,2,1,1);
        //addToPanel(left, Switch, constraints,0,3,1,1);
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

