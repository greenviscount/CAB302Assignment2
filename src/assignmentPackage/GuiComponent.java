package assignmentPackage;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiComponent extends JFrame implements ActionListener, ChangeListener, Runnable {
    //listen for actions
    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src==Point) {
            JButton btn = ((JButton) src);
            canvasPnl.changeMode(0);
        }
        else if (src==Line) {
            JButton btn = ((JButton) src);
            canvasPnl.changeMode(1);
        }
        else if (src==Rectangle) {
            JButton btn = ((JButton) src);
            canvasPnl.changeMode(2);
        }
        else if (src==Elipse) {
            JButton btn = ((JButton) src);
            canvasPnl.changeMode(3);
        }
        else {
            JButton btn = ((JButton) src);
            canvasPnl.changeMode(4);
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }

    //run gui
    @Override
    public void run() {
        createGUI();
    }

    //initial size of frame
    public static final int WIDTH = 600;
    public static final int HEIGHT = 500;

    //each panel of the frame
    private CanvasComponent canvasPnl;
    private JPanel bottom;
    private JPanel left;

    //each different button
    private JButton Point;
    private JButton Line;
    private JButton Rectangle;
    private JButton Elipse;
    private JButton Polygon;

    //create and add panels to frame
    private void createGUI() {
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        canvasPnl = new CanvasComponent();
        bottom = createPanel(Color.GRAY);
        left = createPanel(Color.GRAY);

        Point = createButton("images/draw_icon.png");
        Line = createButton("images/line_icon.png");
        Rectangle = createButton("images/rectangle_icon.png");
        Elipse = createButton("images/elipse_icon.png");
        Polygon = createButton("images/polygon_icon.png");

        layoutButtonPanel();

        getContentPane().add(canvasPnl,BorderLayout.CENTER);
        getContentPane().add(left,BorderLayout.WEST);
        getContentPane().add(bottom,BorderLayout.SOUTH);
        repaint();
        setVisible(true);
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

        addToPanel(left, Point, constraints,0,0,1,1);
        addToPanel(left, Line, constraints,0,1,1,1);
        addToPanel(left, Rectangle, constraints,0,2,1,1);
        addToPanel(left, Elipse, constraints, 0,3,1,1);
        addToPanel(left, Polygon, constraints,0,4,1,1);
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

