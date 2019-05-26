package assignmentPackage;

import assignmentPackage.VecCommand.VecCommandType;
import assignmentPackage.VecFile.VecFile;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

import static assignmentPackage.VecCommand.VecCommandType.*;

public class GuiComponent extends JFrame implements ActionListener, ChangeListener, Runnable {
    //listen for actions
    //Create a file chooser
    final JFileChooser fc = new JFileChooser();

    //initial size of frame
    public static final int WIDTH = 600;
    public static final int HEIGHT = 500;

    //each panel of the frame
    private VecFile canvasPnl;
    private JPanel bottom;
    private JPanel left;

    //each different button
    private JButton Point;
    private JButton Line;
    private JButton Rectangle;
    private JButton Elipse;
    private JButton Polygon;
    private JButton imp;
    private JButton export;
    private JButton colour;
    private JButton fill;
    private JButton fillcolour;
    private ArrayList<String> fileArrayList;
    private boolean fillon;

    @Override
    public void actionPerformed(ActionEvent e){
            //throws Exception
        Object src = e.getSource();
        if (src==Point) {
            canvasPnl.SetType(PLOT);
        }
        else if (src==Line) {
            canvasPnl.SetType(LINE);
        }
        else if (src==Rectangle) {
            canvasPnl.SetType(RECTANGLE);
        }
        else if (src==Elipse) {
            canvasPnl.SetType(ELLIPSE);
        }
        else if (src==Polygon){
            canvasPnl.SetType(POLYGON);
        }
        else if (src==fill){
            canvasPnl.SetFill(!canvasPnl.GetFill());
            canvasPnl.SetColourCommand(FILL);
        }
        else if (src==colour || src==fillcolour) {
            ColourChooser frejm = new ColourChooser((JButton) src);
            frejm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frejm.setVisible(true);
        }

        else if (src==imp) {
            //In response to a button click:
            fileRead = true;
            FileDialog dialog = new FileDialog((Frame)null, "Select File to Read");
            dialog.setMode(FileDialog.LOAD);
            dialog.setVisible(true);
            String file = dialog.getFile();
            System.out.println(file);
            try {
                // pass the path to the file as a parameter
                FileReader fr = new FileReader(file);
                int i;
                while ((i = fr.read()) != -1) {

                    //System.out.print((char) i);
                }
            }
            catch(Exception e2){
                e2.printStackTrace();
            }
        }
        else if (src==export) {
            canvasPnl.SaveFile();
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


    private static boolean fileRead;
  
    //create and add panels to frame
    private void createGUI() {
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        bottom = createPanel(Color.GRAY);
        left = createPanel(Color.GRAY);

        Point = createButton("images/draw_icon.png");
        Line = createButton("images/line_icon.png");
        Rectangle = createButton("images/rectangle_icon.png");
        Elipse = createButton("images/elipse_icon.png");
        Polygon = createButton("images/polygon_icon.png");
        imp = createButton("images/import.png");
        export = createButton("images/export.png");
        colour = createButton("");
        colour.setBackground(Color.BLACK);
        fill = createButton("images/fill.png");
        fillcolour = createButton("");
        fillcolour.setBackground(Color.BLACK);

        File f = new File(System.getProperty("user.dir")+"\\newFile.vec");
        System.out.println(f.getAbsolutePath());
        canvasPnl = new VecFile(f, colour, fillcolour );
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
        button.setPreferredSize(new Dimension(40,40));
        button.addActionListener(this);
        return button;

    }

    //define layout of buttons in bottom panel
    private void layoutButtonPanel() {
        GridBagLayout layout = new GridBagLayout();
        left.setLayout(layout);
        bottom.setLayout(layout);
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
        addToPanel(left,colour,constraints,0,5,1,1);
        addToPanel(left,fill,constraints,0,6,1,1);
        addToPanel(left,fillcolour,constraints,0,7,1,1);
        addToPanel(bottom,imp,constraints,0,0,1,1);
        addToPanel(bottom,export,constraints,1,0,1,1);
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
    public static void main(String[] args){

        SwingUtilities.invokeLater(new GuiComponent());
    }
}

