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

    private boolean fillon =false;
    //initial size of frame
    public static final int WIDTH = 600;
    public static final int HEIGHT = 500;

    //each panel of the frame
    private JTabbedPane canvasArea;
    private ArrayList<VecFile> canvases;
    private ArrayList<JScrollPane> scrolls;
    private JPanel bottom;
    private JPanel left;

    //each different button
    private JButton Increase;
    private JButton Decrease;
    private JButton Point;
    private JButton Line;
    private JButton Rectangle;
    private JButton Elipse;
    private JButton Polygon;
    private JButton colour;
    private JButton fill;
    private JButton undo;
    private JButton redo;
    private JButton fillcolour;
    private JButton current;

    private JButton newPage;
    private JButton imp;
    private JButton export;

    private ArrayList<String> fileArrayList;

    @Override
    public void actionPerformed(ActionEvent e){
            //throws Exception
        Object src = e.getSource();
        if (src==Increase) {
            int index = canvasArea.getSelectedIndex();
            canvases.get(index).changeSize(1.5);
            scrolls.get(index).setViewportView(canvases.get(index));

        }
        else if (src==Decrease) {
            int index = canvasArea.getSelectedIndex();
            canvases.get(index).changeSize(0.5);
            scrolls.get(index).setViewportView(canvases.get(index));
        }
        else if (src==Point) {
            setButton(Point);
            for (VecFile canvasPnl : canvases) {
                canvasPnl.SetType(PLOT);
            }
        }
        else if (src==Line) {
            setButton(Line);
            for (VecFile canvasPnl : canvases) {
                canvasPnl.SetType(LINE);
            }
        }
        else if (src==Rectangle) {
            setButton(Rectangle);
            for (VecFile canvasPnl : canvases) {
                canvasPnl.SetType(RECTANGLE);
            }
        }
        else if (src==Elipse) {
            setButton(Elipse);
            for (VecFile canvasPnl : canvases) {
                canvasPnl.SetType(ELLIPSE);
            }
        }
        else if (src==Polygon){
            setButton(Polygon);
            for (VecFile canvasPnl : canvases) {
                canvasPnl.SetType(POLYGON);
            }
        }
        else if (src==fill){
            if (fill.getBackground() == Color.LIGHT_GRAY) {
                fill.setBackground(Color.WHITE);
                fillon = true;
            }
            else {
                fill.setBackground(Color.LIGHT_GRAY);
                fillon = false;
            }
            for (VecFile canvasPnl : canvases) {
                canvasPnl.SetFill(fillon);
                canvasPnl.SetColourCommand(FILL);
            }
        }
        else if (src==colour || src==fillcolour) {
            ColourChooser frejm = new ColourChooser((JButton) src, fill);
            frejm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frejm.setVisible(true);
        }

        else if (src==newPage) {
            FileDialog dialog = new FileDialog((Frame)null, "Select New File Location");
            dialog.setMode(FileDialog.SAVE);
            dialog.setVisible(true);
            String file = dialog.getFile();
            if(file==null){
                return;
            }
            try {
                // pass the path to the file as a parameter
                File f = new File(System.getProperty("user.dir")+"\\"+file);
                System.out.println(f.getAbsolutePath());
                createCanvas(f);
            }
            catch(Exception e2){
                e2.printStackTrace();
            }
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
                File f = new File(System.getProperty("user.dir")+"\\"+file);
                System.out.println(f.getAbsolutePath());
                createCanvas(f);
            }
            catch(Exception e2){
                e2.printStackTrace();
            }
        }
        else if (src==export) {
            int i = canvasArea.getSelectedIndex();
            canvases.get(i).SaveFile();
        }else if(src==redo){
            canvases.get(canvasArea.getSelectedIndex()).RedoLastCommand();
        }else if(src==undo){
            canvases.get(canvasArea.getSelectedIndex()).UndoLastCommand();
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

    private void createTabHead(String title) {
        int index = canvasArea.getTabCount()-1;
        JPanel pnlTab = new JPanel(new GridBagLayout());
        pnlTab.setOpaque(false);
        JLabel lblTitle = new JLabel(title);
        JButton btnClose = new JButton("x");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;

        pnlTab.add(lblTitle, gbc);

        gbc.gridx++;
        gbc.weightx = 0;
        pnlTab.add(btnClose, gbc);

        canvasArea.setTabComponentAt(index, pnlTab);

        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvasArea.remove(canvasArea.indexOfTab(title));
            }
        });
    }

    private void createCanvas(File f) {
        VecFile canvasPnl =  new VecFile(f, colour, fillcolour );
        JScrollPane canvas = new JScrollPane(canvasPnl,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrolls.add(canvas);
        canvasArea.addTab(f.getName(), canvas);
        createTabHead(f.getName());
        canvases.add(canvasPnl);
        canvasPnl.SetFill(fillon);
        fill.doClick();
        fill.doClick();
        current.doClick();
    }

    private void setButton(JButton button) {
        current.setBackground(Color.LIGHT_GRAY);
        current = button;
        current.setBackground(Color.WHITE);
    }
    private static boolean fileRead;
  
    //create and add panels to frame
    private void createGUI() {
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        canvases = new ArrayList<VecFile>();
        scrolls = new ArrayList<>();
        bottom = createPanel(Color.GRAY);
        left = createPanel(Color.GRAY);

        Increase = createButton("images/increase.png");
        Decrease = createButton("images/decrease.png");
        Point = createButton("images/draw_icon.png");
        Line = createButton("images/line_icon.png");
        Rectangle = createButton("images/rectangle_icon.png");
        Elipse = createButton("images/elipse_icon.png");
        Polygon = createButton("images/polygon_icon.png");
        undo = createButton("images/undo.png");
        redo = createButton("images/redo.png");
        current = Line;

        imp = createButton("images/import.png");
        export = createButton("images/export.png");
        newPage = createButton("images/new_page.png");

        colour = createButton("");
        colour.setName("Pen Colour");
        colour.setBackground(Color.BLACK);

        fill = createButton("images/fill.png");
        fillcolour = createButton("");
        fillcolour.setName("Fill Colour");
        fillcolour.setBackground(Color.BLACK);

        File f = new File(System.getProperty("user.dir")+"\\newFile.vec");
        System.out.println(f.getAbsolutePath());
        canvasArea = new JTabbedPane();
        layoutButtonPanel();
        createCanvas(f);


        current.setBackground(Color.WHITE);
        getContentPane().add(canvasArea,BorderLayout.CENTER);
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
        button.setBackground(Color.LIGHT_GRAY);
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

        addToPanel(left, Increase   , constraints, 0,0,1,1);
        addToPanel(left, Decrease   , constraints, 1,0,1,1);
        addToPanel(left, Point      , constraints,0,1,1,1);
        addToPanel(left, Line       , constraints,1,1,1,1);
        addToPanel(left, Rectangle  , constraints,0,2,1,1);
        addToPanel(left, Elipse     , constraints, 1,2,1,1);
        addToPanel(left, Polygon    , constraints,0,3,1,1);
        addToPanel(left,colour      , constraints,1,5,1,1);
        addToPanel(left,fill        , constraints,0,6,1,1);
        addToPanel(left,fillcolour  , constraints,1,6,1,1);
        addToPanel(left,undo        , constraints,0,8,1,1);
        addToPanel(left,redo        , constraints,1,8,1,1);

        addToPanel(bottom,newPage,constraints,0,0,1,1);
        addToPanel(bottom,imp,constraints,1,0,1,1);
        addToPanel(bottom,export,constraints,2,0,1,1);
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

